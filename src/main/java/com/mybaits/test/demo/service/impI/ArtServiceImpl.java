package com.mybaits.test.demo.service.impI;

import com.mybaits.test.demo.bean.Art;
import com.mybaits.test.demo.dao.ArtDao;
import com.mybaits.test.demo.service.ArtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
@Service
public class ArtServiceImpl implements ArtService {

    @Autowired
    ArtDao dao;

    @Override
    public List<Art> getArtBysid(Integer sid) {
        return  dao.getArtBysid(sid);
    }

    @Override
    public List<Art> getArts0() {
        return dao.getArts0();
    }

    @Override
    public List<Art> getArt() {
        return  dao.getArt();
    }

    @Override
    public Art getArtByid(Integer id) {
        return dao.getArtByid(id);
    }

    @Transactional
    @Override
    public boolean insertArt(Art art) {
        if (art!=null&&art.getTitle()!=null){
        art.setCreateTime(new Timestamp(new Date().getTime()));
            try {
                if (dao.insertArt(art)>0){
                    return true;
                }else{
                    return  false;
                }
            } catch (Exception e) {
                throw  new RuntimeException("添加公告失败"+e.getMessage());

            }
        }else{
            throw  new RuntimeException("公告不能为空");
        }
    }
    @Transactional
    @Override
    public boolean updateArt(Art art) {
        if (art!=null&&art.getId()!=null){

            try {
                if (dao.updateArt(art)>0){
                    return true;
                }else{
                    return  false;
                }
            } catch (Exception e) {
                throw  new RuntimeException("修改公告失败"+e.getMessage());

            }
        }else{
            throw  new RuntimeException("公告不能为空");
        }
    }
    @Transactional
    @Override
    public boolean deleteArt(Art art) {
        if (art!=null&&art.getId()!=null){

            try {
                if (dao.deleteArt(art)>0){
                    return true;
                }else{
                    return  false;
                }
            } catch (Exception e) {
                throw  new RuntimeException("删除公告失败"+e.getMessage());

            }
        }else{
            throw  new RuntimeException("公告不能为空");
        }
    }
}
