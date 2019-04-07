package com.mybaits.test.demo;

import com.alibaba.fastjson.JSONObject;
import com.mybaits.test.demo.Common.WebUtils;
import com.mybaits.test.demo.bean.Art;
import com.mybaits.test.demo.bean.GetTask;
import com.mybaits.test.demo.bean.Order;
import com.mybaits.test.demo.bean.Task;
import com.mybaits.test.demo.dao.ArtDao;
import com.mybaits.test.demo.dao.GetTaskDao;
import com.mybaits.test.demo.dao.OrderDao;
import com.mybaits.test.demo.dao.TaskDao;
import com.mybaits.test.demo.service.OrderService;
import com.mybaits.test.demo.service.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskTest {
    @Autowired
    TaskDao dao;

    @Autowired
    GetTaskDao getTaskDao;
    @Autowired
    ArtDao artDao;
    @Autowired
    OrderDao orderDao;
    @Autowired
    TaskService service;
@Autowired
    OrderService orderService;
    @Test
    public void testss() {
        Task task = new Task("titl 1", "des1111c", "pri", 1.1, 1, 1, 1, new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()),
                new Timestamp(new Date().getTime()), 1);
        task.setType("tt");
        task.setPic1("11");
        task.setPic2("22");
        task.setPic3("33");
//        List<Task> tasks=service.getTastListBySchool("",2);
//        System.out.println(tasks.size());
//        for (Task t: tasks
//             ) {        System.out.println(t );
//            System.out.println();
//
//
//        }
//        System.out.println(tasks);
//        boolean i=  service.insertTask(task);
//        int i=dao.insertTask(task);
//
//        task.setId(4);
//        int i=dao.updateTask(task);
//        System.out.println(i);
        Task task1 = dao.getTaskById(4);
        System.out.println(task1);

    }

    @Test
    public void test() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1 = "2019-02-21 00:03:00";
        try {
            System.out.println(sdf.format(sdf.parse(date1)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void gettest() {
        GetTask getTask = new GetTask(2, 1);
//        getTask.setId(4);
//        getTaskDao.insertGetTask(getTask);
        List<GetTask> getTasks = getTaskDao.getGetTaskByTid(2);
        System.out.println(getTasks);

    }

    @Test
    public void art() {
        Art art = new Art("111", "111", new Timestamp(new Date().getTime()));
        art.setId(4);
//        artDao.updateArt(art);
//        artDao.insertArt(art);
//            List<Art> arts=artDao.getArt();
//        System.out.println(arts);

        Art art1 = artDao.getArtByid(4);
        System.out.println(art1);
    }

    @Test
    public void order() {
        Order order = new Order("23333333", 1.1, 1, 1, new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()), 1, 1);

        orderService.insertOrder(order);
//    orderDao.insertOrder(order);
//    orderDao.insertOrder(order);
//        order.setId(1);
//orderDao.updateOrder(order);
//        List<Order> orders = orderDao.getOrderByUid(10);
//        System.out.println(orders);

    }
    @Test
    public  void test1(){
//        long date=new Date().getTime();
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmsssss");
//        Calendar calendar=Calendar.getInstance();
//        System.out.println(sdf.format(calendar.getTime()));
//        System.out.println(WebUtils.randomNum(10));

String s="{\"payment\":1}";
        JSONObject jsonObject=JSONObject.parseObject(s);
        Map<String ,Object> map =JSONObject.toJavaObject(jsonObject,Map.class);
        System.out.println(Double.valueOf( map.get("payment").toString()));
        System.out.println(Double.parseDouble( map.get("payment").toString()));
    }
}
