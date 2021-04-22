package com.imcode.rls.roomlease.mapper;

import com.imcode.rls.roomlease.model.RoomLeaseList;
import com.imcode.rls.roomsource.model.RoomSource;
import com.imcode.rls.user.model.Loginregister;

import java.util.HashMap;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface RoomLeaseListMapper {
    List<RoomLeaseList> getRoomLeaseList();
    List<Loginregister> getLoginregister();
    List<RoomSource> getRoomSourceList();

    //    管理员查询所有的已退租列表
    List<RoomLeaseList> getAllRoomLeaseByWithout();

    //    管理员查询所有的空闲列表
//根据roomNO查询
    List<RoomLeaseList> getRoomLeaseListByRoomNO(String roomNO);

    //    管理员查询所有的在租列表
    List<RoomLeaseList> getAllRoomLeaseByRent();

    //租客查询自己已退租列表
    List<RoomLeaseList> getRoomLeaseByWithout(String userID);

    //租客查询自己在租列表
    List<RoomLeaseList> getRoomLeaseByRent(String userID);

    //租客查询在租（已同意）列表
    List<RoomLeaseList> selectBystatu(HashMap<String, Object> map);

    //..管理员查询在租（已同意）列表
    List<RoomLeaseList> cselectBystatu(HashMap<String, Object> map);

    //..管理员新增合同
    boolean addContract(Map<String, String> map);

    //查看合同
    RoomLeaseList getContractURL(Integer roomListID);

    //修改
    boolean updateRoomLeaseList(Map<String, Object> RoomLeaseList);

    //删除已退租列表
    //删除
    boolean deleteRoomLeaseList(int roomListID);

    //新增
    boolean addcontractURL(Map<String,String> RoomLeaseList);


}
