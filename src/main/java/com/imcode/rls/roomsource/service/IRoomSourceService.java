package com.imcode.rls.roomsource.service;

import com.imcode.common.model.R;
import com.imcode.rls.roomsource.model.RoomSource;

import java.util.List;
import java.util.Map;

public interface IRoomSourceService {
    List<RoomSource> getRoomSourceList();
    boolean updateRoomSource(Map<String,String> roomSource);
    boolean deleteRoomSource(String roomNO);

    R getRoomSourceAll();

    R addRoomSource(Map<String, String> map);
}
