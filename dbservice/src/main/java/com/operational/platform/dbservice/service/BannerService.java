package com.operational.platform.dbservice.service;

import com.operational.platform.dbservice.model.Banner;

import java.util.List;

/**
 * Created by lihuajun on 16-7-6.
 */
public interface BannerService {


    List<Banner> listByForum(Integer forum);

}
