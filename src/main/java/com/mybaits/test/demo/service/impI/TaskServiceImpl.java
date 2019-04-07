package com.mybaits.test.demo.service.impI;

import com.mybaits.test.demo.bean.Task;
import com.mybaits.test.demo.bean.User;
import com.mybaits.test.demo.dao.TaskDao;
import com.mybaits.test.demo.service.TaskService;
import com.mybaits.test.demo.service.UserService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskServiceImpl  implements TaskService {
    @Autowired
    TaskDao dao;
    @Autowired
    UserService userService;
    @Override
    public Task getTaskById(Integer id) {
        return dao.getTaskById(id);
    }

    @Transactional
    @Override
    public boolean insertTask(Task task) {
        if (task!=null&&task.getTitle()!=null){

            try {
                User u=userService.getUserById(task.getPublishUserid());

                task.setCreateTime(new Timestamp(new Date().getTime()));
                task.setStatus(1);
                task.setExeNum(0);
                task.setPaymentType(1);
                task.setSchool(u.getSchool());

                if (dao.insertTask(task)>0){
                    return true;
                }else{
                    return  false;
                }
            } catch (Exception e) {
                throw  new RuntimeException("发布任务失败"+e.getMessage());

            }
        }else{
            throw  new RuntimeException("任务不能为空");
        }
    }

    @Transactional
    @Override
    public boolean updateTask(Task task) {

        if (task!=null&&task.getTitle()!=null&&task.getId()!=null){
        Task t=this.getTaskById(task.getId());
        t.setPayment(task.getPayment());
        t.setPic1(task.getPic1());
        t.setPic2(task.getPic2());
        t.setPic3(task.getPic3());
        t.setDesc(task.getDesc());
        t.setFinshTime(task.getFinshTime());
        t.setPersonNum(task.getPersonNum());
        t.setPlace(task.getPlace());
        t.setPrivateInfo(task.getPrivateInfo());
        t.setTitle(task.getTitle());

            try {
                if (dao.updateTask(t)>0){
                    return true;
                }else{
                    return  false;
                }
            } catch (Exception e) {
                throw  new RuntimeException("更新任务失败"+e.getMessage());

            }
        }else{
            throw  new RuntimeException("任务不能为空");
        }
    }
    @Transactional
    @Override
    public boolean updateTaskStatus(Task task) {

        if (task!=null&&task.getId()!=null){
            Task t=this.getTaskById(task.getId());
           t.setStatus(task.getStatus());
           if (task.getStatus()==4){
               t.setEndTime(new Timestamp(new Date().getTime()));
           }

            try {
                if (dao.updateTask(t)>0){
                    return true;
                }else{
                    return  false;
                }
            } catch (Exception e) {
                throw  new RuntimeException("更新任务失败"+e.getMessage());

            }
        }else{
            throw  new RuntimeException("任务不能为空");
        }
    }
    @Transactional
    @Override
    public boolean updateTaskExeNum(Task task,int type) {

        if (task!=null&&task.getId()!=null){
            Task t=this.getTaskById(task.getId());
            Integer exenum=t.getExeNum();
            System.out.println(exenum);
            if (type==-1){
                 exenum--;
                System.out.println(exenum);
                 if (exenum==0){
                     t.setStatus(1);
                 }else{
                     t.setStatus(2);
                 }
                 t.setExeNum(exenum);
                System.out.println(t);
            }else if (type==1){
                exenum++;
                if (exenum>=t.getPersonNum()){
                    t.setStatus(3);
                }else{
                    t.setStatus(2);
                }
                t.setExeNum(exenum);
            }

            try {
                if (dao.updateTask(t)>0){
                    return true;
                }else{
                    return  false;
                }
            } catch (Exception e) {
                throw  new RuntimeException("更新任务失败"+e.getMessage());

            }
        }else{
            throw  new RuntimeException("任务不能为空");
        }
    }
    @Transactional
    @Override
    public int deleteTask(int id) {
        Task task=this.getTaskById(id);
        if (task.getId()!=null){

            if (task.getStatus()==2||task.getStatus()==3){
                return  0;
            }

            try {

                if (dao.deleteTask(task)>0){
                    return 1;
                }else{
                    return  -1;
                }
            } catch (Exception e) {
                throw  new RuntimeException("删除任务失败"+e.getMessage());


            }
        }else{
            throw  new RuntimeException("任务不能为空");

        }
    }

    @Override
    public int counttask() {
        return dao.counttask();
    }

    @Override
    public List<Task> getTastList() {
        return dao.getTaskList();
    }

    @Override
    public List<Task> getTastListByUid(Integer id) {
        return dao.getTaskListByuid(id);
    }

    @Override
    public List<Task> getTastListBySchool(String school,Integer gender){
        if (gender==0){
            return dao.getTaskListBySchool(school);
        }else {
            if (school==""){
                return dao.getTaskListByGedner(gender );

            }else{
                Map<String,Object> map=new HashMap<>();
                map.put("school",school);
                map.put("gender",gender);
                return dao.getTaskListBySchoolandGender(map );
            }


        }
    }
}
