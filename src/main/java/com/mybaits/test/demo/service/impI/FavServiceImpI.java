package com.mybaits.test.demo.service.impI;

import com.mybaits.test.demo.bean.Fav;
import com.mybaits.test.demo.dao.FavDao;
import com.mybaits.test.demo.service.FavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class FavServiceImpI implements FavService {
    @Autowired
    FavDao dao;

    @Override
    public List<Fav> getFavByUid(Integer uid) {
        return dao.getFavByUid(uid);
    }

    @Override
    public Fav getFavByUidAndTid(Integer tid, Integer uid) {
        return dao.getFavByUidAndTid(tid, uid);
    }

    @Transactional
    @Override
    public boolean insertFav(Fav fav) {
        if (fav != null && fav.getTid() != null && fav.getUid() != null) {
            try {
//            fav.setUid(1);
                fav.setTime(new Timestamp(new Date().getTime()));
                if (dao.insertFav(fav) > 0) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                throw new RuntimeException("收藏失败" + e.getMessage());

            }
        } else {
            throw new RuntimeException("藏不能为空");
        }

    }

    @Transactional
    @Override
    public boolean updateFav(Fav fav) {
        if (fav != null && fav.getId() != null) {
            try {
                if (dao.updateFav(fav) > 0) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                throw new RuntimeException("更新收藏失败" + e.getMessage());

            }
        } else {
            throw new RuntimeException("藏不能为空");
        }
    }

    @Transactional
    @Override
    public boolean deleteFav(Fav fav) {
        if (fav != null && fav.getUid() != null && fav.getTid() != null) {
            Fav fav1 = getFavByUidAndTid(fav.getTid(), fav.getUid());
            if (fav1 != null) {
                try {
                    if (dao.deleteFav(fav1) > 0) {
                        return true;
                    } else {
                        return false;
                    }
                } catch (Exception e) {
                    throw new RuntimeException("删除收藏失败" + e.getMessage());

                }
            } else {
                throw new RuntimeException("藏不能为空");

            }

        } else {
            throw new RuntimeException("藏不能为空");
        }
    }
}
