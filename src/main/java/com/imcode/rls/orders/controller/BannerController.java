package com.imcode.rls.orders.controller;


import com.imcode.common.model.R;
import com.imcode.common.service.FileService;
import com.imcode.rls.orders.model.Banner;
import com.imcode.rls.orders.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/banner-handle")
public class BannerController {

    @Autowired
    private BannerService bannerServiceImpl;

    @PostMapping("/add")
    public R addBanner(@RequestParam("file") final MultipartFile file, @RequestParam("title") final String title, @RequestParam("content") final String content) {
//        R json = bannerServiceImpl.addBanner(file);
        R json;
        if (file==null || title==null || title.equals("") || content == null || content.equals("")) {
            json = R.error("上传失败，请正确添加");
        } else {
            json = bannerServiceImpl.addBanner(new HashMap<String, Object>() {{
                put("title", title);
                put("content", content);
                put("file", file);
            }});
        }
        return json;
    }

    @GetMapping("/getBannerAll")
    public R getBannerAll() {
        R json;
        List<Banner> bannerAll = bannerServiceImpl.getBannerAll();
        json = R.ok();
        json.put("msg","请求成功");
        json.put("data",bannerAll);
        return json;
    }

}
