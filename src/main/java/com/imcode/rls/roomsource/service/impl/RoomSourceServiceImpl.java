package com.imcode.rls.roomsource.service.impl;

import com.imcode.common.model.R;
import com.imcode.rls.roomsource.mapper.RoomSourceMapper;
import com.imcode.rls.roomsource.model.RoomSource;
import com.imcode.rls.roomsource.service.IRoomSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RoomSourceServiceImpl implements IRoomSourceService {

    @Autowired
    private RoomSourceMapper roomSourceMapper;

    public List<RoomSource> getRoomSourceList() {
        return roomSourceMapper.getRoomSourceList();
    }

    public boolean updateRoomSource(Map<String, String> data) {

        // 获取参数
        String roomNO = data.get("roomNO");
        String roomAddress = data.get("roomAddress");
        String roomArea = data.get("roomArea");
        String roomPrice = data.get("roomPrice");
        String roomStatus = data.get("roomStatus");
        // 校验
        if (roomNO == null || roomNO.equals("") || roomAddress == null || roomAddress.equals("")
                || roomArea == null || roomArea.equals("") || roomPrice == null || roomPrice.equals("")
                || roomStatus == null || roomStatus.equals("")) {
            return false;
        }
        // 校验面积是否是数字
        if (!roomArea.matches("^\\d+.?\\d{0,2}$")) {
            return false;
        }
        // 校验单价是否是数字
        if (!roomPrice.matches("^\\d+.?\\d{0,2}$")) {
            return false;
        }
        return roomSourceMapper.updateRoomSource(data);
    }

    public boolean deleteRoomSource(String roomNO) {
        if (roomNO == null || roomNO.equals("")) {
            return false;
        }
        return roomSourceMapper.deleteRoomSource(roomNO);
    }

    public R getRoomSourceAll() {
        return R.ok().put("data",roomSourceMapper.getRoomSourceList());
    }

    public R addRoomSource(Map<String, String> map) {

        R json = null;
        Date date = new Date();
        map.put("roomNO",date.getTime()+"");

        if (roomSourceMapper.addRoomSource(map)) {
            json = R.ok("添加成功");
        } else {
            json = R.error("添加失败");
        }
        return json;
    }

    public R getRoomSourceByRoomNO(String roomNO) {
        return R.ok().put("data",roomSourceMapper.getRoomSourceByRoomNO(roomNO));
    }
}
