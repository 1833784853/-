package com.imcode.rls.roomlease.controller;

import com.imcode.common.model.R;
import com.imcode.common.service.FileService;
import com.imcode.rls.roomlease.model.RoomLeaseList;
import com.imcode.rls.roomlease.service.IRoomLeaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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

    //查询
    @PostMapping("/getRoomLeaseList")
    public R getRoomLeaseList(){
        R json = R.ok();
        List<RoomLeaseList> roomLeaseList = roomLeaseService.getRoomLeaseList();
        json.put("msg", "请求成功");
        json.put("data", roomLeaseList);
        return json;
    }

    //根据roomNO查询
    @PostMapping("/getRoomLeaseListByRoomNO")
    public R getRoomLeaseListByRoomNO(@RequestBody Map<String,String> data){
        R json = R.ok();
        String roomNO = data.get("roomNO");
        List<RoomLeaseList> RoomLeaseListByRoomNO = roomLeaseService.getRoomLeaseListByRoomNO(roomNO);
        json.put("msg", "请求成功");
        json.put("data", RoomLeaseListByRoomNO);
        return json;
    }

//    管理员查询所有的已退租列表
    @PostMapping("/getAllRoomLeaseByWithout")
    public R getAllRoomLeaseByWithout(){
        R json = R.ok();
        List<RoomLeaseList> selectByStatus = roomLeaseService.getAllRoomLeaseByWithout();
        json.put("msg", "请求成功");
        json.put("data", selectByStatus);
        return json;
    }

    //    管理员查询所有的在租列表
    @PostMapping("/getAllRoomLeaseByRent")
    public R getAllRoomLeaseByRent(){
        R json = R.ok();
        List<RoomLeaseList> selectByStatus = roomLeaseService.getAllRoomLeaseByRent();
        json.put("msg", "请求成功");
        json.put("data", selectByStatus);
        return json;
    }


    //租客查询自己已退租列表
    @PostMapping("/getRoomLeaseByWithout")
    public R getRoomLeaseByWithout(@RequestBody Map<String,String> data){
        R json = R.ok();
        String userID = data.get("userID");
        List<RoomLeaseList> selectByStatus = roomLeaseService.getRoomLeaseByWithout(userID);
        json.put("msg", "请求成功");
        json.put("data", selectByStatus);
        return json;
    }

    //租客查询自己在租列表
    @PostMapping("/getRoomLeaseByRent")
    public R getRoomLeaseByRent(@RequestBody Map<String,String> data){
        R json = R.ok();
        String userID = data.get("userID");
        List<RoomLeaseList> selectByStatus = roomLeaseService.getRoomLeaseByRent(userID);
        json.put("msg", "请求成功");
        json.put("data", selectByStatus);
        return json;
    }

    //查询合同（获取图片路径）
    @PostMapping("/getContractURL")
    public R getContractURL(Integer roomListID){
        R json = R.ok();
        RoomLeaseList roomLeaseList = roomLeaseService.getContractURL(roomListID);
        json.put("msg", "请求成功");
        json.put("data", roomLeaseList);
        return json;
    }

    //终止合同之后的修改
    @PostMapping("/RoomLeaseList/update")
    public R updateRoomLeaseList(@RequestBody Map<String, Object> data) {
        R json;
        boolean isOK = roomLeaseService.updateRoomLeaseList(data);
        if (isOK) {
            json = R.ok();
            json.put("msg", "更新成功！！");
        } else {
            json = R.error("更新失败！！");
        }
        return json;
    }

//    @PostMapping("/RoomLeaseList/add")
//    public String insertRoomSource(RoomLeaseList roomLeaseList, @RequestParam("multipartFile")MultipartFile multipartFile){
//        try {
//            fileService.upload(multipartFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//





//    //新增
   @PostMapping("/RoomLeaseList/add")
    public R insertRoomSource(Map<String, String> data, MultipartFile uploadFile) throws IOException {
        R json;
        System.out.println(data);
        R path;
        path = fileService.upload(uploadFile);
        if (path != null){
            data.put("contractURL", String.valueOf(path));
            System.out.println("图片上传成功");
        }else {
            System.out.println("图片上传失败");
        }
        boolean isOK = roomLeaseService.addcontractURL(data);
        if (isOK){
            json = R.ok();
            json.put("msg", "添加成功！！!");
        }else {
            json = R.error("添加失败！！！");
        }
    return json;
}
    //删除
    @PostMapping("/RoomLeaseList/delete")
    public R deleteRoomLeaseList(@RequestBody Map<String, Integer> map) {
        R json;
        boolean roomListID = roomLeaseService.deleteRoomLeaseList(map.get("roomListID"));
        if (roomListID) {
            json = R.ok().put("msg","删除成功！！");
        } else {
            json = R.error("删除失败");
        }
        return json;
    }

}


