package com.imcode.rls.daily.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imcode.common.model.JwtUtils;
import com.imcode.common.model.R;
import com.imcode.rls.daily.mapper.DailyMapper;
import com.imcode.rls.daily.model.Daily;
import com.imcode.rls.daily.service.DailyService;
import com.imcode.rls.user.mapper.LoginregisterMapper;
import com.imcode.rls.user.model.Loginregister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DailyServiceimpl implements DailyService {

    @Autowired
    DailyMapper dailyMapper;
    @Autowired
    LoginregisterMapper loginregisterMapper;
    public R add(Map<String, String> daily) {
        String userID = daily.get("userID");
        String dailyTitle = daily.get("dailyTitle");
        String dailyContent = daily.get("dailyContent");

        if (userID == null || userID.trim().equals("")) {
            return R.error("缺少必要参数,请刷新重试");
        }
        if (dailyTitle == null || dailyTitle.trim().equals("")) {
            return R.error("请输入标题");
        }
        if (dailyContent == null || dailyContent.trim().equals("")) {
            return R.error("请输入内容");
        }
        HashMap<String, String> userInfo = new HashMap<String, String>();
        userInfo.put("userID", userID);
        userInfo.put("dailyTitle", dailyTitle);
        userInfo.put("dailyContent", dailyContent);

        if (dailyMapper.add(userInfo)) {
           return R.ok();
        }else{
            return R.error("添加失败");
        }
    }



    public boolean delete(int dailyID) {
            return dailyMapper.delete(dailyID);
    }



    public List<Daily> selectAll() {
        List<Daily> dailyList = dailyMapper.selectAll();
        return dailyMapper.selectAll();
    }

    public List<Daily> selectid(String userID) {
        return dailyMapper.selectid(userID);
    }

    public boolean update(Map<String,Object> daily) {

        return dailyMapper.update(daily);
    }
}
