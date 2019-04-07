package com.mybaits.test.demo.dao;

import com.mybaits.test.demo.bean.Task;
import com.mybaits.test.demo.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface TaskDao {


    public Task getTaskById(Integer id);
    @Insert("INSERT INTO task(`title`,`type`,`desc`,`private_info`,`payment`,`person_num`,`payment_type`, `status`,`end_time`,`create_time` ,`finsh_time`,`publish_userid`" +
            ",`pic1`,`pic2`,`pic3`, `place`,`school`,`exe_num`) \n" +
            " VALUES(#{title},#{type},#{desc},#{privateInfo},#{payment},#{personNum},#{paymentType},#{status},#{endTime},#{createTime},#{finshTime},#{publishUserid},#{pic1},#{pic2},#{pic3} ,#{place},#{school},#{exeNum})")
    public  int insertTask(Task task);
    @Update("UPDATE   `task` SET `title`=#{title},type=#{type},`desc`=#{desc},`private_info`=#{privateInfo},`payment`=#{payment},`person_num`=#{personNum},`payment_type`=#{paymentType}" +
            ", `status`=#{status},`end_time`=#{endTime},`create_time` =#{createTime},`finsh_time`=#{finshTime},`pic1`=#{pic1},`pic2`=#{pic2},`pic3`=#{pic3}, `place`=#{place},`school`=#{school},`exe_num`=#{exeNum}," +
            "`publish_userid`=#{publishUserid}" +
            " WHERE id=#{id}")
    public  int updateTask(Task task);
    @Delete(" delete from `task` where id=#{id}")
    public  int deleteTask(Task task);
    @Select("SELECT count(*) FROM  `task`   " )
    public int  counttask();
    @Select("SELECT * FROM `task`  ORDER BY create_time desc" )
    @Results({
            @Result(property = "user" ,column = "publish_userid" ,one=@One(select = "com.mybaits.test.demo.dao.UserDao.getUserById"))
    })
    public List<Task> getTaskList();

    @Select("SELECT * FROM `task` where  `school` like concat('%',#{school},'%')   ORDER BY create_time desc ")
    @Results({
            @Result(property = "user" ,column = "publish_userid" ,one=@One(select = "com.mybaits.test.demo.dao.UserDao.getUserById"))
    })
    public List<Task> getTaskListBySchool(String school);
    public List<Task> getTaskListByGedner(Integer gender);
    public List<Task> getTaskListByuid(Integer uid);

    public List<Task> getTaskListBySchoolandGender(Map<String,Object> map);

}
