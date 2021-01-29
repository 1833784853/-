package com.imcode.rls.user.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loginregister {
  private String userId;
  private String userName;
  private String userPassword;
  private String userPhone;
  private String userType;
  private Integer userStatus;
  private String registerTime;
  private WxUser wxOpenid;
}
