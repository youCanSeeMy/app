package com.baizhi.dao;

import com.baizhi.entity.Article;

import java.util.List;

public interface ArticleDAO {
    //查询所有文章
    List<Article> queryAll();
    //根据id查询文章
    Article queryOne(String id);
    //根据上师id查询文章
    List<Article> queryByTid(String tId);
    List<Article> queryByNotTid(String tId);

}