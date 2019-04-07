package com.mybaits.test.demo.bean;

import java.sql.Timestamp;

public class Art {
    private  Integer id;
    private  String title;
    private  String content;
    private Timestamp createTime;
    private  Integer sid;
    private  User user;

    @Override
    public String toString() {
        return "Art{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", sid=" + sid +
                ", user=" + user +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Art(String title, String content, Timestamp createTime) {

        this.title = title;
        this.content = content;
        this.createTime = createTime;
    }

    public Art() {

    }
}
