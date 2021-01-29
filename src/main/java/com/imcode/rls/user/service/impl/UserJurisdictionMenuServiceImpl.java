package com.imcode.rls.user.service.impl;

import com.imcode.rls.user.mapper.UserJurisdictionMenuMapper;
import com.imcode.rls.user.model.Userjurisdictionmenu;
import com.imcode.rls.user.service.IUserJurisdictionMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserJurisdictionMenuServiceImpl implements IUserJurisdictionMenuService {

    @Autowired
    private UserJurisdictionMenuMapper userJurisdictionMenuMapper;

    public List<Userjurisdictionmenu> getUserJurisdictionMenu(String userType) {
        return userJurisdictionMenuMapper.getUserJurisdictionMenu(userType);
    }
}
