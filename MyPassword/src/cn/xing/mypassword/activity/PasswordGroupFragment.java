package cn.xing.mypassword.activity;

import java.util.List;

import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import cn.xing.mypassword.R;
import cn.xing.mypassword.adapter.PasswordGroupAdapter;
import cn.xing.mypassword.app.BaseActivity;
import cn.xing.mypassword.dialog.GreatePasswordGroupDialog;
import cn.xing.mypassword.model.PasswordGroup;
import cn.xing.mypassword.model.SettingKey;
import cn.xing.mypassword.service.Mainbinder;
import cn.xing.mypassword.service.OnGetAllPasswordGroupCallback;
import cn.xing.mypassword.service.OnPasswordGroupListener;

public class PasswordGroupFragment extends Fragment implements OnItemClickListener, OnGetAllPasswordGroupCallback,
		OnPasswordGroupListener {
	private Mainbinder mainbinder;
	private PasswordGroupAdapter passwordGroupAdapter;
	private OnPasswordGroupSelected onPasswordGroupSelected;

	private OnClickListener onAddClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			GreatePasswordGroupDialog cDialog = new GreatePasswordGroupDialog(getActivity(), mainbinder);
			cDialog.show();
		}
	};

	private OnItemLongClickListener onDeleteClickListener = new OnItemLongClickListener() {
		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
			// 长按删除密码
			final String passwordGroupName = ((PasswordGroup) (parent.getItemAtPosition(position))).getGroupName();
			showDeleteDialog(passwordGroupName);
			return true;
		}

	};

	/**
	 * 显示删除密码分组对话框
	 * 
	 * @param passwordGroupName
	 *            要删除的密码分组
	 */
	private void showDeleteDialog(final String passwordGroupName) {
		Builder builder = new Builder(getActivity());
		builder.setMessage(getString(R.string.delete_password_group_message, passwordGroupName));
		builder.setTitle(R.string.delete_password_group_title);
		builder.setNeutralButton(R.string.delete_password_sure, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				mainbinder.deletePasswordgroup(passwordGroupName);
			}
		});
		builder.setNegativeButton(R.string.delete_password_cancle, null);
		builder.show();
	}

	public void setDataSource(Mainbinder mainbinder, OnPasswordGroupSelected onPasswordGroupSelected) {
		this.mainbinder = mainbinder;
		this.onPasswordGroupSelected = onPasswordGroupSelected;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		passwordGroupAdapter = new PasswordGroupAdapter(getActivity());
		mainbinder.registOnPasswordGroupListener(this);
		mainbinder.getAllPasswordGroup(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mainbinder.unregistOnPasswordGroupListener(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_password_group, null);
		ListView listView = (ListView) rootView.findViewById(R.id.fragment_password_group_listView);
		listView.setAdapter(passwordGroupAdapter);
		listView.setOnItemClickListener(this);
		listView.setOnItemLongClickListener(onDeleteClickListener);
		View addView = rootView.findViewById(R.id.fragment_password_group_add);
		addView.setOnClickListener(onAddClickListener);
		return rootView;
	}

	private BaseActivity getBaseActivity() {
		return (BaseActivity) getActivity();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		PasswordGroup passwordGroup = passwordGroupAdapter.getItem(position);
		selectItem(passwordGroup.getGroupName());
	}

	public static interface OnPasswordGroupSelected {
		public void onPasswordGroupSelected(String passwordGroupName);
	}

	@Override
	public void onGetAllPasswordGroup(List<PasswordGroup> passwordGroups) {
		BaseActivity baseActivity = getBaseActivity();
		if (baseActivity != null) {
			String lastGroupName = baseActivity.getSetting(SettingKey.LAST_SHOW_PASSWORDGROUP_NAME,
					getString(R.string.password_group_default_name));

			passwordGroupAdapter.setCurrentGroupName(lastGroupName);

			passwordGroupAdapter.setData(passwordGroups);
		}
	}

	@Override
	public void onNewPasswordGroup(PasswordGroup passwordGroup) {
		passwordGroupAdapter.addPasswordGroup(passwordGroup);
		if (passwordGroupAdapter.getCount() == 1) {
			selectItem(passwordGroup.getGroupName());
		}
	}

	@Override
	public void onDeletePasswordGroup(String passwordGroupName) {
		boolean result = passwordGroupAdapter.removePasswordGroup(passwordGroupName);
		if (result && passwordGroupName.equals(passwordGroupAdapter.getCurrentGroupName())) {
			String selectedname = "";
			if (passwordGroupAdapter.getCount() > 0)
				selectedname = passwordGroupAdapter.getItem(0).getGroupName();

			selectItem(selectedname);
		}
	}

	private void selectItem(String selectedname) {
		BaseActivity baseActivity = getBaseActivity();
		baseActivity.putSetting(SettingKey.LAST_SHOW_PASSWORDGROUP_NAME, selectedname);

		passwordGroupAdapter.setCurrentGroupName(selectedname);
		onPasswordGroupSelected.onPasswordGroupSelected(selectedname);
	}
}
