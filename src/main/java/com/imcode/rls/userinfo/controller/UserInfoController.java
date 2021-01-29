package com.imcode.rls.userinfo.controller;

import com.imcode.common.model.R;
import com.imcode.rls.userinfo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoServiceImpl;


    @PostMapping("/registerUserInfo")
    public R registerUserInfo(@RequestBody Map<String,String> data) {
        if (data == null) return R.error("请求失败，缺少必要参数");
        return userInfoServiceImpl.addUserInfo(data);
    }
}
