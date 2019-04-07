package com.mybaits.test.demo.Controller;

import com.alibaba.fastjson.JSONObject;
import com.mybaits.test.demo.Common.JSONResult;
import com.mybaits.test.demo.bean.GetTask;
import com.mybaits.test.demo.bean.Order;
import com.mybaits.test.demo.bean.Task;
import com.mybaits.test.demo.service.GetTaskService;
import com.mybaits.test.demo.service.OrderService;
import com.mybaits.test.demo.service.TaskService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.soap.Addressing;
import java.util.List;
import java.util.Map;

@RestController
public class OrderController {
@Autowired
    OrderService orderService;
    @Autowired
    GetTaskService getTaskService;
    @Autowired
    TaskService taskService;


    @PostMapping("/api/addorder")
    public JSONResult addOrder(Integer tid, Integer uid,String token, @RequestBody String payment){
        JSONObject jsonObject=JSONObject.parseObject(payment);
        Map<String ,Object> map =JSONObject.toJavaObject(jsonObject,Map.class);
        System.out.println(map.get("payment"));
        Order order=new Order();
        order.setTastid(tid);
        order.setToken(token);
        order.setUid(uid);
        order.setPayment(Double.parseDouble(map.get("payment").toString()));
        Task task=new Task();
        task.setId(tid);
        taskService.updateTaskExeNum(task,1);
        Map<String ,Object> res=orderService.insertOrder(order);
        if (res.get("code").equals("-1")){
            return  JSONResult.errorMsg(res.get("data").toString());
        }else if (res.get("code").equals("1")){
            GetTask getTask=new GetTask();
            getTask.setTid(tid);
            getTask.setUid(uid);

            boolean r=getTaskService.insertGetTask(getTask);

            return  JSONResult.ok(res.get("data"));
        }else{
            return  JSONResult.errorMsg("接单失败");
        }



    }

    @PostMapping("/api/getOrderByuid")
    public JSONResult  getOrderByuid(Integer  uid){
       List<Order> orders=orderService.getOrderByUid(uid);
        return  JSONResult.ok(orders);

    }
    @PostMapping("/api/getOrderByorderid")
    public JSONResult  getOrderByorderid(String  id){
        Order  order =orderService.getOrderByOrderid(id);
        return  JSONResult.ok(order);

    }
    @PostMapping("/api/getOrderByid")
    public JSONResult  getOrderByid(Integer  id){
        Order orders=orderService.getOrderByid(id);
        return  JSONResult.ok(orders);

    }


    @PostMapping("/api/delOrder")
    public JSONResult  delOrder(Integer  id,Integer tid,Integer uid){
        Order order=new Order();
        GetTask getTask=new GetTask();
        order.setId(id);
        getTask.setUid(uid);
        getTask.setTid(tid);
        Task task=new Task();
        task.setId(tid);
        boolean o=orderService.deleteOrder(order);
        boolean r=getTaskService.deleteGetTask(getTask);
        boolean tr=taskService.updateTaskExeNum(task,-1);
        if (o&& r&&tr){
            return  JSONResult.ok(order);
        }else {
            return  JSONResult.errorMsg("接单失败");
        }

    }

    @PostMapping("/api/updateOrderStatus")
    public JSONResult  updateOrderStatus(Integer  id ,Integer status){
        Order order=new Order();
        order.setStatus(status);
        order.setId(id);
        boolean o=orderService.updateOrder(order);
        if (o){
            return  JSONResult.ok(order);
        }else {
            return  JSONResult.errorMsg("接单失败");
        }

    }
}
