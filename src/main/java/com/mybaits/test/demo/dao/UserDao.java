package com.mybaits.test.demo.dao;

import com.mybaits.test.demo.bean.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserDao {
    @Select("SELECT *  FROM `user` where nick_name like concat('%',#{keyword},'%') or real_name like concat('%',#{keyword},'%') ")
    public  List<User> getUserbyName(String keyword);
    @Select("SELECT count(*) FROM `user`  ")
    public  int countUser();
    @Select("SELECT * FROM `user` WHERE status=#{status}")
    public   List<User>  getUserByStatus(Integer status);
    @Select("SELECT * FROM `user`  ")
    public List<User> getUser();
    @Select("SELECT * FROM `user` WHERE id=#{id}")
    public   User getUserById(Integer id);
    @Select("select * from `user` where open_id=#{openId}")
    public  User getUserByOpenId(String openId);
    @Insert("INSERT INTO `user`(open_id,nick_name,gender,language,city,province,country,password,avatar_url,phone,status,description,token,regtime,last_login_time)\n" +
            " VALUES(#{openId},#{nickName},#{gender},#{language},#{city},#{province},#{country},#{password},#{avatarUrl},#{phone},#{status}," +
            "#{description},#{token},#{regtime},#{lastLoginTime})")
    public  int insertUser(User user);
    @Update("UPDATE   `user` SET open_id=#{openId},nick_name=#{nickName},gender=#{gender}," +
            "language=#{language},city=#{city},province=#{province},country=#{country},password=#{password},avatar_url=#{avatarUrl}" +
            ",phone=#{phone},status=#{status},description=#{description},token=#{token},last_login_time=#{lastLoginTime}," +
            "real_name=#{realName},school=#{school},college=#{college},class_name=#{className},stu_no=#{stuNo},id_card1=#{idCard1},id_card2=#{idCard2},stu_card=#{stuCard}" +
            " WHERE open_id=#{openId}")
    public  int updateUesr(User user);
    @Delete(" delete from `user` where id=#{id}")
    public  int deleteUser(User user);

}
