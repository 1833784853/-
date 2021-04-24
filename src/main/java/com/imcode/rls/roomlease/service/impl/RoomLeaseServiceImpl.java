package com.imcode.rls.roomlease.service.impl;

import com.imcode.common.model.R;
import com.imcode.common.service.FileService;
import com.imcode.rls.roomlease.mapper.RoomLeaseListMapper;
import com.imcode.rls.roomlease.model.RoomLeaseList;
import com.imcode.rls.roomlease.service.IRoomLeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoomLeaseServiceImpl implements IRoomLeaseService {

    @Autowired
    private RoomLeaseListMapper roomLeaseListMapper;

    private FileService fileService;

    //根据roomNO查询
    public List<RoomLeaseList> getRoomLeaseListByRoomNO(String roomNO){
        return roomLeaseListMapper.getRoomLeaseListByRoomNO(roomNO);
    }

    //..租客查询在租（申请已同意）列表
    public List<RoomLeaseList> selectBystatu(HashMap<String, Object> map){
        return roomLeaseListMapper.selectBystatu(map);
    }

    //..管理员查询在租（申请已同意）列表
    public List<RoomLeaseList> cselectBystatu(HashMap<String, Object> map) {
        map.put("currentPage",(Integer)map.get("currentPage")*(Integer)map.get("pageSize"));
        return roomLeaseListMapper.cselectBystatu(map);
    }

    //..管理员新增合同
    public R addContract(Map<String, String> map) {

        R json = null;
        Date date = new Date();
        map.put("contractNO",date.getTime()+"");

        if (roomLeaseListMapper.addContract(map)) {
            roomLeaseListMapper.updateRoomBystatus(map);
            roomLeaseListMapper.updateApplyBystatus(map);
            json = R.ok("添加成功");
        } else {
            json = R.error("添加失败");
        }
        return json;
    }

    //..管理员查询所有的在租列表
    public List<RoomLeaseList> getAllRoomLeaseByRent(HashMap<String, Object> map){
        map.put("currentPage",(Integer)map.get("currentPage")*(Integer)map.get("pageSize"));
        return roomLeaseListMapper.getAllRoomLeaseByRent(map);
    }




    public List<RoomLeaseList> getRoomLeaseList() {

        return roomLeaseListMapper.getRoomLeaseList();
    }

    //查看合同
    public RoomLeaseList getContractURL(Integer roomListID){

        return roomLeaseListMapper.getContractURL(roomListID);
    }

    //租客删除已退租列表
    //删除
    public boolean deleteRoomLeaseList(int roomListID) {
        return roomLeaseListMapper.deleteRoomLeaseList(roomListID);
    }

    //    管理员查询所有的已退租列表
    public List<RoomLeaseList> getAllRoomLeaseByWithout(){
        return roomLeaseListMapper.getAllRoomLeaseByWithout();
    }






    //租客查询自己已退租列表
    public List<RoomLeaseList> getRoomLeaseByWithout (String userID){
//        if (status == null || status.equals("")){
//            return null;
//        }
        System.out.println(userID);
        return roomLeaseListMapper.getRoomLeaseByWithout(userID);
    }

    //租客查询自己在租列表
    public List<RoomLeaseList> getRoomLeaseByRent (String userID){
//        if (status == null || status.equals("")){
//            return null;
//        }
        System.out.println(userID);
        return roomLeaseListMapper.getRoomLeaseByRent(userID);
    }

    //终止合同之后的修改
    public boolean updateRoomLeaseList(Map<String, Object> data){

        return roomLeaseListMapper.updateRoomLeaseList(data);
    }


    //新增
    public boolean addcontractURL(Map<String, String> data) {
        // 获取参数
        String roomNO = data.get("roomNO");
        String userID = data.get("userID");
        String contractNO = data.get("contractNO");
        String contractURL = data.get("contractURL");
        String contractUser = data.get("contractUser");

        // 校验
        if (roomNO == null || roomNO.equals("") || userID == null || userID.equals("")
                || contractNO == null || contractNO.equals("") || contractURL == null || contractURL.equals("")
                || contractUser == null || contractUser.equals("")) {
            return false;
        }
        return roomLeaseListMapper.addcontractURL(data);

    }


}
