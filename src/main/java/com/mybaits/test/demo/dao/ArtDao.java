package com.mybaits.test.demo.dao;

import com.mybaits.test.demo.bean.Art;
import com.mybaits.test.demo.bean.Fav;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ArtDao {

    @Select("select * from art where sid=#{sid} or sid=0 order by create_time desc ")
    public  List<Art>  getArtBysid(Integer sid);
    @Select("select * from art  where   sid=0  order by create_time desc ")
    public List<Art> getArts0( );
    @Select("select * from art  order by create_time  desc ")
    @Results({
            @Result(property = "user" ,column = "sid" ,one = @One(select = "com.mybaits.test.demo.dao.UserDao.getUserById"))
    })
    public List<Art> getArt( );
    @Select("select * from art where id=#{id}  ")
    public Art getArtByid(Integer id);

    @Insert("INSERT INTO `art`( `title`,`create_time`,`content`,`sid`) " +
            " VALUES( #{title},#{createTime},#{content} ,#{sid})")
    public int insertArt(Art art);

    @Update("UPDATE   `art` SET title=#{title},create_time=#{createTime} ,content=#{content},sid=#{sid} " +
            " WHERE id=#{id}")
    public int updateArt(Art art);

    @Delete(" delete from `art` where id=#{id}")
    public int deleteArt(Art art);

}