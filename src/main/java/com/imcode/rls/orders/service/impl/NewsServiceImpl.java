package com.imcode.rls.orders.service.impl;

import com.imcode.common.model.R;
import com.imcode.common.service.FileService;
import com.imcode.rls.orders.mapper.OrdersMapper;
import com.imcode.rls.orders.model.NewTypes;
import com.imcode.rls.orders.model.RoomNews;
import com.imcode.rls.orders.service.NewsService;
import com.imcode.rls.roomsource.mapper.RoomSourceMapper;
import com.imcode.rls.user.mapper.LoginregisterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private FileService fileService;

    @Autowired
    private RoomSourceMapper roomSourceMapper;

    @Autowired
    private LoginregisterMapper loginregisterMapper;

    public List<RoomNews> getRoomNews(final Integer CurrentPage, final Integer pageSize) {
        List<RoomNews> roomNews = ordersMapper.getRoomNews(new HashMap<String, Integer>(){{
            put("start",CurrentPage*pageSize);
            put("end",pageSize);
        }});
        for (RoomNews roomNew : roomNews) {
            String newHTML = roomNew.getNewHTML();
            int index = 0;
            int header = 0;
            StringBuffer resultHTML = new StringBuffer();
            try {
                InetAddress ip4 = Inet4Address.getLocalHost();
                while ((index = newHTML.indexOf("<img", index + 3)) != -1) {
                    int start = newHTML.indexOf("src=", index);
                    int end = newHTML.indexOf("\"", start + 3);
                    resultHTML.append(newHTML, header, start + 5);
                    resultHTML.append("http://").append(ip4.getHostAddress());
                    resultHTML.append(newHTML.substring(end + 1));
                    newHTML = resultHTML.toString();
                    resultHTML.delete(0, resultHTML.length());
                }
                roomNew.setNewHTML(newHTML);
            } catch (UnknownHostException e) {
                e.printStackTrace();
                return null;
            }
        }
        return roomNews;
    }

    public R getImgUrl(HashMap<String, MultipartFile> data) {
        R json = new R();
        MultipartFile file = data.get("file");
        if (file != null) {
            try {
                R upload = fileService.upload(file);
                json.put("errno", 0);
                ArrayList<String> list = new ArrayList<String>();
                InetAddress ip4 = Inet4Address.getLocalHost();
                list.add("http://" + ip4.getHostAddress() + upload.get("url"));
                json.put("data", list);
            } catch (IOException e) {
                e.printStackTrace();
                json.put("errno", 1);
            }
        } else {
            json.put("errno", 1);
        }
        return json;
    }

    public List<NewTypes> getNewTypes() {
        return ordersMapper.getNewTypes();
    }

    public R addNews(String roomNO, String userID, String newTitle, String newDescription, String newHTML, Integer newTypeID, String newTime) {
        if (roomNO == null || roomNO.trim().equals("")) {
            return R.error("请选择正确的房源编号");
        } else if (roomSourceMapper.getRoomSource(roomNO) == null) {
            return R.error("请选择正确的房源编号");
        }

        if (userID == null || userID.trim().equals("")) {
            return R.error("缺少必要参数，请正确填写表单");
        } else if (loginregisterMapper.getLoginregisterByID(userID) == null) {
            return R.error("无效用户，请先注册后再使用该功能");
        }

        if (newTitle == null || newTitle.trim().equals("")) {
            return R.error("请填写新闻标题");
        }

        if (newDescription == null || newDescription.trim().equals("")) {
            return R.error("请填写新闻描述");
        } else if (newDescription.trim().length() < 0 || newDescription.trim().length() > 100) {
            return R.error("请输入长度为10-100的新闻描述");
        }

        if (newHTML == null || newHTML.trim().equals("")) {
            return R.error("请输入新闻详情内容");
        } else if (newHTML.trim().length() < 50) {
            return R.error("请输入50个字符以上的新闻内容");
        }

        int index = 0;
        int header = 0;
        StringBuffer resultHTML = new StringBuffer();
        while ((index = newHTML.indexOf("<img", index + 3)) != -1) {
            int start = newHTML.indexOf("http://", index);
            if (start == -1) break;
            int end = newHTML.indexOf("/", start + 7);
            resultHTML.append(newHTML, header, start);
            resultHTML.append(newHTML.substring(end));
            newHTML = resultHTML.toString();
            resultHTML.delete(0, resultHTML.length());
        }

        if (newTypeID == null) {
            return R.error("请选择新闻类型");
        } else if (ordersMapper.getNewType(newTypeID) == null) {
            return R.error("请选择正确的新闻类型");
        }

        HashMap<String, Object> news = new HashMap<String, Object>();
        news.put("roomNO", roomNO);
        news.put("userID", userID);
        news.put("newTitle", newTitle);
        news.put("newDescription", newDescription);
        news.put("newTime", newTime);
        news.put("newHTML", newHTML);
        news.put("newTypeID", (int) newTypeID);
        if (ordersMapper.addNews(news)) {
            R json = R.ok();
            return json.put("msg", "添加成功");
        } else {
            return R.error("添加失败");
        }
    }

    public NewTypes getNewTypeByName(String typeName) {
        return ordersMapper.getNewTypeByName(typeName);
    }

    public R addNewsType(String newstypeName) {
        if (newstypeName == null || newstypeName.trim().equals("")) {
            return R.error("请输入新闻类型");
        } else if (newstypeName.length() < 2) {
            return R.error("请输入2个字符以上");
        }
        if (ordersMapper.addNewsType(new NewTypes(0, newstypeName))) {
            return R.ok().put("msg", "添加成功");
        } else {
            return R.error("添加失败");
        }
    }

    public RoomNews getRoomNews(int roomNewsID) {
        RoomNews news = ordersMapper.getNews(roomNewsID);
        String newHTML = news.getNewHTML();
        int index = 0;
        int header = 0;
        StringBuffer resultHTML = new StringBuffer();
        try {
            InetAddress ip4 = Inet4Address.getLocalHost();
            while ((index = newHTML.indexOf("<img", index + 3)) != -1) {
                int start = newHTML.indexOf("src=", index);
                int end = newHTML.indexOf("\"", start + 3);
                resultHTML.append(newHTML, header, start + 5);
                resultHTML.append("http://").append(ip4.getHostAddress());
                resultHTML.append(newHTML.substring(end + 1));
                newHTML = resultHTML.toString();
                resultHTML.delete(0, resultHTML.length());
            }
            news.setNewHTML(newHTML);
            return news;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
    }

    public R updateNews(Integer roomNewID, String roomNO, String userID, String newTitle, String newDescription, String newHTML, Integer newTypeID, String newTime) {

        if (roomNewID == null) {
            return R.error("缺少必要参数");
        }

        if (roomNO == null || roomNO.trim().equals("")) {
            return R.error("请选择正确的房源编号");
        } else if (roomSourceMapper.getRoomSource(roomNO) == null) {
            return R.error("请选择正确的房源编号");
        }

        if (userID == null || userID.trim().equals("")) {
            return R.error("缺少必要参数，请正确填写表单");
        } else if (loginregisterMapper.getLoginregisterByID(userID) == null) {
            return R.error("无效用户，请先注册后再使用该功能");
        }

        if (newTitle == null || newTitle.trim().equals("")) {
            return R.error("请填写新闻标题");
        }

        if (newDescription == null || newDescription.trim().equals("")) {
            return R.error("请填写新闻描述");
        } else if (newDescription.trim().length() < 0 || newDescription.trim().length() > 100) {
            return R.error("请输入长度为10-100的新闻描述");
        }

        if (newHTML == null || newHTML.trim().equals("")) {
            return R.error("请输入新闻详情内容");
        } else if (newHTML.trim().length() < 50) {
            return R.error("请输入50个字符以上的新闻内容");
        }

        int index = 0;
        int header = 0;
        StringBuffer resultHTML = new StringBuffer();
        while ((index = newHTML.indexOf("<img", index + 3)) != -1) {
            int start = newHTML.indexOf("http://", index);
            if (start == -1) break;
            int end = newHTML.indexOf("/", start + 7);
            resultHTML.append(newHTML, header, start);
            resultHTML.append(newHTML.substring(end));
            newHTML = resultHTML.toString();
            resultHTML.delete(0, resultHTML.length());
        }

        if (newTypeID == null) {
            return R.error("请选择新闻类型");
        } else if (ordersMapper.getNewType(newTypeID) == null) {
            return R.error("请选择正确的新闻类型");
        }

        HashMap<String, Object> news = new HashMap<String, Object>();
        news.put("roomNewID", roomNewID);
        news.put("roomNO", roomNO);
        news.put("userID", userID);
        news.put("newTitle", newTitle);
        news.put("newDescription", newDescription);
        news.put("newTime", newTime);
        news.put("newHTML", newHTML);
        news.put("newTypeID", (int) newTypeID);
        if (ordersMapper.updateNews(news)) {
            R json = R.ok();
            return json.put("msg", "修改成功");
        } else {
            return R.error("修改失败");
        }
    }

    public R getNewsDataByNewsType(Integer newTypeID) {
        if (newTypeID == null) {
            return R.error("数据加载失败，缺少必要参数");
        }
        List<RoomNews> newsDataByNewsType = ordersMapper.getNewsDataByNewsType(newTypeID);
        System.out.println(newsDataByNewsType);
        ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        for (RoomNews roomNews : newsDataByNewsType) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            String newHTML = roomNews.getNewHTML();
            int index = 0;
            StringBuffer resultHTML = new StringBuffer();
            try {
                InetAddress ip4 = Inet4Address.getLocalHost();
                while ((index = newHTML.indexOf("<img", index + 3)) != -1) {
                    int start = newHTML.indexOf("src=", index);
                    int end = newHTML.indexOf("\"", start + 3);
                    resultHTML.append(newHTML, 0, start + 5);
                    resultHTML.append("http://").append(ip4.getHostAddress());
                    resultHTML.append(newHTML.substring(end + 1));
                    newHTML = resultHTML.toString();
                    resultHTML.delete(0, resultHTML.length());
                }

                int indexOf = newHTML.indexOf("<img");
                String imgUrl = "";
                if (indexOf != -1) {
                    int start = newHTML.indexOf("http://", indexOf);
                    int end = newHTML.indexOf("\"", start + 6);
                    imgUrl = newHTML.substring(start, end);
                }
                roomNews.setNewTime(roomNews.getNewTime().split("\\.")[0]);
                map.put("imgUrl", imgUrl);
                roomNews.setNewHTML(newHTML);
                map.put("newsData", roomNews);
                data.add(map);
            } catch (UnknownHostException e) {
                e.printStackTrace();
                return R.ok().put("msg", "请求失败");
            }
        }
        return R.ok().put("msg", "请求成功").put("data", data);
    }

    public Integer getCountNewsNumber() {
        return ordersMapper.getCountNewsNumber();
    }

    public R deleteNews(Integer roomNewID) {
        RoomNews roomNews = this.getRoomNews(roomNewID);
        String newHTML = roomNews.getNewHTML();
        int index = 0;
        while ((index = newHTML.indexOf("<img",index+1))!=-1) {
            index = newHTML.indexOf("upload",index);
            if (index == -1) break;
            int start = newHTML.indexOf("/", index + 1);
            int end = newHTML.indexOf("\"", start + 2);
            fileService.deleteFile(newHTML.substring(start,end));
        }
        boolean isDel = ordersMapper.deleteNews(roomNewID);
        if (isDel) {
            return R.ok("删除成功");
        } else {
            return R.error("删除失败");
        }
    }
}
