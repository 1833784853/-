package com.imcode.rls.roomsource.service;

import com.imcode.rls.roomlease.model.RoomLeaseList;
import com.imcode.common.model.R;
import com.imcode.rls.roomsource.model.RoomSource;

import java.util.List;
import java.util.Map;

public interface IRoomSourceService {
    List<RoomSource> getRoomSourceList();
    boolean updateRoomSource(Map<String,String> roomSource);
    boolean deleteRoomSource(String roomNO);
    boolean insertRoomSource(Map<String,String> roomSource);


    R getRoomSourceAll();

    R addRoomSource(Map<String, String> map);
}
