package com.mybaits.test.demo.service.impI;

import com.mybaits.test.demo.Common.WebUtils;
import com.mybaits.test.demo.bean.Order;
import com.mybaits.test.demo.dao.OrderDao;
import com.mybaits.test.demo.service.OrderService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    public  static  final String ORDER_TIME="yyyyMMddHHmmsssss";
    @Autowired
    OrderDao dao;

    @Override
    public Order getOrderByid(Integer id) {
        return dao.getOrderByid(id);
    }
    @Override
    public Order getOrderByOrderid(String id) {
        return dao.getOrderByOrderid(id);
    }

    @Override
    public Order getOrderByToken(String token) {
        return dao.getOrderByToken(token);
    }

    @Override
    public List<Order> getOrderByUid(Integer uid) {
        return dao.getOrderByUid(uid);
    }

    @Transactional
    @Override
    public Map<String,Object> insertOrder(Order order) {
        Map<String,Object> map=new HashMap<>();

        Order o=this.getOrderByToken(order.getToken());
        if (o!=null){
            map.put("code","-1");
            map.put("data","不能重复提交订单");
            return  map;

        }
        if (order!=null&&order.getTastid()!=null&&order.getUid()!=null){
            SimpleDateFormat sdf=new SimpleDateFormat(ORDER_TIME);
            Calendar calendar=Calendar.getInstance();
            String orderid=sdf.format(calendar.getTime())+WebUtils.randomNum(10);
            order.setOrderid(orderid);
            order.setCreateTime(new Timestamp(new Date().getTime()));
            order.setUpdateTime(new Timestamp(new Date().getTime()));
            order.setStatus(1);
            order.setPaymentType(1);
            try {
                if (dao.insertOrder(order)>0){
                    map.put("code","1");
                    map.put("data",order.getOrderid());
                    return  map;
                }else{
                    map.put("code","-1");
                    map.put("data","提交失败");
                    return  map;
                }
            } catch (Exception e) {
                throw  new RuntimeException("提交订单失败"+e.getMessage());

            }
        }else{
            throw  new RuntimeException("订单不能为空");
        }
    }
    @Transactional
    @Override
    public boolean updateOrder(Order order) {

        Order order1=dao.getOrderByid(order.getId());
        if (order!=null&&order1.getOrderid()!=null ){
            order1.setStatus(order.getStatus());
            order1.setUpdateTime(new Timestamp(new Date().getTime()));
            try {
                if (dao.updateOrder(order1)>0){
                    return true;
                }else{
                    return  false;
                }
            } catch (Exception e) {
                throw  new RuntimeException("更新订单失败"+e.getMessage());

            }
        }else{
            throw  new RuntimeException("订单不能为空");
        }
    }

    @Transactional
    @Override
    public boolean deleteOrder(Order order) {
        if (order!=null ){

            try {
                if (dao.deleteOrder(order)>0){
                    return true;
                }else{
                    return  false;
                }
            } catch (Exception e) {
                throw  new RuntimeException("提交订单失败"+e.getMessage());

            }
        }else{
            throw  new RuntimeException("订单不能为空");
        }
    }
}
