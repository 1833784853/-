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
}
