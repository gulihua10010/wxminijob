package com.mybaits.test.demo.Controller;

import com.mybaits.test.demo.Common.JSONResult;
import com.mybaits.test.demo.bean.Fav;
import com.mybaits.test.demo.bean.GetTask;
import com.mybaits.test.demo.bean.Task;
import com.mybaits.test.demo.service.FavService;
import com.mybaits.test.demo.service.GetTaskService;
import com.mybaits.test.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TaskController {
    @Autowired
     TaskService service;

    @Autowired
    FavService favService;
    @Autowired
    GetTaskService getTaskService;



    @RequestMapping("/api/publishTask")
    public JSONResult publishTask(@RequestBody Task task){
        System.out.println(task);
        if (task==null){
            return  JSONResult.errorMsg("任务不能为空");
        }
        if (task.getPayement()==null){
            return  JSONResult.errorMsg("赏金不能为空");
        }
        if (task.getTitle()==null){
            return  JSONResult.errorMsg("标题不能为空");
        }
        if (task.getDesc()==null){
            return  JSONResult.errorMsg("任务详情不能为空");
        }
        if (task.getFinshTime()==null){
            return  JSONResult.errorMsg("结束时间不能为空");
        }
        if (task.getPersonNum()==null){
            return  JSONResult.errorMsg("人数不能为空");
        }
        if (task.getPublishUserid()==null){
            return  JSONResult.errorMsg("发布者不能为空");
        }
        if (task.getType()==null){
            return  JSONResult.errorMsg("任务类型不能为空");
        }
       boolean res= service.insertTask(task);
        if (res){
            return  JSONResult.ok();
        }else {
            return  JSONResult.errorMsg("发布失败");
        }

    }

    @RequestMapping("/api/updateTask")
    public JSONResult updateTask(@RequestBody Task task){
        System.out.println(task);
        if (task==null){
            return  JSONResult.errorMsg("任务不能为空");
        }
        if (task.getPayement()==null){
            return  JSONResult.errorMsg("赏金不能为空");
        }
        if (task.getTitle()==null){
            return  JSONResult.errorMsg("标题不能为空");
        }
        if (task.getDesc()==null){
            return  JSONResult.errorMsg("任务详情不能为空");
        }
        if (task.getFinshTime()==null){
            return  JSONResult.errorMsg("结束时间不能为空");
        }
        if (task.getPersonNum()==null){
            return  JSONResult.errorMsg("人数不能为空");
        }
        if (task.getPublishUserid()==null){
            return  JSONResult.errorMsg("发布者不能为空");
        }
        if (task.getType()==null){
            return  JSONResult.errorMsg("任务类型不能为空");
        }
        boolean res= service.updateTask(task);
        if (res){
            return  JSONResult.ok();
        }else {
            return  JSONResult.errorMsg("更新失败");
        }

    }

    @RequestMapping("/api/updateTaskstatus/{id}")
    public JSONResult updateTaskstatus(@PathVariable("status") Integer id,Integer status){
        Task task=new Task();
        task.setId(id);
        task.setStatus(status);
        boolean res= service.updateTaskStatus(task);
        if (res){
            return  JSONResult.ok();
        }else {
            return  JSONResult.errorMsg("更新失败");
        }

    }

    @RequestMapping("/api/updateTaskExeNum/{id}")
    public JSONResult updateTaskExeNum(@PathVariable("status") Integer id,Integer num){
        Task task=new Task();
        task.setId(id);
        task.setExeNum(num);
        boolean res= service.updateTaskExeNum(task,1);
        if (res){
            return  JSONResult.ok();
        }else {
            return  JSONResult.errorMsg("更新失败");
        }

    }
    @PostMapping("/api/getTaskList")
    public  JSONResult getTaskList(String school,Integer gender){
        System.out.println(school);
        System.out.println(gender);
        List<Task> taskList=null;
        if (school==""&&gender==0){
            taskList  =service.getTastList();
        }else{
             taskList=service.getTastListBySchool(school,gender);

        }
        return JSONResult.ok(taskList);

    }
    @PostMapping("/api/getTaskById")
    public  JSONResult getTaskById(Integer id){

        Task task=service.getTaskById(id);
        return  JSONResult.ok(task);

    }

    @PostMapping("/api/getTaskListByuid/{uid}")
    public  JSONResult getTaskListByUid(@PathVariable("uid") Integer uid){
        List<Task> taskList  =service.getTastListByUid(uid);
        System.out.println(uid);
        System.out.println(taskList);

        return JSONResult.ok(taskList);

    }

    @PostMapping("/api/delTaskByid/{id}")
    public  JSONResult delTaskByid(@PathVariable("id") Integer id){

      int re=service.deleteTask(id);

      if (re==1){
          return  JSONResult.ok();
      }else if (re==0){
          return  JSONResult.errorMsg("该订单已经有用户接单了，不能删除");

      }else{
          return JSONResult.errorMsg("删除失败");

      }


    }

    @PostMapping("/api/fav/{id}")
    public  JSONResult fav(@PathVariable("id") Integer id,Integer uid){
        Fav fav=new Fav();
        fav.setTid(id);
        fav.setUid(uid);
        boolean re=favService.insertFav(fav);

        if (re){
            return  JSONResult.ok();
        } else{
            return JSONResult.errorMsg("收藏失败");

        }


    }
    @PostMapping("/api/getfav/{id}")
    public  JSONResult getfav(@PathVariable("id") Integer id,Integer uid){
        System.out.println(id);
        System.out.println(uid);
       Fav fav=favService.getFavByUidAndTid(id,uid);

        if (fav!=null){
            return  JSONResult.ok();
        } else{
            return JSONResult.errorMsg("没有收藏");

        }


    }

    @PostMapping("/api/getfavlist")
    public  JSONResult getfavlist(Integer uid){

        List<Fav> fav=favService.getFavByUid(uid);

            return  JSONResult.ok(fav);


    }
    @PostMapping("/api/delfav/{id}")
    public  JSONResult delfav(@PathVariable("id") Integer id,Integer uid){
        Fav fav=new Fav();
        fav.setTid(id);
        fav.setUid(uid);
        boolean re=favService.deleteFav(fav);

        if (re){
            return  JSONResult.ok();
        } else{
            return JSONResult.errorMsg("取消收藏失败");

        }


    }
    @PostMapping("/api/getGettask/{tid}")
    public  JSONResult getGettask(@PathVariable("tid") Integer tid ){

         List<GetTask> tasks=getTaskService.getGetTaskByTid(tid);

        return  JSONResult.ok(tasks);

    }
    @PostMapping("/api/getGettaskbyUidAndTid")
    public  JSONResult getGettaskbyUidAndTid(Integer uid,Integer tid ){
        System.out.println(tid);
        System.out.println(uid);
            List<GetTask> tasks=getTaskService.getGettaskbyUidAndTid(uid,tid);

        return  JSONResult.ok(tasks);

    }

}
