package com.mybaits.test.demo.service;

import com.mybaits.test.demo.bean.Task;
import com.mybaits.test.demo.bean.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface TaskService {

    public Task getTaskById(Integer id);
    public  boolean insertTask(Task task);
    public  boolean updateTask(Task task);
    public  boolean updateTaskStatus(Task task);
    public  boolean updateTaskExeNum(Task task,int type);
    public int deleteTask(int id);
    public int  counttask();

    public List<Task> getTastList();
    public List<Task> getTastListByUid(Integer id);
    public List<Task> getTastListBySchool(String school,Integer gender);
}
