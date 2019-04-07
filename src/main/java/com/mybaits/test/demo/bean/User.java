package com.mybaits.test.demo.bean;


import java.sql.Time;
import java.sql.Timestamp;

public class User {
    private  Integer id;
    private  Integer gender;
    private  String openId;
    private  String nickName;
    private  String language;
    private  String city;
    private  String province;
    private  String country;
    private  String password;
    private  String avatarUrl;
    private  String phone;
    private Timestamp regtime;
    private Timestamp lastLoginTime;
    private  Integer status;
    private  String description;
    private  String token;
    private  String realName;
    private  String school;
    private  String college;
    private  String className;
    private  String stuNo;
    private  String idCard1;
    private  String idCard2;
    private  String stuCard;
    private  WxSession wxSession;
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getIdCard1() {
        return idCard1;
    }

    public void setIdCard1(String idCard1) {
        this.idCard1 = idCard1;
    }

    public String getIdCard2() {
        return idCard2;
    }

    public void setIdCard2(String idCard2) {
        this.idCard2 = idCard2;
    }

    public String getStuCard() {
        return stuCard;
    }

    public void setStuCard(String stuCard) {
        this.stuCard = stuCard;
    }



    public User() {
    }

    public WxSession getWxSession() {
        return wxSession;
    }

    public void setWxSession(WxSession wxSession) {
        this.wxSession = wxSession;
    }

    public User(Integer gender, String openId, String nickName, String language, String city, String province, String country, String password, String avatarUrl, String phone, Timestamp regtime, Timestamp lastLoginTime, Integer status, String description, String token) {
//        this.id = id;
        this.gender = gender;
        this.openId = openId;
        this.nickName = nickName;
        this.language = language;
        this.city = city;
        this.province = province;
        this.country = country;
        this.password = password;
        this.avatarUrl = avatarUrl;
        this.phone = phone;
        this.regtime = regtime;
        this.lastLoginTime = lastLoginTime;
        this.status = status;
        this.description = description;
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", gender=" + gender +
                ", openId='" + openId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", language='" + language + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", password='" + password + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", phone='" + phone + '\'' +
                ", regtime=" + regtime +
                ", lastLoginTime=" + lastLoginTime +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", token='" + token + '\'' +
                ", realName='" + realName + '\'' +
                ", school='" + school + '\'' +
                ", college='" + college + '\'' +
                ", className='" + className + '\'' +
                ", stuNo='" + stuNo + '\'' +
                ", idCard1='" + idCard1 + '\'' +
                ", idCard2='" + idCard2 + '\'' +
                ", stuCard='" + stuCard + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getRegtime() {
        return regtime;
    }

    public void setRegtime(Timestamp regtime) {
        this.regtime = regtime;
    }

    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
