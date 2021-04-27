package com.imcode.rls.userinfo.mapper;

import com.imcode.rls.userinfo.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface UserInfoMapper {

    List<UserInfo> getUserinfo(String userID);

}
