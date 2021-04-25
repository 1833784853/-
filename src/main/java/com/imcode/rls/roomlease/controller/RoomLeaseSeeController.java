package com.imcode.rls.roomlease.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class RoomLeaseSeeController {

    @PostMapping("/seeContract")
    public ModelAndView seeContract(@RequestBody Map<String,Object> map) {
        ModelAndView mav = new ModelAndView();

//        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
//        try {
//            Date time = sdf.parse((String) map.get("time"));
//            int t = time.getYear() + 2;
////            time.setTime(time.getTime() + 1000*60*60*24*30*12);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        String time = (String) map.get("time");
        String[] s = time.split(" ");
        String year1 = s[0].split("-")[0];
        String s1 = Integer.parseInt(year1) + 2 + "-" + s[0].split("-")[1] + "-" + s[0].split("-")[2] +" "+s[1];
        mav.addObject("roomAddress", map.get("roomAddress"));
        mav.addObject("roomArea",map.get("roomArea"));
        mav.addObject("endTime",s1);
        mav.addObject("adminName", map.get("adminName"));
        mav.addObject("userName", map.get("userName"));
        mav.addObject("time", map.get("time"));
        mav.addObject("userCard", map.get("userCard"));
        mav.addObject("adminCard", map.get("adminCard"));
        mav.addObject("price", map.get("price"));
        mav.addObject("roomNO", map.get("roomNO"));
        mav.setViewName("../contract");
        return mav;

    }

}
