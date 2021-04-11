package com.imcode.rls.orders.controller;

import com.imcode.common.model.R;
import com.imcode.common.service.FileService;
import com.imcode.rls.orders.model.NewTypes;
import com.imcode.rls.orders.model.RoomNews;
import com.imcode.rls.orders.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/News")
public class NewsController {

    @Autowired
    private NewsService newsServiceImpl;

    @Autowired
    private FileService fileService;

    @PostMapping("/getAllNews")
    public R getNews(@RequestBody Map<String, Integer> data) {
        if (data == null) return R.error("缺少必要参数");
        Integer currentPage = data.get("currentPage");
        Integer pageSize = data.get("pageSize");
        R json;
        final List<RoomNews> roomNews = newsServiceImpl.getRoomNews(currentPage, pageSize);
        final Integer countNewsNumber = newsServiceImpl.getCountNewsNumber();

        json = R.ok();
        json.put("data", new HashMap<String, Object>() {{
            put("newsList", roomNews);
            put("count", countNewsNumber);
        }});
        return json;
    }



    @PostMapping("/getNewsImgUrl")
    public R uploadNewsImg(@RequestParam("file") final MultipartFile file) {
        R json;
        if (file != null) {
            json = newsServiceImpl.getImgUrl(new HashMap<String, MultipartFile>() {{
                put("file", file);
            }});
        } else {
            json = R.error("上传图片失败，请重试！！");
        }
        return json;
    }

    @GetMapping("/getNewsTypes")
    public R getNewsTypes() {
        List<NewTypes> newTypes = newsServiceImpl.getNewTypes();
        R json = R.ok();
        json.put("data", newTypes);
        return json;
    }


    @PostMapping("/addNews")
    public R addNews(@RequestBody HashMap<String, Object> data) {
        if (data != null) {
            String title = (String) data.get("title");
            String userID = (String) data.get("userID");
            String roomNO = (String) data.get("roomNO");
            String date = (String) data.get("date");
            String time = (String) data.get("time");
            Integer newsType = (Integer) data.get("newsType");
            String description = (String) data.get("description");
            String content = (String) data.get("content");

            String newTime = "";
            if (!date.trim().equals("")) {
                newTime += date;
            } else {
                newTime += new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            }

            if (!time.trim().equals("")) {
                newTime += (" " + time);
            } else {
                newTime += new SimpleDateFormat(" HH:mm:ss").format(new Date());
            }
            return newsServiceImpl.addNews(roomNO, userID, title, description, content, newsType, newTime);

        } else {
            return R.error("添加数据失败，缺少必要参数！！");
        }
    }

    /**
     * 检测新闻类型是否重复
     *
     * @param typeName
     * @return
     */
    @GetMapping("/checkTypeName")
    public R checkTypeName(@RequestParam("typeName") String typeName) {
        if (typeName == null || typeName.trim().equals("")) {
            return R.error("请输入要添加的新闻类型");
        } else if (newsServiceImpl.getNewTypeByName(typeName) != null) {
            return R.error("该新闻类型已存在");
        } else {
            return R.ok();
        }
    }

    @PostMapping("/addNewsType")
    public R addNewsType(@RequestBody HashMap<String, String> data) {
        if (data != null) {
            return newsServiceImpl.addNewsType(data.get("typeName"));
        } else {
            return R.error("请输入新闻类型");
        }
    }

    /**
     * 返回新闻数据
     *
     * @param roomNewID
     * @return
     */
    @GetMapping("/getNews")
    public R getNews(@RequestParam("roomNewID") Integer roomNewID) {
        if (roomNewID != null) {
            RoomNews roomNews = newsServiceImpl.getRoomNews(roomNewID);
            if (roomNews != null) {
                return R.ok().put("data", roomNews);
            } else {
                return R.error("查无该数据");
            }
        } else {
            return R.error("缺少必要参数");
        }
    }

    @PostMapping("/updateNews")
    public R updateNews(@RequestBody HashMap<String, Object> data) {
        if (data != null) {
            if (data.get("roomNewID") == null) {
                return R.error("缺少必要参数");
            }
            Integer roomNewID = Integer.parseInt((String) data.get("roomNewID"));
            String title = (String) data.get("title");
            String userID = (String) data.get("userID");
            String roomNO = (String) data.get("roomNO");
            String date = (String) data.get("date");
            String time = (String) data.get("time");
            Integer newsType = (Integer) data.get("newsType");
            String description = (String) data.get("description");
            String content = (String) data.get("content");

            String newTime = "";
            if (!date.trim().equals("")) {
                newTime += date;
            } else {
                newTime += new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            }

            if (!time.trim().equals("")) {
                newTime += (" " + time);
            } else {
                newTime += new SimpleDateFormat(" HH:mm:ss").format(new Date());
            }
            return newsServiceImpl.updateNews(roomNewID, roomNO, userID, title, description, content, newsType, newTime);

        } else {
            return R.error("添加数据失败，缺少必要参数！！");
        }
    }

    /**
     * 获取首页上的新闻列表
     *
     * @param newTypeID 类型id
     * @return
     */
    @GetMapping("/getNewHouses")
    public R getHousesNews(@RequestParam("newTypeID") Integer newTypeID) {
        if (newTypeID == null) {
            return R.error("缺少必要参数");
        }
        return newsServiceImpl.getNewsDataByNewsType(newTypeID);
    }

    /**
     * 删除文件
     *
     * @param data 要删除的文件列表
     * @return
     */
    @PostMapping("/delete")
    public R delete(@RequestBody Map<String, String> data) {
        if (data != null) {
            String fileUrl = data.get("fileUrls");
            int start = fileUrl.indexOf("upload");
            int end = fileUrl.indexOf("/", start + 2);
            if (fileService.deleteFile(fileUrl.substring(end))) {
                return R.ok("删除成功");
            } else {
                return R.error("删除失败");
            }
        }

        return R.ok("删除成功");
    }

    @PostMapping("/deleteNews")
    public R deleteNews(@RequestBody Map<String, Integer> map) {
        if (map == null) return R.error("缺少必要参数");
        return newsServiceImpl.deleteNews(map.get("roomNewID"));
    }
}
