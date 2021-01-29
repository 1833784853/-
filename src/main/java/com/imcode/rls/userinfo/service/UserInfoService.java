package com.imcode.rls.userinfo.service;

import com.imcode.common.model.R;

import java.util.Map;

public interface UserInfoService {
    R addUserInfo(Map<String,String> map);
}
