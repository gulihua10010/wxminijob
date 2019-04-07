package com.mybaits.test.demo.bean;

import java.sql.Timestamp;

public class Fav {
    private  Integer id;
    private  Integer tid;
    private  Integer uid;
    private  User user;
    private  Task task;
    private Timestamp time;

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Fav(Integer tid, Integer uid ) {
        this.tid = tid;
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Fav{" +
                "id=" + id +
                ", tid=" + tid +
                ", uid=" + uid +
                ", user=" + user +
                ", task=" + task +
                ", time=" + time +
                '}';
    }

    public Fav() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
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
