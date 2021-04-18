package com.imcode.rls.user.service;

import com.imcode.common.model.R;
import com.imcode.rls.user.model.Loginregister;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface IUserLoginService {
    List<Loginregister> getUser();
    boolean addUser(Loginregister loginregister);
    Loginregister getLoginregisterByNameAndPwd(String username,String password);
    Loginregister getLoginregisterByName(String username);
    R registerUser(String username, String password, String repassword, String phone, String code, HttpSession session);

    boolean updateBindWxOpenid(String openid,String userid);

    Loginregister getLoginregisterByPhoneAndPwd(String username,String password);

    R wxRegisterAndBind(String phone,String password,String repassword,String code,String openid,HttpSession session);

    R registerUserInfo(Map<String, Object> data);
}
