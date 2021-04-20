package com.imcode.rls.daily;

import com.imcode.rls.daily.mapper.DailyMapper;
import com.imcode.rls.daily.model.Daily;
import com.imcode.rls.daily.service.DailyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
public class DailyTest {

    @Autowired
    private DailyService dailyService;
    @Autowired
    private DailyMapper dailyMapper;
    @Test
    public void get(){
        List<Daily> dailyList=dailyMapper.selectAll();
        for (Daily daily:dailyList){
            System.out.println(daily);
        }

//        Daily daily=new Daily();
//        daily.setDailyContent("小肉丸");
//        daily.setDailyTitle("食物");
//        daily.setDailyTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        int result=dailyMapper.update(daily);
//        if(result>0){
//            System.out.println("添加成功");
//        }
//        Daily daily=new Daily();
//        int id=1;
//        if(id==daily.getDailyID()){
//            System.out.println("修改成功");
//        }


    }

}
