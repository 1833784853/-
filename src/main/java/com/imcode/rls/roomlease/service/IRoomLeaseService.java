package com.imcode.rls.roomlease.service;

import com.imcode.common.model.R;
import com.imcode.rls.roomlease.model.RoomLeaseList;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IRoomLeaseService {

    //查询全部
    List<RoomLeaseList> getRoomLeaseList();


    //..租客查询在租（已同意）列表
    List<RoomLeaseList> selectBystatu(HashMap<String, Object> map);

    //..管理员查询在租（已同意）列表
    List<RoomLeaseList> cselectBystatu(HashMap<String, Object> map);

    //..管理员新增合同
    R addContract(Map<String, String> map);

    //..管理员查询所有的在租列表
    List<RoomLeaseList> getAllRoomLeaseByRent(HashMap<String, Object> map);


    //租客删除已退租列表
    //根据roomNO查询
    List<RoomLeaseList> getRoomLeaseListByRoomNO(String roomNO);

    //删除
    boolean deleteRoomLeaseList(int roomListID);

    //合同信息
    RoomLeaseList getContractURL(Integer roomListID);

    //    管理员查询所有的已退租列表
    List<RoomLeaseList> getAllRoomLeaseByWithout();



    //租客查询自己已退租列表
    List<RoomLeaseList> getRoomLeaseByWithout (String userID);

    //租客查询自己在租列表
    List<RoomLeaseList> getRoomLeaseByRent(String userID);



    //终止合同之后的修改
    boolean updateRoomLeaseList(Map<String,Object> RoomLeaseList);

    //新增
    boolean addcontractURL(Map<String,String> RoomLeaseList);
}













