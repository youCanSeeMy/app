package com.baizhi.service;

import com.baizhi.entity.Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    //查询所有
    public List<Article> queryAll();
    //查询单个
    public Article showOne(String id);
    //查询除了该上师和该上师以外的文章
    public Map<String,List<Article>> showArticleByTeach(String name);

}
