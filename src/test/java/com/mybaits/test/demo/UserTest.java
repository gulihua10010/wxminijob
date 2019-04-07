package com.mybaits.test.demo;

import com.mybaits.test.demo.Common.HttpClientUtil;
import com.mybaits.test.demo.Common.RedisOperator;
import com.mybaits.test.demo.bean.Admin;
import com.mybaits.test.demo.bean.Art;
import com.mybaits.test.demo.bean.User;
import com.mybaits.test.demo.dao.AdminDao;
import com.mybaits.test.demo.dao.UserDao;
import com.mybaits.test.demo.service.ArtService;
import com.mybaits.test.demo.service.impI.UserServiceImpl;
import org.bouncycastle.crypto.Digest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
//    @Autowired
    User user;
    @Autowired
    UserDao userDao;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    RedisOperator redisOperator;
@Autowired
    AdminDao adminDao;
@Autowired
    ArtService artService;
    @Test
    public void  Test(){
        user=new User(11,"open11","nick111111111","zh","city"
                ,"js","zn","11","a","1232222",new Timestamp(new Date().getTime()),
                new Timestamp(new Date().getTime()),1,"dd","token");
//        int r=userDao.updateUesr(user);
//        System.out.println(r);
//        User u=userDao.getUserByOpenId("111");
//        System.out.println(u);0
//        User u=userService.saveOrUpdate(user,1);
//        System.out.println(u);
//        userService.saveOrUpdate(user,1);

        user=userDao.getUserById(10);

    }
    @Test
    public  void sendTest(   ){
        String code="";
        Random random=new Random();
        for (int i=0;i<6;i++){
            code+=random.nextInt(10);
        }
        long d=1550559626179L;
        System.out.println(code);
        long a=new Date().getTime()-d;
        System.out.println(a/1000);
//        String host ="http://cowsms.market.alicloudapi.com";
//        String path= "/intf/smsapi";
//        String appcode = "fedd26144fc1458faf48bfd9274a9f8b";
//        Map<String  ,String> head=new HashMap<>();
//        head.put("Authorization","APPCODE "+appcode);
//        Map<String, String> querys = new HashMap<String, String>();
//        querys.put("mobile", "18360866171");
//        querys.put("paras", "1234,2");
//        querys.put("sign", "校园微服务");
//        querys.put("tpid", "331");
//        String res=HttpClientUtil.doGetByHead(host+path,querys,head);
//        System.out.println(res);


    }
    @Test
    public  void tttt(){

//        Admin admin=new Admin("admin",DigestUtils.md5DigestAsHex("admin".getBytes()));
////        adminDao.insertAdmin(admin);
//     Admin admin1  =  adminDao.getAdminByNameAndPwd(admin);
//        System.out.println(admin1);
//int i=userDao.countUser();
//        System.out.println(i);
//        List<Art> arts=artService.getArt();
//        System.out.println(arts);

        String host ="http://cowsms.market.alicloudapi.com";
        String path= "/intf/smsapi";
        String appcode = "fedd26144fc1458faf48bfd9274a9f8b";
        Map<String  ,String> head=new HashMap<>();
        head.put("Authorization","APPCODE "+appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("mobile", "18360866171");
        querys.put("paras",1+",10");
        querys.put("sign", "校园微服务");
        querys.put("tpid", "331");
        String res=HttpClientUtil.doGetByHead(host+path,querys,head);

    }
}
