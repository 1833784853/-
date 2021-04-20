package com.imcode.rls.user.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imcode.common.model.CacheMap;
import com.imcode.common.model.JwtUtils;
import com.imcode.common.model.R;
import com.imcode.rls.user.mapper.LoginregisterMapper;
import com.imcode.rls.user.model.Loginregister;
import com.imcode.rls.user.model.WxUser;
import com.imcode.rls.user.service.IUserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserLoginServiceImpl implements IUserLoginService {

    @Autowired
    private LoginregisterMapper loginRegisterMapper;

    public List<Loginregister> getUser() {
        return loginRegisterMapper.getLoginregister();
    }

    public boolean addUser(Loginregister loginregister) {
        return loginRegisterMapper.addUser(loginregister);
    }

    public Loginregister getLoginregisterByNameAndPwd(final String username, final String password) {
        return loginRegisterMapper.getLoginregisterByNameAndPwd(new HashMap<String, String>() {{
            put("username", username);
            put("password", password);
        }});
    }

    public Loginregister getLoginregisterByName(String username) {
        return loginRegisterMapper.getLoginregisterByName(username);
    }

    public Loginregister getPhone(String phone) {
        return loginRegisterMapper.getPhone(phone);
    }

    public R registerUser(String username, String password, String repassword, String phone, String code, HttpSession session) {
        R json = R.ok();
        json.put("msg", "注册成功");
        // 用户名
        if (username == null || username.equals("")) {
            return R.error("请输入用户名！");
        }
        if (!username.matches("^[\u4e00-\u9fa5\\w]{3,16}$")) {
            return R.error("用户名只能是中文、字母、数字、下划线组成的3-16个字符！！！");
        }
        if (this.getLoginregisterByName(username) != null) {
            return R.error("已注册");
        }

        // 密码
        if (password == null || password.equals("")) {
            return R.error("请输入密码！");
        }
        if (password.length() < 6 || password.length() > 18) {
            return R.error("请输入6-18位，数字、大小写字母组成的密码！！");
        }

        // 手机号
        if (phone == null || phone.equals("")) {
            return R.error("请输入密码！");
        }
        if (!phone.matches("^(0|86|17951)?(13[0-9]|15[012356789]|166|17[3678]|18[0-9]|14[57])[0-9]{8}$")) {
            return R.error("请输入正确的手机号码!!");
        }
        if (this.getPhone(phone) != null) {
            return R.error("该手机号码已被注册，请更换!!");
        }

        // 确认密码
        if (repassword == null || repassword.equals("")) {
            return R.error("请输入密码！");
        }
        if (!repassword.equals(password)) {
            return R.error("确认密码和密码不一致！！");
        }
        // 验证码
        String mapCode = (String) CacheMap.getInstance().get(session.getId());
        if (mapCode == null) {
            return R.error("验证码错误！！");
        }
        if (!mapCode.equals(code.toUpperCase())) {
            return R.error("验证码错误！！");
        }
        // 校验通过---------------

        Loginregister loginregister = new Loginregister();
        loginregister.setUserId(UUID.randomUUID().toString() + new Date().getTime());
        loginregister.setUserName(username);
        loginregister.setUserPassword(DigestUtils.md5DigestAsHex(repassword.getBytes()));
        loginregister.setUserPhone(phone);
        loginregister.setUserType("普通用户");
        loginregister.setUserStatus(1);
        loginregister.setRegisterTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        boolean b = loginRegisterMapper.addUser(loginregister);
        if (b) {
            return json;
        } else {
            return json.put("msg", "注册失败，请联系客服或技术人员！！");
        }
    }

    public boolean updateBindWxOpenid(final String openid, final String userid) {
        return loginRegisterMapper.updateBindWxOpenid(new HashMap<String, String>() {{
            put("openid", openid);
            put("userid", userid);
        }});
    }

    public Loginregister getLoginregisterByPhoneAndPwd(final String username, final String password) {
        return loginRegisterMapper.getPhoneAndPwd(new HashMap<String, String>() {{
            put("phone", username);
            put("password", password);
        }});
    }

    public R wxRegisterAndBind(String phone, String password, String repassword, String code, String openid, HttpSession session) {
        R json = R.ok();
        json.put("msg", "注册并绑定成功");

        // 密码
        if (password == null || password.equals("")) {
            return R.error("请输入密码！");
        }
        if (password.length() < 6 || password.length() > 18) {
            return R.error("请输入6-18位，数字、大小写字母组成的密码！！");
        }

        // 手机号
        if (phone == null || phone.equals("")) {
            return R.error("请输入密码！");
        }
        if (!phone.matches("^(0|86|17951)?(13[0-9]|15[012356789]|166|17[3678]|18[0-9]|14[57])[0-9]{8}$")) {
            return R.error("请输入正确的手机号码!!");
        }
        if (this.getPhone(phone) != null) {
            return R.error("该手机号码已被注册，请更换!!");
        }

        // 确认密码
        if (repassword == null || repassword.equals("")) {
            return R.error("请输入密码！");
        }
        if (!repassword.equals(password)) {
            return R.error("确认密码和密码不一致！！");
        }
        // 验证码
        String mapCode = (String) CacheMap.getInstance().get(session.getId());
        if (mapCode == null) {
            return R.error("验证码错误！！");
        }
        if (!mapCode.equals(code.toUpperCase())) {
            return R.error("验证码错误！！");
        }

        if (openid == null) return R.error("网络错误，请重新登录");
        WxUser wxUser = loginRegisterMapper.getWxUser(openid);
        if (wxUser == null) return R.error("网络错误，请重新登录");

        Loginregister loginregister = new Loginregister();
        loginregister.setUserId(UUID.randomUUID().toString() + new Date().getTime());
        loginregister.setUserName(wxUser.getNickname());
        loginregister.setUserPassword(DigestUtils.md5DigestAsHex(repassword.getBytes()));
        loginregister.setUserPhone(phone);
        loginregister.setUserType("普通用户");
        loginregister.setUserStatus(1);
        loginregister.setRegisterTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        boolean isAdd = loginRegisterMapper.addUser(loginregister);
        if (isAdd) {
            if (this.updateBindWxOpenid(openid, loginregister.getUserId())) {
                try {
                    Loginregister user = loginRegisterMapper.getLoginregisterByID(loginregister.getUserId());
                    ObjectMapper jsonParse = new ObjectMapper();
                    String jwt = JwtUtils.createJWT(user.getUserId(), jsonParse.writeValueAsString(user), JwtUtils.TTLMILLIS);// 签发令牌
                    return R.ok("注册绑定成功").put("token", jwt);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    return R.ok("注册绑定成功，将跳转到登录页面");
                }
            } else {
                return R.error("注册绑定失败");
            }
        } else {
            return R.error("注册绑定失败");
        }
    }

    public R registerUserInfo(Map<String, Object> data) {
        if (loginRegisterMapper.registerUserInfo(data)) {
            data.put("status", "认证用户");
            if (loginRegisterMapper.updateStatus(data)) {
                Loginregister user = loginRegisterMapper.getLoginregisterByID((String) data.get("userID"));
                ObjectMapper jsonParse = new ObjectMapper();
                try {
                    return R.ok().put("token", JwtUtils.createJWT(user.getUserId(), jsonParse.writeValueAsString(user), JwtUtils.TTLMILLIS));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }
        return R.error("认证失败");
    }
}
