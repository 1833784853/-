package com.imcode.rls.user.service;

import com.imcode.common.service.FileService;
import com.imcode.rls.orders.mapper.OrdersMapper;
import com.imcode.rls.orders.model.RoomNews;
import com.imcode.rls.orders.service.NewsService;
import com.imcode.rls.user.mapper.UserJurisdictionMenuMapper;
import com.imcode.rls.user.model.Loginregister;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

//
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
public class UserLoginServiceTest {

    @Autowired
    private IUserLoginService userLoginServiceImpl;

    @Autowired
    private IUserJurisdictionMenuService userJurisdictionMenuServiceImpl;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private FileService fileService;

    @Autowired
    private NewsService newsServiceImpl;

    @Test
    public void getUser() {
//        List<Loginregister> loginregister = iUserLoginService.getUser();
////        for (Loginregister loginregister1 : loginregister) {
////            System.out.println(111);
////        }
//        Loginregister loginregister = new Loginregister();
//        loginregister.setUserId(UUID.randomUUID().toString()+new Date().getTime());
//        loginregister.setUserName("zie");
//        loginregister.setUserPassword(DigestUtils.md5DigestAsHex("856507710".getBytes()));
//        loginregister.setUserPhone("13006979670");
//        loginregister.setUserType("管理员");
//        loginregister.setUserStatus(1);
//        loginregister.setRegisterTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        boolean b = iUserLoginService.addUser(loginregister);
//        if (b) {
//            System.out.println("添加成功");
//        }
//        List<RoomNews> roomNews = ordersMapper.getRoomNews();
//        System.out.println(roomNews);
//        String newHTML = "<h1 id='2h50q' style='text-align:center;'>das</h1><p style='text-align:center;'><img src='http://192.168.1.9:8080/upload/5BAC55BC9B37413A859129705FDF6BBF7.jpg' style='max-width:100%;'><br></p><p style='text-align:center;'><img src='http://192.168.1.9:8080/upload/0825EB434F1E42A39EA95288473971060.jpg' style='max-width:100%;' width='50%'><br></p><p style='text-align:center;'><img src='http://192.168.1.9:8080/upload/404DE24B00FC42A6BF520BA9A4A1EA327.jpg' style='max-width:100%;' width='50%'><br></p>";
//        int index = 0;
//        int header = 0;
//        StringBuffer resultHTML = new StringBuffer();
//        while ((index = newHTML.indexOf("<img", index + 3)) != -1) {
//            int start = newHTML.indexOf("http://", index);
//            int end = newHTML.indexOf("/",start+7);
//            resultHTML.append(newHTML, header, start);
//            resultHTML.append(newHTML.substring(end));
//            newHTML = resultHTML.toString();
//            resultHTML.delete(0,resultHTML.length());
//        }
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("start",0);
        map.put("end",5);
        System.out.println(ordersMapper.getRoomNews(map));
    }
}
