package com.imcode.rls.orders.service;

import com.imcode.common.model.R;
import com.imcode.rls.orders.model.NewTypes;
import com.imcode.rls.orders.model.RoomNews;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

public interface NewsService {
    /**
     * 获取所有的新闻
     * @return
     */
    List<RoomNews> getRoomNews(Integer CurrentPage,Integer pageSize);

    /**
     * 获取图片url地址
     * @param data
     * @return
     */
    R getImgUrl(HashMap<String, MultipartFile> data);

    List<NewTypes> getNewTypes();

    R addNews(String roomNO,String userID,String newTitle,String newDescription,String newHTML,Integer newTypeID,String newTime);

    NewTypes getNewTypeByName(String typeName);

    R addNewsType(String newstypeName);

    RoomNews getRoomNews(int roomNewsID);

    R updateNews(Integer roomNewID, String roomNO,String userID,String newTitle,String newDescription,String newHTML,Integer newTypeID,String newTime);

    R getNewsDataByNewsType(Integer newTypeID);

    Integer getCountNewsNumber();

    R deleteNews(Integer roomNewID);
}
