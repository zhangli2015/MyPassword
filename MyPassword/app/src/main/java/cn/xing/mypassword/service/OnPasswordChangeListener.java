package cn.xing.mypassword.service;

import cn.xing.mypassword.model.Password;

/**
 * 密码变化监听器
 *
 * @author zengdexing
 */
public interface OnPasswordChangeListener {
    /**
     * 用户增加了新的密码
     */
    void onNewPassword(Password password);

    /**
     * 密码被删除了
     */
    void onDeletePassword(int id);

    /**
     * 密码的属性发生变化了
     */
    void onUpdatePassword(Password password);
}
