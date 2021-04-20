package com.imcode.rls.roomsource.service;

import com.imcode.rls.roomsource.mapper.RoomSourceMapper;
import com.imcode.rls.roomsource.model.RoomSource;
import com.imcode.rls.user.model.Loginregister;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
public class RoomSourceServiceTest {

    @Qualifier("IRoomSourceService")
    @Autowired
    private IRoomSourceService roomSourceService;

    @Autowired
    private RoomSourceMapper roomSourceMapper;

    @Test
    public void getRoom(){
        //查询全部
        List<RoomSource> roomSources = roomSourceMapper.getRoomSourceList();
        for (RoomSource roomSource : roomSources) {
            System.out.println(roomSource);
        }

//        Loginregister loginregister = new Loginregister();
//
//        RoomSource roomSource = new RoomSource();
//        roomSource.setRoomNO("20201542");
//        roomSource.setRoomAddress("广西桂林");
//        roomSource.setRoomArea(126);
//        roomSource.setRoomPrice(1500);
//        roomSource.setRoomStatus("在售");
//        roomSource.setRoomTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        roomSource.setRoomLatelyTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        roomSource.setUser(loginregister);
//        boolean bl = roomSourceMapper.insertRoomSource(roomSource);
//        if (bl){
//            System.out.println("添加成功");
//        }


        //删除
//        boolean bl = roomSourceMapper.deleteRoomSource("10002011");
//        if (bl){
//            System.out.println("删除成功");
//        }

        //修改
        RoomSource roomSource = new RoomSource();
        String roomNo = "10002010";
        if (roomNo == roomSource.getRoomNO()){
            System.out.println(roomNo);


        }





    }



}
