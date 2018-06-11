package com.baizhi.entity;

import java.util.List;

public class All {
    private List<Banner> banners;
    private List<Ablum> ablums;
    private List<Article> articles;

    public All() {
    }

    @Override
    public String toString() {
        return "All{" +
                "banners=" + banners +
                ", ablums=" + ablums +
                ", articles=" + articles +
                '}';
    }

    public All(List<Banner> banners, List<Ablum> ablums, List<Article> articles) {
        this.banners = banners;
        this.ablums = ablums;
        this.articles = articles;
    }

    public List<Banner> getBanners() {

        return banners;
    }

    public void setBanners(List<Banner> banners) {
        this.banners = banners;
    }

    public List<Ablum> getAblums() {
        return ablums;
    }

    public void setAblums(List<Ablum> ablums) {
        this.ablums = ablums;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
