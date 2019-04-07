package com.mybaits.test.demo.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.mybaits.test.demo.Common.GeetestLib;
import com.mybaits.test.demo.Common.JSONResult;
import com.mybaits.test.demo.bean.Admin;
import com.mybaits.test.demo.bean.Art;
import com.mybaits.test.demo.bean.User;
import com.mybaits.test.demo.config.GeetestConfig;
import com.mybaits.test.demo.service.AdminService;
import com.mybaits.test.demo.service.ArtService;
import com.mybaits.test.demo.service.TaskService;
import com.mybaits.test.demo.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {
@Autowired
    UserService userService;

    @Autowired
    TaskService taskService;
    @Autowired
    ArtService artService;
@Autowired
    AdminService adminService;
    @RequestMapping("pages/index.html")
    public  String index(){
        return  "pages/index";

    }
    @GetMapping("/users")
    public String getUser(Model model,String keyword){
        List<User> users=null;
        if (keyword==null){
           users=userService.getUser();

        }else {
            users=userService.getUserbyName(keyword);

        }
        model.addAttribute("users",users);
        return  "pages/user";
    }

    @GetMapping("/user/{id}")
    public String getUserByid(Model model, @PathVariable("id") Integer id){
//        List<User> users=userService.getUser();
        User user=userService.getUserById(id);
        model.addAttribute("user",user);
        return  "pages/userdetail";
    }

    @GetMapping("/userssh")
    public String getUsersh(Model model){
        List<User> users=userService.getUserByStatus(2);
        model.addAttribute("users",users);
        return  "pages/usersh";
    }
    @GetMapping("/getUsershByid/{id}")
    public String getUsershByid(Model model, @PathVariable("id") Integer id){
//        List<User> users=userService.getUser();
        User user=userService.getUserById(id);
        System.out.println(user);
        model.addAttribute("user1",user);
        return  "pages/usershdetail";
    }
    @GetMapping("/index")
    public String index(Model model){
//        List<User> users=userService.getUser();
        Map<String ,Integer> map=new HashMap<>();
        map.put("usercount",userService.countUser());
        map.put("taskcount",taskService.counttask());
        System.out.println(map);
        model.addAllAttributes(map);
        return  "pages/index";
    }
    @GetMapping("/publishart")
    public String publishart(Model model){
        List<User> users=userService.getUser();
        model.addAttribute("users",users);
        return  "pages/publishart";
    }


    @GetMapping("/art")
    public String getart(Model model){
        List<Art> arts=artService.getArt();
        model.addAttribute("arts",arts);
        return  "pages/art";
    }
    @GetMapping("/artedit/{id}")
    public String artedit(Model model,@PathVariable("id") Integer id){
        List<User> users=userService.getUser();
        model.addAttribute("users",users);
        Art art =artService.getArtByid(id);
        model.addAttribute("art",art);
        return  "pages/artedit";
    }

    @ResponseBody
    @RequestMapping("/publishart")
    public JSONResult publishart(@RequestBody Art art){
        System.out.println( art);
        boolean r=artService.insertArt(art);
        if (r){
            return  JSONResult.ok("发布成功");

        }else {
            return  JSONResult.errorMsg("发布失败");
        }
    }
    @ResponseBody
    @RequestMapping("/shenhenoagree/{sid}")
    public JSONResult shenhenoagree(@RequestBody Art art,@PathVariable("sid") Integer sid){
        System.out.println( art);
        art.setTitle("审核不通过通知");
        art.setSid(sid);
        User user=new User();
        user.setId(sid);
        user.setStatus(3);
        System.out.println(user);
        userService.updateStatus(user);
        boolean r=artService.insertArt(art);
        if (r){
            return  JSONResult.ok("发送成功");

        }else {
            return  JSONResult.errorMsg("发布失败");
        }
    }
    @ResponseBody
    @RequestMapping("/editart1/{id}")
    public JSONResult editart1(@RequestBody Art art,@PathVariable("id") Integer id){
        art.setId(id);
        System.out.println( art);
        boolean r=artService.updateArt(art);
        if (r){
            return  JSONResult.ok("更新成功");

        }else {
            return  JSONResult.errorMsg("发布失败");
        }
    }

    @ResponseBody
    @RequestMapping("/delart/{id}")
    public JSONResult delart(@PathVariable("id") Integer id){
        Art art=new Art();
        art.setId(id);
        boolean r=artService.deleteArt(art);
        if (r){
            return  JSONResult.ok("删除成功");

        }else {
            return  JSONResult.errorMsg("发布失败");
        }
    }
    @GetMapping("/login")
    public String  login(Model model){
        return  "login";

    }
    @ResponseBody
    @PostMapping("/VerifyLogin")
    public JSONResult  VerifyLogin(HttpServletRequest request){

        GeetestLib gtSdk = new GeetestLib(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key(),
                GeetestConfig.isnewfailback());

        String challenge = request.getParameter(GeetestLib.fn_geetest_challenge);
        String validate = request.getParameter(GeetestLib.fn_geetest_validate);
        String seccode = request.getParameter(GeetestLib.fn_geetest_seccode);

        //从session中获取gt-server状态
        int gt_server_status_code = (Integer) request.getSession().getAttribute(gtSdk.gtServerStatusSessionKey);

        //从session中获取userid
        String userid = (String)request.getSession().getAttribute("userid");

        //自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("user_id", userid); //网站用户id
        param.put("client_type", "web"); //web:电脑上的浏览器；h5:手机上的浏览器，包括移动应用内完全内置的web_view；native：通过原生SDK植入APP应用的方式
        param.put("ip_address", "127.0.0.1"); //传输用户请求验证时所携带的IP

        int gtResult = 0;

        if (gt_server_status_code == 1) {
            //gt-server正常，向gt-server进行二次验证

            gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, param);
            System.out.println(gtResult);
        } else {
            // gt-server非正常情况下，进行failback模式验证

            System.out.println("failback:use your own server captcha validate");
            gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
            System.out.println(gtResult);
        }


        if (gtResult == 1) {
            // 验证成功

            JSONResult.ok(gtSdk.getVersionInfo());
        }
        else {
            JSONResult.errorMsg( gtSdk.getVersionInfo());
        }

        return  JSONResult.ok();
    }
    @ResponseBody
    @GetMapping("/GeetestStart")
    public JSONResult startCaptcha(HttpServletRequest request){


        GeetestLib gtSdk = new GeetestLib(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key(),
                GeetestConfig.isnewfailback());

        String resStr = "{}";

        String userid = "test";

        //自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("user_id", userid); //网站用户id
        param.put("client_type", "web"); //web:电脑上的浏览器；h5:手机上的浏览器，包括移动应用内完全内置的web_view；native：通过原生SDK植入APP应用的方式
        param.put("ip_address", "127.0.0.1"); //传输用户请求验证时所携带的IP

        //进行验证预处理
        int gtServerStatus = gtSdk.preProcess(param);

        //将服务器状态设置到session中
        request.getSession().setAttribute(gtSdk.gtServerStatusSessionKey, gtServerStatus);
        //将userid设置到session中
        request.getSession().setAttribute("userid", userid);

        resStr = gtSdk.getResponseStr();
        com.alibaba.fastjson.JSONObject  jsonObject= com.alibaba.fastjson.JSONObject.parseObject(resStr);
        Map<String,Object> map= com.alibaba.fastjson.JSONObject.toJavaObject(jsonObject,Map.class);
      return   JSONResult.ok(map);
    }
@ResponseBody
    @PostMapping("/login1")
    public JSONResult login1(@RequestBody Admin admin,HttpServletRequest request){
        Admin admin1=new Admin();
//        admin1.set
        admin1= adminService.getAdminByNameAndPwd(admin);
        if (admin==null){
            return  JSONResult.errorMsg("用户名或密码错误");
        }else {
            request.getSession().setAttribute("admin",admin);
            return JSONResult.ok("登陆成功");
        }


}

    @ResponseBody
    @GetMapping("/logout")
    public JSONResult logout( HttpServletRequest request){
   request.getSession().setAttribute("admin",null);
        return JSONResult.ok("退出成功");


    }
}
