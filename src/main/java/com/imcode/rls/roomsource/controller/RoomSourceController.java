package com.imcode.rls.roomsource.controller;


import com.imcode.common.model.R;
import com.imcode.rls.roomsource.model.RoomSource;
import com.imcode.rls.roomsource.service.IRoomSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RoomSourceController {

    @Autowired
    private IRoomSourceService roomSourceServiceImpl;

    @GetMapping("/getRoomList")
    public R getRoomList() {
        R json = R.ok();
        List<RoomSource> roomSourceList = roomSourceServiceImpl.getRoomSourceList();
        json.put("msg", "请求成功");
        json.put("data", roomSourceList);
        return json;
    }

    @GetMapping("/getRoomSourceAll")
    public R getRoomSourceAll() {
        return getRoomList();
    }

    @PostMapping("/RoomSource/edit")
    public R editRoomSource(@RequestBody Map<String, String> data) {
        R json;
        boolean isOK = roomSourceServiceImpl.updateRoomSource(data);
        if (isOK) {
            // 更新成功
            json = R.ok();
            json.put("msg", "更新成功！！");
        } else {
            // 更新失败
            json = R.error("更新失败！！");
        }
        return json;
    }

    @PostMapping("/RoomSource/delete")
    public R deleteRoomSource(@RequestBody Map<String, String> map) {
        R json;
        boolean roomNO = roomSourceServiceImpl.deleteRoomSource(map.get("roomNO"));
        if (roomNO) {
            json = R.ok().put("msg","删除成功！！");
        } else {
            json = R.error("删除失败");
        }
        return json;
    }

}
