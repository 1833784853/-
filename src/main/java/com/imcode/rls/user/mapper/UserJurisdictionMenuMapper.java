package com.imcode.rls.user.mapper;

import com.imcode.rls.user.model.Userjurisdictionmenu;

import java.util.List;

public interface UserJurisdictionMenuMapper {
    List<Userjurisdictionmenu> getUserJurisdictionMenu(String userType);
}
