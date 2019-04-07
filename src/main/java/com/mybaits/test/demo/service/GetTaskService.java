package com.mybaits.test.demo.service;

import com.mybaits.test.demo.bean.GetTask;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface GetTaskService {
    public List<GetTask> getGetTaskByTid(Integer tid);
    public List<GetTask> getGettaskbyUidAndTid(Integer uid,Integer tid);

    public  boolean insertGetTask(GetTask getTask);
    public  boolean updateGetTask(GetTask getTask);
    public  boolean deleteGetTask(GetTask getTask);
}