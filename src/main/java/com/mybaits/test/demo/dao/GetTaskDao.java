package com.mybaits.test.demo.dao;

import com.mybaits.test.demo.bean.Fav;
import com.mybaits.test.demo.bean.GetTask;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface GetTaskDao {
    @Select("select * from get_task where tid=#{tid} and uid=#{uid}")
    public List<GetTask> getGettaskbyUidAndTid(@Param("uid")  Integer uid ,@Param("tid")  Integer tid);
    @Select("select * from get_task where tid=#{tid}")
    @Results({
            @Result(property = "user" ,column = "uid" ,one = @One(select = "com.mybaits.test.demo.dao.UserDao.getUserById"))
    })
    public List<GetTask> getGetTaskByTid(Integer tid);

    @Select("select * from get_task where tid=#{tid} and  uid=#{uid}")
    public  GetTask getGetTaskByTidAndUid(@Param("tid") Integer tid,@Param("uid")  Integer uid);

    @Insert("INSERT INTO `get_task`(tid,uid)\n" +
            " VALUES( #{tid},#{uid}  )")
    public  int insertGetTask(GetTask getTask);
    @Update("UPDATE   `get_task` SET tid=#{tid},uid=#{uid}  " +
            " WHERE id=#{id}")
    public  int updateGetTask(GetTask getTask);
    @Delete(" delete from `get_task` where id=#{id}")
    public  int deleteGetTask(GetTask getTask);
}
