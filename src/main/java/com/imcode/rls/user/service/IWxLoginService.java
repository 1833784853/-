package com.imcode.rls.user.service;

import com.imcode.rls.user.model.Loginregister;
import com.imcode.rls.user.model.WxUser;

public interface IWxLoginService {
    Loginregister getLoginregisterWxUser(String wxOpenid);

    WxUser getWxUser(String wxOpenid);

    boolean addWxUser(WxUser wxUser);
}
