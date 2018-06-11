package com.baizhi.dao;
import com.baizhi.entity.Ablum;
import com.baizhi.entity.Banner;
import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AblumDAO {
    //展示所有专辑以及章节
    public List<Ablum> queryAll();
    //根据专辑id查询专辑详情
    public Ablum queryOne(String id);
    public Ablum queryOne1(String id);
    //添加专辑
    public void addOne(Ablum ablum);
    //添加章节
    public void addChapter(Chapter chapter);

}
