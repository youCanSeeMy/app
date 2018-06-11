package com.baizhi.service;

import com.baizhi.dao.ArticleDAO;
import com.baizhi.dao.TeacherDAO;
import com.baizhi.dao.UserDAO;
import com.baizhi.entity.Article;
import com.baizhi.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDAO articleDAO;
    @Autowired
    private TeacherDAO teacherDAO;
    @Override
    public List<Article> queryAll() {
        return articleDAO.queryAll();
    }

    @Override
    public Article showOne(String id) {
        return articleDAO.queryOne(id);
    }

    //查询该上师和非该上师所著的文章
    // @Override
    public Map<String, List<Article>> showArticleByTeach(String username) {
        Teacher teacher = teacherDAO.selectByName(username);
        String tId = teacher.getId();
        Map<String,List<Article>> map = new HashMap<String, List<Article>>();
        List<Article> articles = articleDAO.queryByTid(tId);
        map.put("isTeacher",articles);

        List<Article> articles1 = articleDAO.queryByNotTid(tId);
        map.put("isNotTeacher",articles1);

        return map;

    }
}
