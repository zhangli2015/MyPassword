package cn.xing.mypassword.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import cn.xing.mypassword.R;
import cn.xing.mypassword.adapter.PasswordGroupAdapter;
import cn.xing.mypassword.service.Mainbinder;

public class PasswordGroupFragment extends Fragment {
	private Mainbinder mainbinder;
	private PasswordListFragment passwordListFragment;
	private PasswordGroupAdapter passwordGroupAdapter;

	public void setDataSource(Mainbinder mainbinder) {
		this.mainbinder = mainbinder;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		passwordGroupAdapter = new PasswordGroupAdapter(getActivity());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_password_group, null);
		ListView listView = (ListView) rootView.findViewById(R.id.fragment_password_group_listView);
		listView.setAdapter(passwordGroupAdapter);
		return rootView;
	}
}
