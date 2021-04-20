package com.imcode.rls.roomlease.service;

import com.imcode.rls.roomlease.model.RoomLeaseList;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IRoomLeaseService {

    //查询全部
    List<RoomLeaseList> getRoomLeaseList();

    //租客删除已退租列表
    boolean deleteRoomLeaseList(int roomListID);

    //合同信息
    RoomLeaseList getContractURL(Integer roomListID);

    //    管理员查询所有的已退租列表
    List<RoomLeaseList> getAllRoomLeaseByWithout();

    //    管理员查询所有的在租列表
    List<RoomLeaseList> getAllRoomLeaseByRent();

    //租客查询自己已退租列表
    List<RoomLeaseList> getRoomLeaseByWithout (String userID);

    //租客查询自己在租列表
    List<RoomLeaseList> getRoomLeaseByRent(String userID);

    //已同意
    List<RoomLeaseList> selectBystatu(HashMap<String, Object> map);

    //终止合同之后的修改
    boolean updateRoomLeaseList(Map<String,Object> RoomLeaseList);

    //新增
    boolean addcontractURL(Map<String,String> RoomLeaseList);
}













