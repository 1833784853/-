package com.imcode.rls.roomlease.mapper;

import com.imcode.rls.roomlease.model.RoomLeaseList;
import com.imcode.rls.roomsource.model.RoomSource;
import com.imcode.rls.user.model.Loginregister;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface RoomLeaseListMapper {
    List<RoomLeaseList> getRoomLeaseList();
    List<Loginregister> getLoginregister();
    List<RoomSource> getRoomSourceList();

//根据roomNO查询
    List<RoomLeaseList> getRoomLeaseListByRoomNO(String roomNO);

    //    管理员查询所有的已退租列表
    List<RoomLeaseList> getAllRoomLeaseByWithout();

    //    管理员查询所有的在租列表
    List<RoomLeaseList> getAllRoomLeaseByRent();

    //租客查询自己已退租列表
    List<RoomLeaseList> getRoomLeaseByWithout(String userID);

    //租客查询自己在租列表
    List<RoomLeaseList> getRoomLeaseByRent(String userID);

    //查看合同
    RoomLeaseList getContractURL(Integer roomListID);

    //修改
    boolean updateRoomLeaseList(Map<String, Object> RoomLeaseList);

    //删除
    boolean deleteRoomLeaseList(int roomListID);

    //新增
    boolean addcontractURL(Map<String,String> RoomLeaseList);


}
