package com.imcode.rls.userinfo.service.impl;

import com.imcode.rls.userinfo.mapper.UserInfoMapper;
import com.imcode.rls.userinfo.model.UserInfo;
import com.imcode.rls.userinfo.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfo> getUserinfo(String userID) {
        return userInfoMapper.getUserinfo(userID);
    }
}
