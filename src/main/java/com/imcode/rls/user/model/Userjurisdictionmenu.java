package com.imcode.rls.user.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Userjurisdictionmenu {

  private Integer menuId;
  private String userType;
  private String menuUrl;
  private String menuTitle;
  private String menuIcon;
  private Integer menuParent;


}
