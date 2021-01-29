package com.imcode.rls.user.service.impl;

import com.imcode.rls.user.mapper.LoginregisterMapper;
import com.imcode.rls.user.model.Loginregister;
import com.imcode.rls.user.model.WxUser;
import com.imcode.rls.user.service.IWxLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxLoginServiceImpl implements IWxLoginService {

    @Autowired
    private LoginregisterMapper loginregisterMapper;

    public Loginregister getLoginregisterWxUser(String wxOpenid) {
        return loginregisterMapper.getOpenidUser(wxOpenid);
    }

    public WxUser getWxUser(String wxOpenid) {
        return loginregisterMapper.getWxUser(wxOpenid);
    }

    public boolean addWxUser(WxUser wxUser) {
        return loginregisterMapper.addWxUser(wxUser);
    }
}
