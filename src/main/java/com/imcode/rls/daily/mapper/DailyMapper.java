package com.imcode.rls.daily.mapper;

import com.imcode.common.model.R;
import com.imcode.rls.daily.model.Daily;
import com.imcode.rls.user.model.Loginregister;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DailyMapper {
    boolean add(Map<String, String> daily); //添加日常
    boolean delete(int dailyID);     //删除日常
    List<Daily> selectAll();    //查询所有日常
    List<Daily> selectid(String userID);   //根据用户id查询日常
    boolean update(Map<String, Object> daily);  //修改日常
    List<Loginregister> getLoginregister();

}
