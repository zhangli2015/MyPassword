package cn.xing.mypassword.service;

import cn.xing.mypassword.model.PasswordGroup;

/**
 * 密码变化监听器
 * 
 * @author zengdexing
 * 
 */
public interface OnPasswordGroupListener {

	/**
	 * 用户增加了新的密码
	 */
	public void onNewPasswordGroup(PasswordGroup passwordGroup);

	/**
	 * 密码被删除了
	 */
	public void onDeletePasswordGroup(String passwordGroupName);
}
