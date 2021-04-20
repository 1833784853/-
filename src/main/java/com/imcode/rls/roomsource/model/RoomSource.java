package com.imcode.rls.roomsource.model;


import com.imcode.rls.user.model.Loginregister;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 房源信息类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomSource {
    private int roomID; // 每条记录的id
    private String roomNO; // 房源编号
    private String roomAddress; // 房源地址
    private double roomArea; // 房源的面积
    private double roomPrice; // 房源单价
    private String roomStatus; // 房源状态（在租、已租）
    private String roomTime; // 记录创建时间
    private String roomLatelyTime; // 最后一次操作更新的时间
    private int roomDeleteStatus; // 房源是否上线，默认上线（1）
    private Loginregister user; // 用户信息

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getRoomNO() {
        return roomNO;
    }

    public void setRoomNO(String roomNO) {
        this.roomNO = roomNO;
    }

    public String getRoomAddress() {
        return roomAddress;
    }

    public void setRoomAddress(String roomAddress) {
        this.roomAddress = roomAddress;
    }

    public double getRoomArea() {
        return roomArea;
    }

    public void setRoomArea(double roomArea) {
        this.roomArea = roomArea;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    public String getRoomTime() {
        return roomTime;
    }

    public void setRoomTime(String roomTime) {
        this.roomTime = roomTime;
    }

    public String getRoomLatelyTime() {
        return roomLatelyTime;
    }

    public void setRoomLatelyTime(String roomLatelyTime) {
        this.roomLatelyTime = roomLatelyTime;
    }

    public int getRoomDeleteStatus() {
        return roomDeleteStatus;
    }

    public void setRoomDeleteStatus(int roomDeleteStatus) {
        this.roomDeleteStatus = roomDeleteStatus;
    }

    public Loginregister getUser() {
        return user;
    }

    public void setUser(Loginregister user) {
        this.user = user;
    }
}
