package com.imcode.rls.roomlease.model;

import com.imcode.rls.roomsource.model.RoomSource;
import com.imcode.rls.userinfo.model.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomLeaseList {
    private Integer roomListID;
    private RoomSource roomNO;
    private UserInfo userID;
    private String contractNO;
    private String contractURL;
    private String contractUser;
    private String status;
}
