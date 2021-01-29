package com.imcode.rls.user.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imcode.common.model.HttpClient;
import com.imcode.common.model.JwtUtils;
import com.imcode.rls.user.model.Loginregister;
import com.imcode.rls.user.model.WxToken;
import com.imcode.rls.user.model.WxUser;
import com.imcode.rls.user.service.IWxLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api")
public class WxLoginController {

    @Autowired
    private IWxLoginService wxLoginServiceImpl;

    @RequestMapping("/wxLogin")
    public String wxLogin(HttpServletRequest request, ModelAndView modelAndView) {
        String code = request.getParameter("code");
        if (code == null) return "redirect:/#/login/common";
        else {
            String tokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                    "appid=wx7287a60bb700fd21&secret=1ef8755f92bebae8ad7bab432ba29cbf&code=" + code + "&grant_type=authorization_code";
            try {
                HttpClient httpClient = new HttpClient(tokenUrl);

                httpClient.get();
                String token_content = httpClient.getContent();

                WxToken wxToken = JSON.parseObject(token_content, WxToken.class);

                String user_url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + wxToken.getAccess_token() + "&openid=" + wxToken.getOpenid();
                httpClient = new HttpClient(user_url);

                httpClient.get();
                String user_content = httpClient.getContent();
                WxUser wxUser = JSON.parseObject(user_content, WxUser.class);

                String openid = wxUser.getOpenid();

                Loginregister user = wxLoginServiceImpl.getLoginregisterWxUser(openid);

                if (wxLoginServiceImpl.getWxUser(openid) == null) {
                    // 数据库没有该信息
                    wxLoginServiceImpl.addWxUser(wxUser);
                }

                if (user == null) {
                    // 未绑定该信息
                    return "redirect:/#/login/WxRegister/"+openid;
                } else {
                    ObjectMapper jsonParse = new ObjectMapper();
                    String jwt = JwtUtils.createJWT(user.getUserId(), jsonParse.writeValueAsString(user), JwtUtils.TTLMILLIS);// 签发令牌
                    return "redirect:/#/home/"+jwt;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "redirect:/#/login/common";
            }
        }
    }
}
