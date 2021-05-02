package com.imcode.rls.roomlease.service.impl;

import com.aliyuncs.utils.StringUtils;
import com.imcode.common.model.R;
import com.imcode.common.service.FileService;
import com.imcode.rls.roomlease.mapper.RoomLeaseListMapper;
import com.imcode.rls.roomlease.model.RoomLeaseList;
import com.imcode.rls.roomlease.service.IRoomLeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;

@Service
public class RoomLeaseServiceImpl implements IRoomLeaseService {

    @Autowired
    private RoomLeaseListMapper roomLeaseListMapper;

    private FileService fileService;

//    //根据roomNO查询
//    public List<RoomLeaseList> getRoomLeaseListByRoomNO(String roomNO){
//        return roomLeaseListMapper.getRoomLeaseListByRoomNO(roomNO);
//    }

    //查询全部
    public List<RoomLeaseList> getRoomLeaseList() {
        return roomLeaseListMapper.getRoomLeaseList();
    }

    //..根据状态查询
    public  List<RoomLeaseList> getSelectBystatu(String status){
        return roomLeaseListMapper.getSelectBystatu(status);
    }

//    //..租客查询在租（申请已同意）列表
//    public List<RoomLeaseList> selectBystatu(HashMap<String, Object> map){
//        return roomLeaseListMapper.selectBystatu(map);
//    }

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
            if(roomLeaseListMapper.updateRoomBystatus(map) && roomLeaseListMapper.updateApplyBystatus(map)){
                json = R.ok("添加成功");
            }
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

    // ..管理员查询已退租列表
    public List<RoomLeaseList> getAllRoomLeaseByWithout(HashMap<String, Object> map) {
        map.put("currentPage",(Integer)map.get("currentPage")*(Integer)map.get("pageSize"));
        return roomLeaseListMapper.getAllRoomLeaseByWithout(map);
    }

    //..管理员终止合同
    public R updateRoomLeaseList(Map<String, Object> map) {
        R json = null;
        if (roomLeaseListMapper.updateRoomLeaseList(map)) {
            if(roomLeaseListMapper.getupdateRoomBystatus(map)){
                json = R.ok("终止合同成功");
            }
        } else {
            json = R.error("终止合同失败");
        }
        return json;
    }

    /*
    管理员拒绝终止合同
     */
    public R updaterefuseRoomLease(Map<String, Object> map) {
        R json=null;
        if(roomLeaseListMapper.updaterefuseRoomLease(map)){
            if(roomLeaseListMapper.updateRoomByRented(map)){
                json=R.ok("拒绝终止合同成功");
            }
        }else {
            json=R.error("拒绝终止合同失败");
        }
        return json;
    }

    /*
    管理员批量终止合同
     */
    public R updateRoomLeaseLists(Map<String,Object> roomListID) {
        List<Map<String,Object>> roomList=(List<Map<String,Object>>)roomListID.get("roomListID");
        List<Map<String,Object>> roomNO=(List<Map<String,Object>>)roomListID.get("roomNO");
        R json=null;
        if(roomLeaseListMapper.updateRoomLeaseLists(roomList)){
            if (roomLeaseListMapper.getupdateRoomBystatusList(roomNO)){
                json=R.ok("终止合同成功");
            }
        }else {
            json=R.error("终止合同失败");
        }
        return json;
    }

    /*
    管理员批量拒绝终止合同
     */
    public R updaterefuseRoomLeases(Map<String,Object> roomListID) {
        List<Map<String,Object>> roomList=(List<Map<String,Object>>)roomListID.get("roomListID");
        List<Map<String,Object>> roomNO=(List<Map<String,Object>>)roomListID.get("roomNO");
        R json=null;
        if(roomLeaseListMapper.updaterefuseRoomLeases(roomList)){
            if(roomLeaseListMapper.updateRoomByRentedList(roomNO)){
                json=R.ok("拒绝终止合同成功");
            }
        }else {
            json=R.error("拒绝终止合同失败");
        }
        return json;
    }

    //..租客查询在租列表
    public List<RoomLeaseList> getRoomLeaseByRent(HashMap<String, Object> map){
        map.put("currentPage",(Integer)map.get("currentPage")*(Integer)map.get("pageSize"));
        return roomLeaseListMapper.getRoomLeaseByRent(map);
    }

    //..租客查询已退租租列表
    public List<RoomLeaseList> getRoomLeaseByWithout(HashMap<String, Object> map){
        map.put("currentPage",(Integer)map.get("currentPage")*(Integer)map.get("pageSize"));
        return roomLeaseListMapper.getRoomLeaseByWithout(map);
    }

    //..删除已退租列表
    public boolean deleteRoomLeaseList(int roomListID){
        return roomLeaseListMapper.deleteRoomLeaseList(roomListID);
    }

    //..批量删除已退租列表
    public boolean batchdeleteRoomLeaseList(List<Integer> roomListID){
        return roomLeaseListMapper.batchdeleteRoomLeaseList(roomListID);
    }


    //..（批量）管理员新增合同
    @Override
    public R batchAddContract(Map<String, Object> map) {
        R json = null;
        Date date = new Date();
        ArrayList<HashMap<String,Object>> roomLeaseList = (ArrayList<HashMap<String, Object>>) map.get("roomLeaseList");
        ArrayList<Object> roomNOList = new ArrayList<Object>();
        ArrayList<Object> applyList = new ArrayList<Object>();
        roomLeaseList.forEach(item->{
            item.put("contractNO",UUID.randomUUID().toString().replaceAll("-",""));
            roomNOList.add((Object) item.get("roomNO"));
            applyList.add((Object) item.get("applyID"));
        });
        System.out.println(roomLeaseList);
        if(roomLeaseListMapper.batchAddContract(roomLeaseList)){
            if(roomLeaseListMapper.batchupdateRoomBystatus(roomNOList) && roomLeaseListMapper.batchupdateApplyBystatus(applyList)){
                json = R.ok("添加成功");
            }
        }else {
            json = R.error("添加失败");
        }
        return json;
    }

    //租客申请退租
    public R tenantVacating(Map<String, Object> map){
        R json = null;
        if (roomLeaseListMapper.tenantVacating(map)) {
            if(roomLeaseListMapper.tenantApplyByStatus(map)){
                json = R.ok("申请退租成功");
            } else {
                json = R.error("该房已申请退租");
            }
        } else {
            json = R.error("申请退租失败");
        }
        return json;
    }
}
