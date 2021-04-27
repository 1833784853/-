package com.imcode.rls.roomsource.service;

import com.imcode.common.model.R;
import com.imcode.rls.roomsource.model.RoomSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IRoomSourceService {
    List<RoomSource> getRoomSourceList();
    boolean updateRoomSource(Map<String,String> roomSource);
    boolean deleteRoomSource(String roomNO);


    R getRoomSourceAll();

    R addRoomSource(Map<String, String> map);

    R getRoomSourceByRoomNO(String roomNO);

    //..租客查询空闲列表
    List<RoomSource> getRoomSourceByStatus(HashMap<String, Object> map);

    //..根据状态查询
    List<RoomSource> getRoomSelectBystatu(String roomStatus);
}
