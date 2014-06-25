package cn.xing.mypassword.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import cn.xing.mypassword.R;
import cn.xing.mypassword.model.PasswordGroup;

public class PasswordGroupAdapter extends BaseAdapter {
	private ArrayList<PasswordGroup> passwordGroups = new ArrayList<>();
	private Context context;

	public PasswordGroupAdapter(Context context) {
		super();
		this.context = context;
	}

	public void setData(ArrayList<PasswordGroup> passwordGroups) {
		this.passwordGroups.clear();
		this.passwordGroups.addAll(passwordGroups);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		//return passwordGroups.size();
		return 6;
	}

	@Override
	public PasswordGroup getItem(int position) {
		// TODO Auto-generated method stub
		return passwordGroups.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = LayoutInflater.from(context).inflate(R.layout.password_group_item, null);
		return view;
	}

}
