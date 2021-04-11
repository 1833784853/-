package com.imcode.rls.roomsource.mapper;

import com.imcode.rls.roomsource.model.RoomSource;
import com.imcode.rls.user.model.Loginregister;

import java.util.List;
import java.util.Map;

public interface RoomSourceMapper {
    List<RoomSource> getRoomSourceList();
    List<Loginregister> getLoginregister();
    RoomSource getRoomSource(String roomNO);
    boolean updateRoomSource(Map<String,String> roomSource);
    boolean deleteRoomSource(String roomNO);

    boolean addRoomSource(Map<String, String> map);
}
