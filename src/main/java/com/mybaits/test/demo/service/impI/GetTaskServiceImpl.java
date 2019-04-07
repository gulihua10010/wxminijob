package com.mybaits.test.demo.service.impI;

import com.mybaits.test.demo.bean.GetTask;
import com.mybaits.test.demo.dao.GetTaskDao;
import com.mybaits.test.demo.service.GetTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class GetTaskServiceImpl implements GetTaskService {
    @Autowired
    GetTaskDao dao;
    @Override
    public List<GetTask> getGetTaskByTid(Integer tid) {
        return dao.getGetTaskByTid(tid);
    }

    @Override
    public List<GetTask> getGettaskbyUidAndTid( Integer uid,Integer tid) {
        return dao.getGettaskbyUidAndTid(uid,tid);
    }

    @Transactional
    @Override
    public boolean insertGetTask(GetTask getTask) {
    if (getTask!=null&&getTask.getTid()!=null&&getTask.getUid()!=null){

        try {
            if (dao.insertGetTask(getTask)>0){
                return true;
            }else{
                return  false;
            }
        } catch (Exception e) {
            throw  new RuntimeException("接单失败"+e.getMessage());

        }
    }else{
        throw  new RuntimeException("接单不能为空");
    }
    }

    @Transactional
    @Override
    public boolean updateGetTask(GetTask getTask) {
        if (getTask!=null&&getTask.getTid()!=null&&getTask.getUid()!=null){

            try {
                if (dao.updateGetTask(getTask)>0){
                    return true;
                }else{
                    return  false;
                }
            } catch (Exception e) {
                throw  new RuntimeException("修改接单失败"+e.getMessage());

            }
        }else{
            throw  new RuntimeException("接单不能为空");
        }
    }
    @Transactional
    @Override
    public boolean deleteGetTask(GetTask getTask) {
        GetTask getTask1=dao.getGetTaskByTidAndUid(getTask.getTid(),getTask.getUid());

        if (getTask!=null&&getTask1.getId()!=null){

            try {
                if (dao.deleteGetTask(getTask1)>0){
                    return true;
                }else{
                    return  false;
                }
            } catch (Exception e) {
                throw  new RuntimeException("删除接单失败"+e.getMessage());

            }
        }else{
            throw  new RuntimeException("接单不能为空");
        }
    }
}
