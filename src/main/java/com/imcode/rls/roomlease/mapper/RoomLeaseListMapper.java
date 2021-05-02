package com.imcode.rls.roomlease.mapper;

import com.imcode.common.model.R;
import com.imcode.rls.roomapply.model.Apply;
import com.imcode.rls.roomlease.model.RoomLeaseList;
import com.imcode.rls.roomsource.model.RoomSource;
import com.imcode.rls.user.model.Loginregister;

import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface RoomLeaseListMapper {
    List<RoomLeaseList> getRoomLeaseList();
    List<Loginregister> getLoginregister();
    List<RoomSource> getRoomSourceList();

    //..根据状态查询
    List<RoomLeaseList> getSelectBystatu(String status);

    //..管理员查询在租（已同意）列表
    List<RoomLeaseList> cselectBystatu(HashMap<String, Object> map);

    //..管理员新增合同
    boolean addContract(Map<String, String> map);



    //..修改房源状态（已租）
    boolean updateRoomBystatus(Map<String, String> map);

    //..修改申请状态
    boolean updateApplyBystatus(Map<String, String> map);

    //..修改房源状态（未租）
    boolean getupdateRoomBystatus(Map<String, Object> map);

    //..修改房源状态（已租）
    boolean updateRoomByRented(Map<String, Object> map);


    //..批量新增合同
    boolean batchAddContract(ArrayList<HashMap<String,Object>> roomLeaseList);

    //..（批量）修改房源状态（已租）
    boolean batchupdateRoomBystatus(List<Object> roomNO);

    //..（批量）修改申请状态
    boolean batchupdateApplyBystatus(List<Object> roomNO);


    // ..管理员查询所有的在租列表
    List<RoomLeaseList> getAllRoomLeaseByRent(HashMap<String, Object> map);

    //..管理员查询所有的已退租列表
    List<RoomLeaseList> getAllRoomLeaseByWithout(HashMap<String, Object> map);

    // ..管理员同意终止合同
    boolean updateRoomLeaseList(Map<String,Object> map);

    // ..管理员拒绝终止合同
    boolean updaterefuseRoomLease(Map<String,Object> map);

    // ..管理员批量同意终止合同
    boolean updateRoomLeaseLists(List<Map<String,Object>> roomListID);

    // ..管理员批量拒绝终止合同
    boolean updaterefuseRoomLeases(List<Map<String,Object>> roomListID);

    //..批量修改房源状态（未租）
    boolean getupdateRoomBystatusList(List<Map<String,Object>> roomNO);

    //..批量修改房源状态（已租）
    boolean updateRoomByRentedList(List<Map<String,Object>> roomNO);

    // ..租客查询自己在租列表
    List<RoomLeaseList> getRoomLeaseByRent(HashMap<String, Object> map);

    // ..租客查询自己在租列表
    List<RoomLeaseList> getRoomLeaseByWithout(HashMap<String, Object> map);

    //..租客正在退租
    boolean tenantVacating(Map<String, Object> map);

    //..租客正在退租后修改申请状态
    boolean tenantApplyByStatus(Map<String, Object> map);

    //..删除已退租列表
    boolean deleteRoomLeaseList(int roomListID);

    //..批量删除已退租列表
    boolean batchdeleteRoomLeaseList(List<Integer> roomListID);
    //根据applyid查询
    List<Apply> selectById(int applyID);

}
