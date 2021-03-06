package com.imcode.rls.user.controller;

import com.imcode.common.model.R;
import com.imcode.rls.user.model.Userjurisdictionmenu;
import com.imcode.rls.user.service.IUserJurisdictionMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HomeController {

    @Autowired
    private IUserJurisdictionMenuService userJurisdictionMenuServiceImpl;

    @GetMapping(value = "/getSideMenuScope",params = "userType")
    public R getSideMenuScope(String userType) throws UnsupportedEncodingException {
        R json = R.ok();
        userType = new String(userType.getBytes("iso-8859-1"),"utf-8");
        System.out.println(userType);
        List<Userjurisdictionmenu> menu = userJurisdictionMenuServiceImpl.getUserJurisdictionMenu(userType);
        json.put("data",menu);
        return json;
    }

}
