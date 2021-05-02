package com.imcode.rls.roomapply.mapper;


import com.imcode.rls.roomapply.model.Apply;
import com.imcode.rls.roomsource.model.RoomSource;
import com.imcode.rls.user.model.Loginregister;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Mapper
public interface ApplyMapper {
    boolean add(Map<String, String> roomapply);  //租客申请
    boolean delete(int applyID);  //租客删除已退租列表
    List<Apply> selectAll();        //查询所有看房申请列表
    List<Apply> selectApplylist(HashMap<String, Object> map);  //查询所有看房申请列表
    List<Apply> selectid(int applyStatus);  //根据看房申请状态查询
    List<Apply> selectRefund(HashMap<String, Object> map);  //查看申请退租
    List<Apply> selectidTenant(HashMap<String, Object> map);  //根据客户id查询
    List<Apply> selectByIdNo(Map<String,String> map);      //根据用户id和房源编号查询
    List<Apply> selectById(int applyID);         //根据applyid查询
    int countUserApplySee(String userID); // 获取当前用户的看房申请条数
    int countRefund(); //获取当前所有申请退房总条数
    boolean update(Map<String, Object> applyStatus);  //修改看房申请状态
    Loginregister getLoginregister();  //用户信息
    RoomSource getRoomSource();   //房源信息
    boolean deleteList(List<Integer> applyID);   //批量删除
    boolean updateAgreeList(List<Integer> applyID);    //批量同意
    boolean updateRefuseList(List<Integer> applyID);    //批量拒绝

    boolean updateAgreeRefund(Map<String,Object> map);    //单个同意退租
    boolean updateRefuseRefund(int applyID);    //单个拒绝退租


    boolean updateAgreeRefundList(List<Integer> applyID);    //批量同意退租
    boolean updateRefuseRefundList(List<Integer> applyID);    //批量拒绝退租



}
