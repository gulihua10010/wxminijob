package com.mybaits.test.demo.service;

import com.mybaits.test.demo.bean.Art;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ArtService {

    public  List<Art>  getArtBysid(Integer sid);
    public List<Art> getArts0( );
    public List<Art> getArt( );
    public Art getArtByid(Integer id);

    public boolean insertArt(Art art);

    public boolean updateArt(Art art);

    public boolean deleteArt(Art art);

}
