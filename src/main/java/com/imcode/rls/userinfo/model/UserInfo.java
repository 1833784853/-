package com.imcode.rls.userinfo.model;

import com.imcode.rls.user.model.Loginregister;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private Integer id;
    private Loginregister userID;
    private String userName;
    private String userMail;
    private String userCard;
}
