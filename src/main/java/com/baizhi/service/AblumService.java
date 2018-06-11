package com.baizhi.service;

import com.baizhi.entity.Ablum;
import com.baizhi.entity.Chapter;

import java.util.List;

public interface AblumService {
    //展示所有专辑
    public List<Ablum> showAll();
    //根据专辑id查询专辑详情
    public Ablum showOne(String id);
    public Ablum showOne1(String id);
    //添加专辑
    public void addOne(Ablum ablum);
    //添加章节
    public void addChapter(Chapter chapter);
}
