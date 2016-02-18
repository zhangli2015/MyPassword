package cn.xing.mypassword.service;

import cn.xing.mypassword.model.Password;

public interface OnGetPasswordCallback {
    void onGetPassword(Password password);
}
