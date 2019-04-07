package com.mybaits.test.demo.dao;

import com.mybaits.test.demo.bean.Admin;
import com.mybaits.test.demo.bean.Art;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface AdminDao {


    @Select("select * from admin  ")
    public List<Admin> getAdmin( );
    @Select("select * from admin where name=#{name} and pwd=#{pwd}  ")
    public Admin getAdminByNameAndPwd(Admin admin);

    @Insert("INSERT INTO `admin`( `name`,`pwd` ) " +
            " VALUES( #{name},#{pwd}  )")
    public int insertAdmin(Admin admin);

    @Update("UPDATE   `admin` SET name=#{name},pwd=#{pwd}  " +
            " WHERE id=#{id}")
    public int updateAdmin(Admin admin);

    @Delete(" delete from `admin` where id=#{id}")
    public int deleteAdmin(Admin admin);
}
