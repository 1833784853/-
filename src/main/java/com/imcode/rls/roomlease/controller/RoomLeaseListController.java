package com.imcode.rls.roomlease.controller;

import com.alibaba.fastjson.JSON;
import com.imcode.common.model.R;
import com.imcode.common.service.FileService;
import com.imcode.rls.roomapply.service.ApplyService;
import com.imcode.rls.roomlease.model.RoomLeaseList;
import com.imcode.rls.roomlease.service.IRoomLeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RoomLeaseListController {

    @Qualifier("roomLeaseServiceImpl")
    @Autowired
    private IRoomLeaseService roomLeaseService;

    private FileService fileService;

    private RoomLeaseList roomLeaseList;

    @Autowired
    private ApplyService applyServiceimpl;

    //查询
    @PostMapping("/getRoomLeaseList")
    public R getRoomLeaseList(){
        R json = R.ok();
        List<RoomLeaseList> roomLeaseList = roomLeaseService.getRoomLeaseList();
        json.put("msg", "请求成功");
        json.put("data", roomLeaseList);
        return json;
    }
//
//    //..租客查询已同意（在租）列表
//    @PostMapping("/selectBystatu")
//    public R selectBystatu(@RequestBody HashMap<String, Object> map) {
//        R json = R.ok();
//        List<RoomLeaseList> roomLeaseList = roomLeaseService.selectBystatu(map);
//        json.put("msg", "请求成功");
//        json.put("data", roomLeaseList);
//        return json;
//    }

    //..管理员查询已同意（在租）列表
    @PostMapping("/cselectBystatu")
    public R cselectBystatu(@RequestBody HashMap<String, Object> map) {
        R json = R.ok();
        List<RoomLeaseList> roomLeaseList = roomLeaseService.cselectBystatu(map);
        json.put("msg", "请求成功");
        json.put("data", roomLeaseList).put("totalPage",applyServiceimpl.selectid(1).size());
        return json;
    }

    //..管理员新增合同
    @PostMapping("/addRoomLeaseList")
    public R addContract(@RequestBody Map<String,String> map) {
        return roomLeaseService.addContract(map);
    }

    //..(批量)管理员新增合同
    @PostMapping("/batchAddContract")
    public R batchAddContract(@RequestBody Map<String,Object> map) {
//        ArrayList<Map<String,Object>> maps = new ArrayList<>();
//        ((ArrayList<String>) map.get("roomLeaseList")).forEach(item->{
//            maps.add(JSON.parseObject(item,Map.class));
//        });
//        System.out.println(maps);
        return roomLeaseService.batchAddContract(map);
    }

    // ..管理员查询所有的在租列表
    @PostMapping("/getAllRoomLeaseByRent")
    public R getAllRoomLeaseByRent(@RequestBody HashMap<String, Object> map){
        R json = R.ok();
        List<RoomLeaseList> selectByStatus = roomLeaseService.getAllRoomLeaseByRent(map);
        json.put("msg", "请求成功");
        json.put("data", selectByStatus).put("totalPage",roomLeaseService.getSelectBystatu("在租").size());
        return json;
    }

    // ..管理员查询所有的已退租列表
    @PostMapping("/getAllRoomLeaseByWithout")
    public R getAllRoomLeaseByWithout(@RequestBody HashMap<String, Object> map){
        R json = R.ok();
        List<RoomLeaseList> selectByStatus = roomLeaseService.getAllRoomLeaseByWithout(map);
        json.put("msg", "请求成功");
        json.put("data", selectByStatus).put("totalPage",roomLeaseService.getSelectBystatu("已退租").size());
        return json;
    }

    // ..管理员终止合同
    @PostMapping("/updateRoomLeaseList")
    public R updateRoomLeaseList(@RequestBody Map<String,Object> map) {
        return roomLeaseService.updateRoomLeaseList(map);
    }

    // ..租客在租列表
    @PostMapping("/getRoomLeaseByRent")
    public R getRoomLeaseByRent(@RequestBody HashMap<String, Object> map){
        R json = R.ok();
        List<RoomLeaseList> selectByStatus = roomLeaseService.getRoomLeaseByRent(map);
        json.put("msg", "请求成功");
        json.put("data", selectByStatus).put("totalPage",roomLeaseService.getSelectBystatu("在租").size());
        return json;
    }

    // ..租客已退租列表
    @PostMapping("/getRoomLeaseByWithout")
    public R getRoomLeaseByWithout(@RequestBody HashMap<String, Object> map){
        R json = R.ok();
        List<RoomLeaseList> selectByStatus = roomLeaseService.getRoomLeaseByWithout(map);
        json.put("msg", "请求成功");
        json.put("data", selectByStatus).put("totalPage",roomLeaseService.getSelectBystatu("已退租").size());
        return json;
    }

    //..删除已退租列表
    @PostMapping("/deleteRoomLeaseList")
    public R deleteRoomLeaseList(@RequestBody Map<String,Integer> roomListID){
        R json;
        boolean flag=roomLeaseService.deleteRoomLeaseList(roomListID.get("roomListID"));
        if(flag){
            json=R.ok().put("msg","删除成功！");
        }else {
            json=R.error("删除失败！");
        }
        return json;
    }

    //..批量删除已退租列表
    @PostMapping("/batchdeleteRoomLeaseList")
    public R batchdeleteRoomLeaseList(@RequestBody Map<String,Object> roomListID){
        List<Integer> roomListIDs=(List<Integer>)roomListID.get("roomListID");
        R json;
        boolean flag=roomLeaseService.batchdeleteRoomLeaseList(roomListIDs);
        if(flag){
            json=R.ok().put("msg","删除成功！");
        }else {
            json=R.error("删除失败！");
        }
        return json;
    }
}


