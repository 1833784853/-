package com.imcode.rls.user.mapper;


import com.imcode.rls.user.model.Loginregister;
import com.imcode.rls.user.model.WxUser;

import java.util.List;
import java.util.Map;

public interface LoginregisterMapper {

    /**
     * 获取所有用户
     * @return
     */
    List<Loginregister> getLoginregister();

    boolean addUser(Loginregister loginregister);

    Loginregister getLoginregisterByNameAndPwd(Map<String,String> map);
    Loginregister getLoginregisterByName(String username);
    Loginregister getLoginregisterByID(String userID);
    Loginregister getPhone(String phone);
    Loginregister getPhoneAndPwd(Map<String,String> map);

    Loginregister getOpenidUser(String openid);

    boolean addWxUser(WxUser wxUser);

    WxUser getWxUser(String wxOpenid);

    boolean updateBindWxOpenid(Map<String,String> map);

    boolean registerUserInfo(Map<String, Object> data);

    boolean updateStatus(Map<String, Object> data);

}
