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


    //..根据状态查询
    List<RoomLeaseList> getSelectBystatu(String status);

//    //..租客查询在租（已同意）列表
//    List<RoomLeaseList> selectBystatu(HashMap<String, Object> map);

    //..管理员查询在租（已同意）列表
    List<RoomLeaseList> cselectBystatu(HashMap<String, Object> map);

    //..管理员新增合同
    R addContract(Map<String, String> map);


    //..管理员查询所有的在租列表
    List<RoomLeaseList> getAllRoomLeaseByRent(HashMap<String, Object> map);

    // ..管理员终止合同
    R updateRoomLeaseList(Map<String,Object> map);

    //..管理员查询所有的已退租列表
    List<RoomLeaseList> getAllRoomLeaseByWithout(HashMap<String, Object> map);

    // ..租客查询自己在租列表
    List<RoomLeaseList> getRoomLeaseByRent(HashMap<String, Object> map);

    // ..租客查询自己已退租列表
    List<RoomLeaseList> getRoomLeaseByWithout(HashMap<String, Object> map);

    //删除已退租列表
    boolean deleteRoomLeaseList(int roomListID);

    //..批量删除已退租列表
    boolean batchdeleteRoomLeaseList(List<Integer> roomListID);

    //..（批量）新增合同
    R batchAddContract(Map<String, Object> map);
}













