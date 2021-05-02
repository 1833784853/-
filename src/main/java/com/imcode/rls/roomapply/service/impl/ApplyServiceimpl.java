package com.imcode.rls.roomapply.service.impl;


import com.imcode.common.model.R;
import com.imcode.rls.roomapply.mapper.ApplyMapper;
import com.imcode.rls.roomapply.model.Apply;
import com.imcode.rls.roomapply.service.ApplyService;
import com.imcode.rls.roomlease.mapper.RoomLeaseListMapper;
import com.imcode.rls.roomlease.service.IRoomLeaseService;
import com.imcode.rls.roomsource.mapper.RoomSourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApplyServiceimpl implements ApplyService{

    @Autowired
    ApplyMapper applyMapper;
    @Autowired
    RoomLeaseListMapper roomLeaseListMapper;

    @Autowired
    private IRoomLeaseService roomLeaseServiceImpl;

    /*
    租客申请
     */
    public R add(Map<String, String> roomapply) {
        String userID=roomapply.get("userID");
        String roomNO=roomapply.get("roomNO");

        if (userID == null || userID.trim().equals("")) {
            return R.error("缺少必要参数,请刷新重试");
        }
        if (roomNO == null || roomNO.trim().equals("")) {
            return R.error("请输入房源信息的编号");
        }
        HashMap<String, String> userInfo = new HashMap<String, String>();
        userInfo.put("userID",userID);
        userInfo.put("roomNO",roomNO);
        if(applyMapper.add(userInfo)){
                return R.ok().put("msg","添加成功");
        }
        return R.error("添加失败,请重新申请");
    }
    /*
    租客删除已退租列表
     */
    public boolean delete(int applyID) {

        return applyMapper.delete(applyID);

    }
    /*
    查询所有看房申请列表
     */
    public List<Apply> selectAll() {
        List<Apply> applyList=applyMapper.selectAll();
        return applyList;
    }
    /*
    查询所有看房申请列表
     */
    public List<Apply> selectApplylist(HashMap<String, Object> map) {
        List<Apply> applyList=applyMapper.selectApplylist(map);
        return applyList;
    }
    /*
    根据看房申请状态查询
     */
    public List<Apply> selectid(int applyStatus) {
        //0：表示该房没有被申请  1：同意申请看房   2：拒绝申请看房
        // 3：表示该房被同意申请
        //4：表示该房正在申请退租  5.表示该房同意申请退租    6.拒绝申请退租
        if(applyStatus==0||applyStatus==1||applyStatus==2||applyStatus==3|| applyStatus==4||applyStatus==5||applyStatus==6||applyStatus==7||applyStatus==8){
            return applyMapper.selectid(applyStatus);
        }
        return null;

    }
    /*
    根据客户id查询
     */
    public List<Apply> selectidTenant(HashMap<String, Object> map) {
        String userID = (String) map.get("userID");
        if(userID!=null||userID.equals("")){
            List<Apply> applyLis=applyMapper.selectidTenant(map);
            return applyLis;
        }
        return null;
    }
    /*
    批量删除
     */
    public boolean deleteList(List<Integer> applyID) {
        return applyMapper.deleteList(applyID);
    }


    /*
    根据用户id和房源编号查询
     */
    public List<Apply> selectByIdNo(final String userID, final String roomNO) {
        if(userID!=null||roomNO!=null){
            List<Apply> applyList=applyMapper.selectByIdNo(new HashMap<String,String>(){{
                put("userID",userID);
                put("roomNO",roomNO);
            }});
            return applyList;
        }
        return  null;

    }
    /*
    修改看房申请状态
     */
    public boolean update(Map<String, Object> applyStatus) {
        //0：表示该房没有被申请  1：同意申请看房   2：拒绝申请看房
        // 3：表示该房被同意申请
        //4：表示该房正在申请退租   5.表示该房同意申请退租  6.拒绝申请退租

        return applyMapper.update(applyStatus);
    }
    /*
    批量同意
     */
    public boolean updateAgreeList(List<Integer> applyID) {
        return applyMapper.updateAgreeList(applyID);
    }
    /*
    批量拒绝
     */
    public boolean updateRefuseList(List<Integer> applyID) {
        return applyMapper.updateRefuseList(applyID);
    }

    /*
    获取当前用户的看房申请条数
     */
    public int countUserApplySee(String userID) {
        return applyMapper.countUserApplySee(userID);
    }

    /*
    查看申请退租
     */
    public List<Apply> selectRefund(HashMap<String, Object> map) {
        return applyMapper.selectRefund(map);
    }

    /*
    获取当前所有申请退房总条数
     */
    public int countRefund() {
        return applyMapper.countRefund();
    }

    /*
    管理员同意退租
     */
    public boolean updateAgreeRefund(Map<String,Object> map,Map<String,Object> roomListID) {

        return applyMapper.updateAgreeRefund(map) && roomLeaseServiceImpl.updateRoomLeaseList(roomListID).get("msg").equals("终止合同成功");
    }

    /*
     管理员拒绝退租
      */
    public boolean updateRefuseRefund(int applyID,Map<String,Object> roomListID) {

        return applyMapper.updateRefuseRefund(applyID) && roomLeaseServiceImpl.updaterefuseRoomLease(roomListID).get("msg").equals("拒绝终止合同成功");
    }

    /*
    管理员批量同意申请退租
     */
    public boolean updateAgreeRefundList(List<Integer> applyID,Map<String,Object> roomListID) {
        return applyMapper.updateAgreeRefundList(applyID) && roomLeaseServiceImpl.updateRoomLeaseLists(roomListID).get("msg").equals("终止合同成功");
    }

    /*
    管理员拒绝同意申请退租
     */
    public boolean updateRefuseRefundList(List<Integer> applyID,Map<String,Object> roomListID) {
        return applyMapper.updateRefuseRefundList(applyID) && roomLeaseServiceImpl.updaterefuseRoomLeases(roomListID).get("msg").equals("拒绝终止合同成功");
    }
}
