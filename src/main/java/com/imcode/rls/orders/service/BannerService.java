package com.imcode.rls.orders.service;

import com.imcode.common.model.R;
import com.imcode.rls.orders.model.Banner;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface BannerService {
    R addBanner(Map<String,Object> data);
    List<Banner> getBannerAll();
}
