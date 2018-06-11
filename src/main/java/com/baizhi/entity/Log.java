package com.baizhi.entity;

import java.util.Date;

public class Log {
    private String id;
    private  String person;
    private Date date;
    private  String thing;
    private  String success;

    @Override
    public String toString() {
        return "Log{" +
                "id='" + id + '\'' +
                ", person='" + person + '\'' +
                ", date=" + date +
                ", thing='" + thing + '\'' +
                ", success='" + success + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getThing() {
        return thing;
    }

    public void setThing(String thing) {
        this.thing = thing;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Log(String id, String person, Date date, String thing, String success) {
        this.id = id;
        this.person = person;
        this.date = date;
        this.thing = thing;
        this.success = success;
    }

    public Log() {

    }
}
