package com.imcode.rls.roomapply.service.impl;


import com.imcode.common.model.R;
import com.imcode.rls.roomapply.mapper.ApplyMapper;
import com.imcode.rls.roomapply.model.Apply;
import com.imcode.rls.roomapply.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApplyServiceimpl implements ApplyService{

    @Autowired
    ApplyMapper applyMapper;

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

    public boolean delete(int applyID) {

        return applyMapper.delete(applyID);

    }

    public List<Apply> selectAll() {
        List<Apply> applyList=applyMapper.selectAll();
        return applyList;
    }

    public List<Apply> selectApplylist(HashMap<String, Object> map) {
        List<Apply> applyList=applyMapper.selectApplylist(map);
        return applyList;
    }

    public List<Apply> selectid(int applyStatus) {
        //0：表示该房没有被申请  1：同意申请看房   2：拒绝申请看房
        // 3：表示该房被同意申请
        //4：表示该放被拒绝申请   5：表示该房可以被申请退房
        //6：正在申请退房  7：表示该房被同意申请退房  8：表示该房被拒绝申请退房
        if(applyStatus==0||applyStatus==1||applyStatus==2||applyStatus==3|| applyStatus==4||applyStatus==5||applyStatus==6||applyStatus==7||applyStatus==8){
            return applyMapper.selectid(applyStatus);
        }
        return null;

    }

    public List<Apply> selectidTenant(HashMap<String, Object> map) {
        String userID = (String) map.get("userID");
        if(userID!=null||userID.equals("")){
            List<Apply> applyLis=applyMapper.selectidTenant(map);
            return applyLis;
        }
        return null;
    }

    public boolean deleteList(List<Integer> applyID) {
        return applyMapper.deleteList(applyID);
    }



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

    public boolean update(Map<String, Object> applyStatus) {
        //0：表示该房没有被申请  1：同意申请看房   2：拒绝申请看房
        // 3：表示该房被同意申请
        //4：表示该放被拒绝申请   5：表示该房可以被申请退房
        //6：正在申请退房  7：表示该房被同意申请退房  8：表示该房被拒绝申请退房
        return applyMapper.update(applyStatus);
    }
    public boolean updateAgreeList(List<Integer> applyID) {
        return applyMapper.updateAgreeList(applyID);
    }

    public boolean updateRefuseList(List<Integer> applyID) {
        return applyMapper.updateRefuseList(applyID);
    }

    @Override
    public int countUserApplySee(String userID) {
        return applyMapper.countUserApplySee(userID);
    }

}
