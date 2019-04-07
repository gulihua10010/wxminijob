package com.mybaits.test.demo.bean;

public class GetTask {
    private  Integer id;
    private  Integer tid;
    private  Integer uid;
private  Task task;
private  User user;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GetTask(Integer tid, Integer uid) {
        this.tid = tid;
        this.uid = uid;
    }

    public GetTask() {

    }

    @Override
    public String toString() {
        return "GetTask{" +
                "id=" + id +
                ", tid=" + tid +
                ", uid=" + uid +
                '}';
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
}
