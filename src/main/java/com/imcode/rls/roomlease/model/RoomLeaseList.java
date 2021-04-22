package com.imcode.rls.roomlease.model;

import com.imcode.rls.roomapply.model.Apply;
import com.imcode.rls.roomsource.model.RoomSource;
import com.imcode.rls.user.model.Loginregister;
import com.imcode.rls.userinfo.model.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomLeaseList {
    private Integer roomListID;
    private RoomSource roomNO;
    private Loginregister userID;
    private Apply applyID;
    private String contractNO;
    private String contractTime;
    private String contractUser;
    private String status;
}
