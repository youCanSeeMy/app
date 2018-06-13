package com.baizhi.service;

import com.baizhi.entity.Banner;

import java.util.List;

public interface BannerService {
    //展示所有轮播图
    public List<Banner> showAll();
    //删除轮播图
    public void deleteBanner(String id);
    //修改轮播图
    public void updateBanner(String id,String status);
    //增加轮播图
    public void addBanner(Banner banner);
}
