package com.mybaits.test.demo.dao;

import com.mybaits.test.demo.bean.Address;
import com.mybaits.test.demo.bean.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AddressDao {

    public Address getAddressById(Integer id);
    @Select("select * from address where uid=#{uid}")   //select查询
    public List<Address> getAddressByUidWithNoInfo(Integer uid);
    public List<Address> getAddressByUid(Integer uid);
    @Insert("INSERT INTO `address`(name,address,uid)\n" +     //insert插入语句
            " VALUES(#{name},#{address},#{uid} )")
    public  int insertAddress(Address address);
    @Update("UPDATE   `address` SET name=#{name},address=#{address},uid=#{uid}  " +             //更新语句
            " WHERE id=#{id}")
    public  int updateAddress(Address address);
    @Delete(" delete from `address` where id=#{id}")            //删除语句
    public  int deleteAddress(Address address);

}
