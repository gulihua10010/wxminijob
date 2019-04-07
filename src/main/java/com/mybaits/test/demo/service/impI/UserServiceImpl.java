package com.mybaits.test.demo.service.impI;

import com.mybaits.test.demo.bean.User;
import com.mybaits.test.demo.dao.UserDao;
import com.mybaits.test.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public User getUserById(Integer id) {
       return  userDao.getUserById(id);
    }

    @Override
    public List<User> getUser() {
        return userDao.getUser();

    }

    @Override
    public User getUserByOpenId(String openId) {
        return  userDao.getUserByOpenId(openId);
    }

    @Transactional
    @Override
    public boolean insertUser(User user) {
        if (user!=null&&!user.getOpenId().equals("")&&user.getOpenId()!=null){
            user.setRegtime(new Timestamp(new Date().getTime()));
            user.setLastLoginTime(new Timestamp(new Date().getTime()));
            user.setStatus(1);
            user.setDescription("");
//            user.setPassword("");
            try {
                int res=userDao.insertUser(user);
                if (res>0){
                    return  true;
                }else{
                    return  false;
                }
            } catch (Exception e) {
                throw new RuntimeException("添加失败！"+e.getMessage());
            }

        }else{
            throw new RuntimeException("用户不能为空！");
        }
    }
@Transactional
    @Override
    public boolean updateStatus(User user) {

    if (user!=null&&user.getId()!=null){
        User u=this.getUserById(user.getId());
        u.setStatus(user.getStatus());
        System.out.println(u);
        try {
            int r=userDao.updateUesr(u);
            if (r>0){
                return  true;
            }else{
                return  false;
            }
        } catch (Exception e) {
            throw new RuntimeException("修改失败！"+e.getMessage() );
        }
    }else{
        throw new RuntimeException("修改失败！" );
    }
    }

    @Transactional
    @Override
    public boolean updateUesr(User user) {
        if (user!=null&&user.getOpenId()!=null){

            try {
                int r=userDao.updateUesr(user);
                if (r>0){
                    return  true;
                }else{
                    return  false;
                }
            } catch (Exception e) {
                throw new RuntimeException("修改失败！"+e.getMessage() );
            }
        }else{
            throw new RuntimeException("修改失败！" );
        }
    }

    @Transactional
    @Override
    public boolean deleteUser(User user) {
        if (user!=null&&user.getOpenId()!=null){

            try {
                int r=userDao.updateUesr(user);
                if (r>0){
                    return  true;
                }else{
                    return  false;
                }
            } catch (Exception e) {
                throw new RuntimeException("删除失败！"+e.getMessage() );
            }
        }else{
            throw new RuntimeException("删除失败！" );
        }
    }

    /**
     *
     * @param user
     * @param type 0 不更新详细数据  1更新详细数据 2学生审核
     * @return
     */
    @Override
    public User saveOrUpdate(User user,int type) {
        User u=userDao.getUserByOpenId(user.getOpenId());
        if (u==null){
            System.out.println(user);
            System.out.println(DigestUtils.md5DigestAsHex(user.getOpenId().getBytes()));
            user.setPassword(DigestUtils.md5DigestAsHex(user.getOpenId().getBytes()));
            System.out.println("this");
            this.insertUser(user);
            System.out.println(user);
            return  user;
        }else{
            System.out.println("this1");
            u.setLastLoginTime(new Timestamp(new Date().getTime()));
            if (type==1){
                if (user.getAvatarUrl()!=null&&!user.getAvatarUrl().equals("")){
                    u.setAvatarUrl(user.getAvatarUrl());
                }
                if (user.getCity()!=null&&!user.getCity().equals("")){
                    u.setCity(user.getCity());
                }
                if (user.getCountry()!=null&&!user.getCountry().equals("")){
                    u.setCountry(user.getCountry());
                }
                if (user.getGender()!=null&&!user.getGender().equals("")){
                    u.setGender(user.getGender());
                }
                if (user.getLanguage()!=null&&!user.getLanguage().equals("")){
                    u.setLanguage(user.getLanguage());
                }
                if (user.getNickName()!=null&&!user.getNickName().equals("")){
                    u.setNickName(user.getNickName());
                }
//                if (user.getPhone()!=null&&!user.getNickName().equals("")&&u.getPhone()==null){
//                    u.setPhone(user.getPhone());
//                }
                if (user.getProvince()!=null&&!user.getProvince().equals("")){
                    u.setProvince(user.getProvince());
                }
                if (user.getToken()!=null&&!user.getToken().equals("")){
                    u.setToken(user.getToken());
                }
                u.setStatus(2);
            }else if(type==2){
                    u.setSchool(user.getSchool());
                    u.setNickName(user.getNickName());
                    u.setGender(user.getGender());
                    u.setRealName(user.getRealName());
                    u.setCollege(user.getCollege());
                    u.setClassName(user.getClassName());
                    u.setStuNo(user.getStuNo());
                    u.setIdCard1(user.getIdCard1());
                    u.setIdCard2(user.getIdCard2());
                    u.setStuCard(user.getStuCard());
                    u.setPhone(user.getPhone());
                     u.setStatus(2);
                System.out.println("dddddd");
                System.out.println(u);
//                         u.setStatus(3);

            }

           boolean r= this.updateUesr(u);
            System.out.println(r);
            return u;
        }

    }

    @Override
    public List<User>  getUserByStatus(Integer status) {
        return userDao.getUserByStatus(status);
    }

    @Override
    public int countUser() {
      return   userDao.countUser();
    }

    @Override
    public List<User> getUserbyName(String keyword) {
        return userDao.getUserbyName(keyword);
    }
}
