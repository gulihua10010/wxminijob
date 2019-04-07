package com.mybaits.test.demo.bean;

import java.sql.Timestamp;

public class Order {
    private  Integer id;
    private  String orderid;
    private  Double payment;
    private  Integer paymentType;
    private  Integer status;
    private Timestamp createTime;
    private  Timestamp updateTime;
    private  Timestamp EndTime;
    private  Timestamp CloseTime;
    private  Integer uid;
    private  Integer tid;
    private User user;
    private  Task task;
    private  String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
//    public Integer getTid() {
//        return tid;
//    }
//
//    public void setTid(Integer tid) {
//        this.tid = tid;
//    }

    public Order() {
    }

    public Order(String orderid, Double payment, Integer paymentType, Integer status, Timestamp createTime, Timestamp updateTime, Timestamp endTime, Timestamp closeTime, Integer uid, Integer tid) {
        this.orderid = orderid;
        this.payment = payment;
        this.paymentType = paymentType;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        EndTime = endTime;
        CloseTime = closeTime;
        this.uid = uid;
        this.tid = tid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderid='" + orderid + '\'' +
                ", payment=" + payment +
                ", paymentType=" + paymentType +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", EndTime=" + EndTime +
                ", CloseTime=" + CloseTime +
                ", uid=" + uid +
                ", tastid=" + tid +
                ", user=" + user +
                ", task=" + task +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Timestamp getEndTime() {
        return EndTime;
    }

    public void setEndTime(Timestamp endTime) {
        EndTime = endTime;
    }

    public Timestamp getCloseTime() {
        return CloseTime;
    }

    public void setCloseTime(Timestamp closeTime) {
        CloseTime = closeTime;
    }


    public Integer getTastid() {
        return tid;
    }

    public void setTastid(Integer tastid) {
        this.tid = tastid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
