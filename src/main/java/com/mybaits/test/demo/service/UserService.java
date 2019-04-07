package com.mybaits.test.demo.service;

import com.mybaits.test.demo.bean.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public User getUserById(Integer id);
    public List<User> getUser();
    public  User getUserByOpenId(String openId);
    public  boolean insertUser(User user);
    public  boolean updateStatus(User user);
    public  boolean updateUesr(User user);
    public  boolean deleteUser(User user);
    public  User saveOrUpdate(User user,int type);
    public   List<User>  getUserByStatus(Integer status);
    public  int countUser();
    public  List<User> getUserbyName(String keyword);
}
