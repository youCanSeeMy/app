package com.baizhi.entity;

import com.baizhi.annoation.UserAnnoaction;

import java.util.Date;

public class User {
    @UserAnnoaction(name="编号")
    private String id;
    @UserAnnoaction(name="电话号码")
    private String phoneNum;
    @UserAnnoaction(name="用户名")
    private String username;
    @UserAnnoaction(name="密码")
    private String password;
    @UserAnnoaction(name="盐")
    private String salt;
    @UserAnnoaction(name="上师名")
    private String dharmaName;
    @UserAnnoaction(name="省份")
    private String province;
    @UserAnnoaction(name="城市")
    private String city;
    @UserAnnoaction(name="性别")
    private String sex;
    @UserAnnoaction(name="标志")
    private String sign;
    @UserAnnoaction(name="头像")
    private  String headPic;
    @UserAnnoaction(name="状态")
    private String status;
    @UserAnnoaction(name="日期")
    private Date date;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", dharmaName='" + dharmaName + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", sex='" + sex + '\'' +
                ", sign='" + sign + '\'' +
                ", headPic='" + headPic + '\'' +
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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getDharmaName() {
        return dharmaName;
    }

    public void setDharmaName(String dharmaName) {
        this.dharmaName = dharmaName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
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

    public User(String id, String phoneNum, String username, String password, String salt, String dharmaName, String province, String city, String sex, String sign, String headPic, String status, Date date) {

        this.id = id;
        this.phoneNum = phoneNum;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.dharmaName = dharmaName;
        this.province = province;
        this.city = city;
        this.sex = sex;
        this.sign = sign;
        this.headPic = headPic;
        this.status = status;
        this.date = date;
    }

    public User() {

    }
}
