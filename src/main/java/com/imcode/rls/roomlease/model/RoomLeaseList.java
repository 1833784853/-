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
    private String contractURL;
    private String contractUser;
    private String status;

    public Apply getApplyID() {
        return applyID;
    }

    public void setApplyID(Apply applyID) {
        this.applyID = applyID;
    }

    public Integer getRoomListID() {
        return roomListID;
    }

    public void setRoomListID(Integer roomListID) {
        this.roomListID = roomListID;
    }

    public RoomSource getRoomNO() {
        return roomNO;
    }

    public void setRoomNO(RoomSource roomNO) {
        this.roomNO = roomNO;
    }

    public Loginregister getUserID() {
        return userID;
    }

    public void setUserID(Loginregister userID) {
        this.userID = userID;
    }

    public String getContractNO() {
        return contractNO;
    }

    public void setContractNO(String contractNO) {
        this.contractNO = contractNO;
    }

    public String getContractURL() {
        return contractURL;
    }

    public void setContractURL(String contractURL) {
        this.contractURL = contractURL;
    }

    public String getContractUser() {
        return contractUser;
    }

    public void setContractUser(String contractUser) {
        this.contractUser = contractUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
