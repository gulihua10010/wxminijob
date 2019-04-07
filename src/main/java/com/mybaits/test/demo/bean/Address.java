package com.mybaits.test.demo.bean;

public class Address {
    private  Integer id;
    private  String name;
    private String address;
    private  Integer  Uid;
    private  User user;

    public Address() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getUid() {
        return Uid;
    }

    public void setUid(Integer uid) {
        Uid = uid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", Uid=" + Uid +
                ", user=" + user +
                '}';
    }

    public Address(String name, String address, Integer uid) {

        this.name = name;
        this.address = address;
        Uid = uid;
    }
}
