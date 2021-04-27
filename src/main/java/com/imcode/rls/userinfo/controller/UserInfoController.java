package com.imcode.rls.userinfo.controller;

import com.imcode.common.model.R;
import com.imcode.rls.roomapply.model.Apply;
import com.imcode.rls.userinfo.model.UserInfo;
import com.imcode.rls.userinfo.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserInfoController {

    @Autowired
    private IUserInfoService userInfoServiceImpl;

    @GetMapping("/getUserInfo")
    public R getUserInfo(String userID){
        R json = R.ok();
        List<UserInfo> getUserinfo = userInfoServiceImpl.getUserinfo(userID);
        json.put("msg", "请求成功");
        json.put("data", getUserinfo);
        return json;
    }


}
