package com.imcode.rls.roomapply.service;

import com.imcode.common.model.R;
import com.imcode.rls.roomapply.model.Apply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ApplyService {
    R add(Map<String, String> roomapply);   //租客申请
    boolean delete(int applyID);  //租客删除已退租列表
    List<Apply> selectAll();    //查询所有看房申请列表
    List<Apply> selectApplylist(HashMap<String, Object> map);  //查询所有看房申请列表
    List<Apply> selectid(int applyStatus);  //根据看房申请状态查询
    List<Apply> selectByIdNo(String userID,String roomNO);  //根据用户id和房源编号查询

    boolean update(Map<String, Object> applyStatus);    //修改看房申请状态
    List<Apply> selectidTenant(HashMap<String, Object> map); //根据客户id查询
    boolean deleteList(List<Integer> applyID);   //批量删除
    boolean updateAgreeList(List<Integer> applyID);    //批量同意
    boolean updateRefuseList(List<Integer> applyID);    //批量拒绝

    int countUserApplySee(String userID); // 获取当前用户的看房申请条数
}
