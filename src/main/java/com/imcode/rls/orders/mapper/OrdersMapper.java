package com.imcode.rls.orders.mapper;

import com.imcode.rls.orders.model.Banner;
import com.imcode.rls.orders.model.NewTypes;
import com.imcode.rls.orders.model.RoomNews;

import java.util.List;
import java.util.Map;

public interface OrdersMapper {
    boolean addBanner(Banner banner);
    List<Banner> getBannerAll();
    List<RoomNews> getRoomNews(Map<String,Integer> map);
    List<NewTypes> getNewTypes();
    NewTypes getNewType(int id);
    NewTypes getNewTypeByName(String typeName);
    boolean addNews(Map<String,Object> map);
    boolean addNewsType(NewTypes newTypes);

    RoomNews getNews(int roomNewsID);

    boolean updateNews(Map<String,Object> map);

    List<RoomNews> getNewsDataByNewsType(Integer newTypeID);

    Integer getCountNewsNumber();

    boolean deleteNews(Integer roomNewID);

    boolean updateNewsCount(Map<String,Object> map);

    List<RoomNews> getAll();
}
