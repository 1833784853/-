package com.imcode.rls.roomlease.service.impl;

import com.imcode.common.service.FileService;
import com.imcode.rls.roomlease.mapper.RoomLeaseListMapper;
import com.imcode.rls.roomlease.model.RoomLeaseList;
import com.imcode.rls.roomlease.service.IRoomLeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoomLeaseServiceImpl implements IRoomLeaseService {

    @Autowired
    private RoomLeaseListMapper roomLeaseListMapper;

    private FileService fileService;

    public List<RoomLeaseList> getRoomLeaseList() {

        return roomLeaseListMapper.getRoomLeaseList();
    }

    //查看合同
    public RoomLeaseList getContractURL(Integer roomListID){

        return roomLeaseListMapper.getContractURL(roomListID);
    }

    //租客删除已退租列表
    public boolean deleteRoomLeaseList(int roomListID) {
        return roomLeaseListMapper.deleteRoomLeaseList(roomListID);
    }

    //    管理员查询所有的已退租列表
    public List<RoomLeaseList> getAllRoomLeaseByWithout(){
        return roomLeaseListMapper.getAllRoomLeaseByWithout();
    }

    //已同意
    public List<RoomLeaseList> selectBystatu(HashMap<String, Object> map){
        return roomLeaseListMapper.selectBystatu(map);
    }
    //    管理员查询所有的在租列表
    public List<RoomLeaseList> getAllRoomLeaseByRent(){
        return roomLeaseListMapper.getAllRoomLeaseByRent();
    }

    //租客查询自己已退租列表
    public List<RoomLeaseList> getRoomLeaseByWithout (String userID){
        System.out.println(userID);
        return roomLeaseListMapper.getRoomLeaseByWithout(userID);
    }

    //租客查询自己在租列表
    public List<RoomLeaseList> getRoomLeaseByRent (String userID){
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
