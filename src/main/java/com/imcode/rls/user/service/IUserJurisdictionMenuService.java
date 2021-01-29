package com.imcode.rls.user.service;

import com.imcode.rls.user.model.Userjurisdictionmenu;

import java.util.List;

public interface IUserJurisdictionMenuService {
    List<Userjurisdictionmenu> getUserJurisdictionMenu(String userType);
}
