package com.mybaits.test.demo.dao;

import com.mybaits.test.demo.bean.Fav;
import com.mybaits.test.demo.bean.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrderDao {
    @Select("select * from `order` where token=#{token}  ")

    public  Order getOrderByToken(  @Param("token") String token);
    @Select("select * from `order` where orderid=#{id}  ")
    @Results({
            @Result(property = "task" ,column = "tid" ,one = @One(select = "com.mybaits.test.demo.dao.TaskDao.getTaskById"))
    })
    public  Order getOrderByOrderid(  @Param("id") String id);
    @Select("select * from `order` where id=#{id}  ")
    @Results({
            @Result(property = "task" ,column = "tid" ,one = @One(select = "com.mybaits.test.demo.dao.TaskDao.getTaskById"))
    })
    public  Order getOrderByid(  @Param("id") Integer id);
    @Select("select * from `order` where uid=#{uid} order by`create_time`")
    @Results({
            @Result(property = "task" ,column = "tid" ,one = @One(select = "com.mybaits.test.demo.dao.TaskDao.getTaskById"))
    })
    public   List<Order> getOrderByUid(  @Param("uid") Integer uid);
    @Insert("INSERT INTO `order`(`orderid`,`payment`,`payment_type`,`status`,`create_time`,`update_time`,`end_time`,`close_time`,`uid`,`tid`,`token`)" +
            " VALUES( #{orderid},#{payment},#{paymentType},#{status},#{createTime},#{updateTime},#{endTime},#{closeTime},#{uid},#{tid},#{token} )")
    public  int insertOrder(Order order);
    @Update("UPDATE   `order` SET `orderid`=#{orderid},`payment`=#{payment},`payment_type`=#{paymentType},`status`=#{status},`create_time`=#{createTime}" +
            ",`update_time`=#{updateTime},`end_time`=#{endTime},`close_time`=#{closeTime},`uid`=#{uid},`tid`=#{tid} ,`token`=#{token}" +
            " WHERE id=#{id}")
    public  int updateOrder(Order order);
    @Delete(" delete from `order` where id=#{id}")
    public  int deleteOrder(Order order);
}
