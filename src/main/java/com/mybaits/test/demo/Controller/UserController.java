package com.mybaits.test.demo.Controller;

import com.alibaba.fastjson.JSONObject;
import com.mybaits.test.demo.Common.HttpClientUtil;
import com.mybaits.test.demo.Common.JSONResult;
import com.mybaits.test.demo.Common.RedisOperator;
import com.mybaits.test.demo.bean.Address;
import com.mybaits.test.demo.bean.User;
import com.mybaits.test.demo.service.AddressService;
import com.mybaits.test.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    RedisOperator redisOperator;
@Autowired
    AddressService addressService;
    @PostMapping("/api/sendCode")
    public JSONResult sendCode(@RequestParam("phone") String phone,HttpServletRequest request){
        HttpSession session=request.getSession();
        String code="";
        Random random=new Random();
        for (int i=0;i<6;i++){
            code+=random.nextInt(10);
        }
 redisOperator.set("user_phone_code",code,60*10*1000);
        System.out.println(session.getAttribute("code_value"));
    String host ="http://cowsms.market.alicloudapi.com";
    String path= "/intf/smsapi";
    String appcode = "fedd26144fc1458faf48bfd9274a9f8b";
    Map<String  ,String> head=new HashMap<>();
    head.put("Authorization","APPCODE "+appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("mobile", phone);
        querys.put("paras", code+",10");
        querys.put("sign", "校园微服务");
        querys.put("tpid", "331");
         String res=HttpClientUtil.doGetByHead(host+path,querys,head);
        System.out.println(res);
        return  JSONResult.ok();
    }

    @GetMapping("/api/sess/{key}")
    public  Object getSession(@PathVariable("key") String key, HttpServletRequest request){
        HttpSession session=request.getSession();
        System.out.println(session.getAttribute("code_value"));
        System.out.println(session.getAttribute("code_time"));
        System.out.println(session.getAttribute(key));
        Object v=session.getAttribute(key);
        return  v;


    }

    @GetMapping("/api/ses/{key}")
    public  String getSession1(@PathVariable("key") String key, HttpServletRequest request){
        HttpSession session=request.getSession();
//        session.setAttribute("dddd","Ddddddddddd");
        String s=redisOperator.get("user_phone_code");

        return  s;
    }
    @RequestMapping("/api/saveUserData/{verification}")
    public JSONResult saveUserData(@RequestBody User user,@PathVariable("verification") String verification){
        if (user==null){
            return  JSONResult.errorMsg("用户不能为空");
        }
        if (user.getPhone()==null){
            return  JSONResult.errorMsg("手机号不能为空");
        }
        if (user.getClassName()==null){
            return  JSONResult.errorMsg("班级不能为空");
        }
        if (user.getCollege()==null){
            return  JSONResult.errorMsg("学院不能为空");
        }
        if (user.getStuNo()==null){
            return  JSONResult.errorMsg("用户不能为空");
        }
        if (user.getIdCard1()==null||user.getIdCard2()==null){
            return  JSONResult.errorMsg("身份证不能为空");
        }
        if (user.getSchool()==null){
            return  JSONResult.errorMsg("学校不能为空");
        }
        if (user.getStuCard()==null){
            return  JSONResult.errorMsg("学生证不能为空");
        }
        System.out.println(user);
        System.out.println(verification);
        System.out.println(redisOperator.get("user_phone_code"));
        if (redisOperator.get("user_phone_code")==""||redisOperator.get("user_phone_code")==null){
            return  JSONResult.errorMsg("验证码过期 ");
        }  else if (!verification.equals(redisOperator.get("user_phone_code"))){
            return  JSONResult.errorMsg("验证码错误 ");
        }else{
            User u= userService.saveOrUpdate(user,2);
            return  JSONResult.ok(u);
        }
    }
    @PostMapping("/api/getuser/{uid}")
    public JSONResult getbaseinfo(@PathVariable("uid") String uid){
        System.out.println("dddddd");
        System.out.println(uid);
    User u=userService.getUserByOpenId(uid);
        System.out.println(u);
    Map<String,String> uu=new HashMap<>();
    uu.put("usernick",u.getNickName());
    uu.put("avatarurl",u.getAvatarUrl());
    return  JSONResult.ok(uu);

    }
    @ResponseBody
    @RequestMapping(value = "/api/saveMsg/{type}/{sessionKey}" ,method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public  JSONResult saveMsg(@RequestBody JSONObject jsonObject,@PathVariable("sessionKey") String sessionKey,@PathVariable("type") Integer type){
        System.out.println(jsonObject);
        if (type==0){
            redisOperator.set("rendered_msg_"+sessionKey,jsonObject.toJSONString());

        }else if (type==1){
            redisOperator.set("msg_"+sessionKey,jsonObject.toJSONString());

        }else if (type==2){
        redisOperator.del("msg_"+sessionKey);
        }

return  JSONResult.ok();
    }

    @ResponseBody
    @RequestMapping(value = "/api/savmember/{member}" ,method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public  JSONResult saveMember(@RequestBody JSONObject jsonObject,@PathVariable("member") String member ){
        System.out.println(jsonObject);
        redisOperator.set("member"+member,jsonObject.toJSONString());
        return  JSONResult.ok();
    }
    @ResponseBody
    @RequestMapping(value = "/api/getmber/{member}" ,method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public  String getMember(@PathVariable("member") String member ){
      String m=  redisOperator.get("member"+member);
        return m;
    }
    @ResponseBody
    @RequestMapping(value = "/api/getMsg/{type}/{sessionKey}" ,method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public  String getMsg( @PathVariable("sessionKey") String sessionKey,@PathVariable("type") Integer type){
        String msg="";
        if (type==0){
             msg= redisOperator.get("rendered_msg_"+sessionKey);

        }else if (type==1){
             msg= redisOperator.get("msg_"+sessionKey );

        }
        return  msg;
    }

    @PostMapping("/api/addAddress")
    public JSONResult addAddress(@RequestBody Address address){
        System.out.println(address);
       if (addressService.insertAddress(address)){
           return  JSONResult.ok();

       }else{
           return  JSONResult.errorMsg("添加失败");
       }
    }
    @PostMapping("/api/editAddress/{id}")
    public JSONResult editAddress(@RequestBody Address address,@PathVariable("id") Integer id){
         address.setId(id);
        if (addressService.updateAddress(address)){
            return  JSONResult.ok();

        }else{
            return  JSONResult.errorMsg("更新失败");
        }
    }
    @PostMapping("/api/getAddress/{uid}")
    public JSONResult getAddressByUid(@PathVariable("uid") Integer uid){
        List<Address> addresses=addressService.getAddressByUid(uid);
        return  JSONResult.ok(addresses);
    }

    @PostMapping("/api/getAddressbyid/{id}")
    public JSONResult getAddressByid(@PathVariable("id") Integer id){
        Address addresses=addressService.getAddressById(id);
        return  JSONResult.ok(addresses);
    }
    @PostMapping("/api/delAddress/{id}")
    public JSONResult delAddress(@PathVariable("id") Integer id){
        Address address=new Address();
        address.setId(id);
        boolean r=addressService.deleteAddress(address);
        if (r){
            return  JSONResult.ok();

        }else{
            return  JSONResult.errorMsg("删除失败");
        }
    }

    @PostMapping("/api/updatesUserStatus")
    public JSONResult updatesUserStatus(Integer id,Integer status ){
      User user=new User();
      user.setId(id);
      user.setStatus(status);
        System.out.println(user);
        boolean r=userService.updateStatus(user);
        if (r){
            return  JSONResult.ok("修改成功");

        }else{
            return  JSONResult.errorMsg("更新失败");
        }
    }

    @PostMapping("/api/getUserbyid")
    public JSONResult getUserbyid(Integer id){
        User user=userService.getUserById(id);
        return  JSONResult.ok(user);

    }
}
