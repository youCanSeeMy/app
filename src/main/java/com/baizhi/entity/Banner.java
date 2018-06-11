package com.baizhi.entity;

import java.util.Date;

public class Banner {
    private String id;
    private String title;
    private String imgPath;
    private String describle;
    private String status;
    private Date date;

    @Override
    public String toString() {
        return "Banner{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", describle='" + describle + '\'' +
                ", status='" + status + '\'' +
                ", date=" + date +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getDescrible() {
        return describle;
    }

    public void setDescrible(String describle) {
        this.describle = describle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Banner(String id, String title, String imgPath, String describle, String status, Date date) {

        this.id = id;
        this.title = title;
        this.imgPath = imgPath;
        this.describle = describle;
        this.status = status;
        this.date = date;
    }

    public Banner() {

    }
}