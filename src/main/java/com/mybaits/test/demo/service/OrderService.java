package com.mybaits.test.demo.service;

import com.mybaits.test.demo.bean.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface OrderService {


    public  Order getOrderByOrderid(   String id);
    public  Order getOrderByToken(   String token);
    public  Order getOrderByid(   Integer id);
    public List<Order> getOrderByUid( Integer uid);
    public Map<String,Object> insertOrder(Order order);
    public  boolean updateOrder(Order order);
    public  boolean deleteOrder(Order order);

}
