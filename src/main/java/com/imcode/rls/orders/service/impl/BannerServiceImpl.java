package com.imcode.rls.orders.service.impl;


import com.imcode.common.model.R;
import com.imcode.common.service.FileService;
import com.imcode.rls.orders.mapper.OrdersMapper;
import com.imcode.rls.orders.model.Banner;
import com.imcode.rls.orders.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private FileService fileService;

    public R addBanner(Map<String, Object> data) {
        R json;
        String title = (String) data.get("title");
        String content = (String) data.get("content");
        R fileUpdate = null;
        try {
            fileUpdate = fileService.upload((MultipartFile) data.get("file"));
            String url = (String) fileUpdate.get("url");
            boolean isAdd = ordersMapper.addBanner(new Banner(null, url, title, content));
            if (isAdd) {
                json = R.ok();
                json.put("msg","添加成功");
                json.put("imgUrl",url);
            } else {
                json = R.error("添加失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
            json = R.error("添加失败");
        }

        return json;
    }

    public List<Banner> getBannerAll() {
        List<Banner> banners = ordersMapper.getBannerAll();
        try {
            InetAddress ip4 = Inet4Address.getLocalHost();
            for (Banner banner : banners) {
                if (banner.getBannerImgUrl()!=null) {
                    banner.setBannerImgUrl("http://"+ip4.getHostAddress()+banner.getBannerImgUrl());
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return banners;
    }
}
