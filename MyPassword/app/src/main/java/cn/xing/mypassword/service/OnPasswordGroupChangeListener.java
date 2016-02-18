package cn.xing.mypassword.service;

import cn.xing.mypassword.model.PasswordGroup;

/**
 * 密码变化监听器
 *
 * @author zengdexing
 */
public interface OnPasswordGroupChangeListener {

    /**
     * 用户增加了新的密码
     */
    void onNewPasswordGroup(PasswordGroup passwordGroup);

    /**
     * 组密码被删除了
     */
    void onDeletePasswordGroup(String passwordGroupName);


    void onUpdateGroupName(String oldGroupName, String newGroupName);
}
