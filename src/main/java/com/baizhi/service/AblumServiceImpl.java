package com.baizhi.service;

import com.baizhi.annoation.LogAnnoaction;
import com.baizhi.dao.AblumDAO;
import com.baizhi.entity.Ablum;
import com.baizhi.entity.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AblumServiceImpl implements  AblumService {
    @Autowired
    private AblumDAO ablumDAO;
    @Override
    public List<Ablum> showAll() {
        List<Ablum> ablums = ablumDAO.queryAll();
        return  ablums;
    }

    @Override
    public Ablum showOne(String id) {
        return ablumDAO.queryOne(id);
    }

    @Override
    public Ablum showOne1(String id) {
        return  ablumDAO.queryOne1(id);
    }

    @Override
    @LogAnnoaction(name = "增加一张专辑")
    public void addOne(Ablum ablum) {
        ablumDAO.addOne(ablum);
    }

    @Override
    @LogAnnoaction(name = "增加一章章节")
    public void addChapter(Chapter chapter) {
        ablumDAO.addChapter(chapter);
    }
}
