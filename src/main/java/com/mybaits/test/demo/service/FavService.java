package com.mybaits.test.demo.service;

import com.mybaits.test.demo.bean.Fav;

import java.util.List;

public interface FavService {


    public List<Fav> getFavByUid(Integer uid);
    public   Fav getFavByUidAndTid( Integer tid,   Integer uid);
    public  boolean insertFav(Fav fav);
    public  boolean updateFav(Fav fav);
    public  boolean deleteFav(Fav fav);
}
