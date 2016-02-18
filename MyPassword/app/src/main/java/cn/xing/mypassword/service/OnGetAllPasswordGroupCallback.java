package cn.xing.mypassword.service;

import java.util.List;

import cn.xing.mypassword.model.PasswordGroup;

public interface OnGetAllPasswordGroupCallback {
    void onGetAllPasswordGroup(List<PasswordGroup> passwordGroups);
}
