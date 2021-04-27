package com.imcode.rls.roomsource.mapper;

import com.imcode.rls.roomsource.model.RoomSource;
import com.imcode.rls.user.model.Loginregister;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface RoomSourceMapper {
    List<RoomSource> getRoomSourceList();
    List<Loginregister> getLoginregister();
    RoomSource getRoomSource(String roomNO);
    boolean updateRoomSource(Map<String,String> roomSource);
    boolean deleteRoomSource(String roomNO);

    boolean addRoomSource(Map<String, String> map);

    List<RoomSource> getRoomSourceByRoomNO(String roomNO);

    //..租客查询空闲列表
    List<RoomSource> getRoomSourceByStatus(HashMap<String, Object> map);

    //根据状态查询
    List<RoomSource> getRoomSelectBystatu(String roomStatus);
}
