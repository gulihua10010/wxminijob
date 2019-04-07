package com.mybaits.test.demo.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

public class Task {
    private Integer id;
    private  String title;
    private  String desc;
    private  String privateInfo;
    private  Integer paymentType;
    private  Integer personNum;
    private  Integer status;
    private Timestamp endTime;
    private Timestamp createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp finshTime;
    private Integer publishUserid;
    private  Double payment;
    private  String type;
    private  String pic1;
    private  String pic2;
    private  String place;
    private  User user;
    private  Integer exeNum;
    private  String school;
    private  Fav fav;
//    private  String peixw;

    public Fav getFav() {
        return fav;
    }

    public void setFav(Fav fav) {
        this.fav = fav;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPic1() {
        return pic1;
    }


    public void setPic1(String pic1) {
        this.pic1 = pic1;
    }

    public String getPic2() {
        return pic2;
    }

    public void setPic2(String pic2) {
        this.pic2 = pic2;
    }

    public String getPic3() {
        return pic3;
    }

    public void setPic3(String pic3) {
        this.pic3 = pic3;
    }

    private  String pic3;

    public Task(String title, String desc, String privateInfo,  Double payment,Integer paymentType, Integer personNum, Integer status, Timestamp endTime, Timestamp createTime, Timestamp finshTime, Integer publishUserid) {
        this.title = title;
        this.desc = desc;
        this.privateInfo = privateInfo;
        this.paymentType = paymentType;
        this.personNum = personNum;
        this.status = status;
        this.endTime = endTime;
        this.createTime = createTime;
        this.finshTime = finshTime;
        this.publishUserid = publishUserid;
        this.payment = payment;
    }

    public Task() {

    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", privateInfo='" + privateInfo + '\'' +
                ", paymentType=" + paymentType +
                ", personNum=" + personNum +
                ", status=" + status +
                ", endTime=" + endTime +
                ", createTime=" + createTime +
                ", finshTime=" + finshTime +
                ", publishUserid=" + publishUserid +
                ", payment=" + payment +
                ", type='" + type + '\'' +
                ", pic1='" + pic1 + '\'' +
                ", pic2='" + pic2 + '\'' +
                ", place='" + place + '\'' +
                ", user=" + user +
                ", exeNum=" + exeNum +
                ", school='" + school + '\'' +
                ", pic3='" + pic3 + '\'' +
                '}';
    }

    public Integer getExeNum() {
        return exeNum;
    }

    public void setExeNum(Integer exeNum) {
        this.exeNum = exeNum;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public Double getPayement() {
        return payment;
    }

    public void setPayement(Double payement) {
        this.payment = payement;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrivateInfo() {
        return privateInfo;
    }

    public void setPrivateInfo(String privateInfo) {
        this.privateInfo = privateInfo;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getPersonNum() {
        return personNum;
    }

    public void setPersonNum(Integer personNum) {
        this.personNum = personNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getFinshTime() {
        return finshTime;
    }

    public void setFinshTime(Timestamp finshTime) {
        this.finshTime = finshTime;
    }

    public Integer getPublishUserid() {
        return publishUserid;
    }

    public void setPublishUserid(Integer publishUserid) {
        this.publishUserid = publishUserid;
    }
}
