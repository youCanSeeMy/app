package com.baizhi.service;

import com.baizhi.annoation.LogAnnoaction;
import com.baizhi.dao.BannerDAO;
import com.baizhi.dao.MenuDAO;
import com.baizhi.entity.Banner;
import com.baizhi.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Service
@RequestMapping(value="menu")
public class BannerServiceImpl implements  BannerService {
    @Autowired
   private BannerDAO bannerDAO;

    public BannerDAO getBannerDAO() {
        return bannerDAO;
    }

    public void setBannerDAO(BannerDAO bannerDAO) {
        this.bannerDAO = bannerDAO;
    }

    //展示所有菜单
    public List<Banner> showAll() {
        List<Banner> banners = bannerDAO.queryAll();
        return  banners;
    }

    @Override
    @LogAnnoaction(name = "删除一张轮播图")
    public void deleteBanner(String id) {
        bannerDAO.deleteOne(id);
    }

    @Override
    @LogAnnoaction(name = "修改一张轮播图")
    public void updateBanner(String id,String status) {
        bannerDAO.updateOne(id,status);
    }

    @Override
    @LogAnnoaction(name = "增加一张轮播图")
    public void addBanner(Banner banner) {
        bannerDAO.addOne(banner);
    }


}

