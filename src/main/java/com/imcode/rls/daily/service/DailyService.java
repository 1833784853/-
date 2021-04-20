package com.imcode.rls.daily.service;

import com.imcode.common.model.R;
import com.imcode.rls.daily.model.Daily;

import java.util.List;
import java.util.Map;

public interface DailyService{
    R add(Map<String, String> daily);   //添加日常
    boolean delete(int dailyID);  //删除日常
    List<Daily> selectAll();    //查询所有日常
    List<Daily> selectid(String userID);   //根据用户id查询日常
    boolean update(Map<String, Object> daily);    //修改日常
}
