package com.mybaits.test.demo;

import com.mybaits.test.demo.bean.Fav;
import com.mybaits.test.demo.dao.FavDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FavTest {
    @Autowired
    FavDao dao;

    @Test
    public void t(){
        Fav fav=new Fav(2,1);
        dao.insertFav(fav);
         Fav  favs=dao.getFavByUidAndTid(4,10);
        System.out.println(favs);

    }
}
