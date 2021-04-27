package com.imcode.rls.userinfo.service;

import com.imcode.rls.userinfo.model.UserInfo;

import java.util.List;

public interface IUserInfoService {

    List<UserInfo> getUserinfo(String userID);

}
