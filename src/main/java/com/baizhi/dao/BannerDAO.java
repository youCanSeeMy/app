package com.baizhi.dao;
import com.baizhi.entity.Banner;
import com.baizhi.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerDAO {
    //展示所有轮播图
    public List<Banner> queryAll();
    //增加轮播图
    public void addOne(Banner banner);
    //删除轮播图
    public void deleteOne(String id);
    //更新轮播图的状态
    public void updateOne(@Param("id") String id,@Param("status") String status);

}
