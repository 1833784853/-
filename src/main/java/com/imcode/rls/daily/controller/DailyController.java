package com.imcode.rls.daily.controller;

import com.imcode.common.model.R;
import com.imcode.rls.daily.model.Daily;
import com.imcode.rls.daily.service.DailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DailyController {
    @Autowired
    DailyService dailyServiceimpl;
    /*
    管理员查看所有日常
     */
    @GetMapping("/Admin/getDailylist")
    public R getDailyListA() {
        R json = R.ok();
        List<Daily> roomSourceList = dailyServiceimpl.selectAll();
        json.put("msg", "请求成功");
        json.put("data", roomSourceList);
        return json;
    }

    /*
    管理员根据用户id查询
     */
    @GetMapping("/Admin/getDailylistUserId")
    public R getDailyListUserIdA(String userID) {
        R json = R.ok();
        List<Daily> roomSourceList = dailyServiceimpl.selectid(userID);
        json.put("msg", "请求成功");
        json.put("data", roomSourceList);
        return json;
    }

    /*
    管理员编辑日常
     */
    @PostMapping ("/Admin/editDaily")
    public R editDailyA(@RequestBody Map<String,Object> dailyid){
        R json;
        boolean result=dailyServiceimpl.update(dailyid);
        if(result){
            json=R.ok();//更新成功
            json.put("msg","更新成功");
        }else {
            json=R.error("更新失败");//更新失败
        }
        return json;
    }

    /*
   管理员添加日常
    */
    @PostMapping("/Admin/addDaily")
    public R addDailyA(@RequestBody Map<String,String> daily){
        if(daily!=null){
            String userID=(String) daily.get("userID");
            String dailyTitle=(String) daily.get("dailyTitle");
            String dailyContent=(String) daily.get("dailyContent");
            return dailyServiceimpl.add(daily);
        }else {
            return R.error("添加失败");
        }
    }


    /*
    租客根据id查询自己的日常
     */
    @GetMapping("/Tenant/getDailylistUserId")
    public R getDailyListUserIdT(String userID) {
        System.out.println(userID);
        R json = R.ok();
        List<Daily> roomSourceList = dailyServiceimpl.selectid(userID);
        json.put("msg", "请求成功");
        json.put("data", roomSourceList);
        return json;
    }

    /*
    租客编辑日常
     */
    @PostMapping ("/Tenant/editDaily")
    public R editDailyT(@RequestBody Map<String,Object> dailyid){
        R json;
        boolean result=dailyServiceimpl.update(dailyid);
        if(result){
            json=R.ok();//更新成功
            json.put("msg","更新成功");
        }else {
            json=R.error("更新失败");//更新失败
        }
        return json;
    }


    /*
    租客添加日常
     */
    @PostMapping("/Tenant/addDaily")
    public R addDailyT(@RequestBody Map<String,String> daily){
        if(daily!=null){
            String userID=(String) daily.get("userID");
            String dailyTitle=(String) daily.get("dailyTitle");
            String dailyContent=(String) daily.get("dailyContent");
            return dailyServiceimpl.add(daily);
        }else {
            return R.error("添加失败");
        }
    }


    /*
    租客删除日常
     */
    @PostMapping("/Tenant/deleteDaily")
    public R deleteDarilyT(@RequestBody Map<String,Integer> dailyID){
        R json;
        boolean flag=dailyServiceimpl.delete(dailyID.get("dailyID"));
        if(flag){
            json=R.ok().put("msg","删除成功！");
        }else {
            json=R.error("删除失败！");
        }
        return json;
    }


}
