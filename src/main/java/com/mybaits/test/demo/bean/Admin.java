package com.mybaits.test.demo.bean;

public class Admin {

    private  Integer id;
    private  String name;
    private  String pwd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Admin() {

    }

    public Admin(String name, String pwd) {

        this.name = name;
        this.pwd = pwd;
    }
}
