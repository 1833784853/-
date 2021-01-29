package com.imcode.rls.userinfo.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imcode.common.model.JwtUtils;
import com.imcode.common.model.R;
import com.imcode.rls.user.mapper.LoginregisterMapper;
import com.imcode.rls.user.model.Loginregister;
import com.imcode.rls.userinfo.mapper.UserInfoMapper;
import com.imcode.rls.userinfo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private LoginregisterMapper loginregisterMapper;

    public R addUserInfo(Map<String, String> map) {

        String userID = map.get("userID");
        String username = map.get("username");
        String useremail = map.get("useremail");
        String card = map.get("card");

        if (userID == null || userID.trim().equals("")) {
            return R.error("缺少必要参数,请刷新重试");
        }

        if (username == null || username.trim().equals("")) {
            return R.error("请输入姓名!");
        }

        if (!username.matches("^[a-zA-Z\u4e00-\u9fa5]{2,10}$")) {
            return R.error("请正确输入姓名（英文或中文）!");
        }

        if (useremail == null || useremail.trim().equals("")) {
            return R.error("请输入邮箱!");
        }
        if (!useremail.matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$")) {
            return R.error("请输入正确合法的邮箱格式!");
        }
        if (card == null || card.trim().equals("")) {
            return R.error("请输入身份证号!");
        }

        if (!card.matches("^(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)|(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[Xx])$)$")) {
            return R.error("请输入正确合法的身份证号码！");
        }
        HashMap<String, String> userInfo = new HashMap<String, String>();
        userInfo.put("userID", userID);
        userInfo.put("userName", username);
        userInfo.put("userMail", useremail);
        userInfo.put("userCard", card);
        userInfo.put("userType", "认证用户");
        if (userInfoMapper.addUserInfo(userInfo)) {
            loginregisterMapper.updateUserType(userInfo);
            try {
                Loginregister user = loginregisterMapper.getLoginregisterByID(userID);
                ObjectMapper jsonParse = new ObjectMapper();
                String jwt = JwtUtils.createJWT(user.getUserId(), jsonParse.writeValueAsString(user), JwtUtils.TTLMILLIS);// 签发令牌
                return R.ok("认证成功").put("token", jwt);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return R.error("获取数据失败");
            }
        } else {
            return R.error("认证失败，请联系管理员");
        }
    }
}
