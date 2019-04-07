package com.mybaits.test.demo.Controller;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.mybaits.test.demo.Common.HttpClientUtil;
import com.mybaits.test.demo.Common.JSONResult;
import com.mybaits.test.demo.Common.JsonUtils;
import com.mybaits.test.demo.Common.WechatUtil;
import com.mybaits.test.demo.bean.User;
import com.mybaits.test.demo.bean.WxSession;
import com.mybaits.test.demo.bean.WxUser;
import com.mybaits.test.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WxloginController {
    @Autowired
    UserService userService;
    @PostMapping("/api/wxLogin")
    public JSONResult wxLogin(String code){
        //https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
        String url="https://api.weixin.qq.com/sns/jscode2session";
        Map<String,String> param=new HashMap<>();
        param.put("appid","wx4bc5587e69415e84");
        param.put("secret","299d44a64cafaa24c1165e41d0b6b1a3");
        param.put("js_code",code);
        param.put("grant_type","authorization_code");
      String wxRes=  HttpClientUtil.doGet(url,param);
        WxSession wxSession=JsonUtils.jsonToPojo(wxRes,WxSession.class);

        User user=new User();
        user.setOpenId(wxSession.getOpenid());

                User u= userService.saveOrUpdate(user,0);
        System.out.println(u);
//        System.out.println(u);
       u.setWxSession(wxSession);
        System.out.println(u);
//        System.out.println(wxRes);
//        System.out.println("wx_code"+code);
        return  JSONResult.ok(u);
    }
    @PostMapping("/api/userLogin")
    public JSONResult userLogin(@RequestParam("encryptedData") String  encryptedData,@RequestParam("iv") String iv,@RequestParam("token") String token){

        System.out.println(encryptedData);
        System.out.println(iv);
        System.out.println(token);
        String result= WechatUtil.decryptData(encryptedData,token,iv);
        WxUser wxUser=JsonUtils.jsonToPojo(result,WxUser.class);
        User user=new User();
        user.setOpenId(wxUser.getOpenId());
        user.setToken(token);
        user.setAvatarUrl(wxUser.getAvatarUrl());
        user.setGender(wxUser.getGender());
        user.setCity(wxUser.getCity());
        user.setCountry(wxUser.getCountry());
        user.setLanguage(wxUser.getLanguage());
        user.setNickName(wxUser.getNickName());
        user.setProvince(wxUser.getProvince());
        User u= userService.saveOrUpdate(user,1);
        return  JSONResult.ok(u);

    }
}
