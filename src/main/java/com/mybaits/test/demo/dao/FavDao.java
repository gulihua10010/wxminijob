package com.mybaits.test.demo.dao;

import com.mybaits.test.demo.bean.Address;
import com.mybaits.test.demo.bean.Fav;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface FavDao {
    @Select("select * from fav where uid=#{uid}")
    @Results({
            @Result(property = "task" ,column = "tid" ,one = @One(select = "com.mybaits.test.demo.dao.TaskDao.getTaskById"))
    })
    public  List<Fav> getFavByUid(Integer uid);
    @Select("select * from fav where uid=#{uid} and tid=#{tid} order by time")
    @Results({
            @Result(property = "task" ,column = "tid" ,one = @One(select = "com.mybaits.test.demo.dao.TaskDao.getTaskById"))
    })
    public   Fav getFavByUidAndTid(@Param("tid") Integer tid,@Param("uid") Integer uid);
    @Insert("INSERT INTO `fav`(tid,uid,time)\n" +
            " VALUES( #{tid},#{uid},#{time} )")
    public  int insertFav(Fav fav);
    @Update("UPDATE   `fav` SET tid=#{tid},uid=#{uid} ,time=#{time} " +
            " WHERE id=#{id}")
    public  int updateFav(Fav fav);
    @Delete(" delete from `fav` where id=#{id}")
    public  int deleteFav(Fav addrefavss);

}
