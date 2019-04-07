package com.mybaits.test.demo.service.impI;

import com.mybaits.test.demo.bean.Admin;
import com.mybaits.test.demo.bean.Order;
import com.mybaits.test.demo.dao.AdminDao;
import com.mybaits.test.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
@Service
public class AdminServiceImpl  implements AdminService {
    @Autowired
    AdminDao dao;
    @Override
    public List<Admin> getAdmin() {
        return dao.getAdmin();
    }

    @Override
    public Admin getAdminByNameAndPwd(Admin admin) {
        System.out.println(admin);

        Admin a=new Admin(admin.getName(),DigestUtils.md5DigestAsHex(admin.getPwd().getBytes()));
        System.out.println(a);
        return dao.getAdminByNameAndPwd(a);
    }

    @Transactional
    @Override
    public boolean insertAdmin(Admin admin) {

        if (admin!=null  ){
            try {
                if (dao.insertAdmin(admin)>0){
                    return true;
                }else{
                    return  false;
                }
            } catch (Exception e) {
                throw  new RuntimeException("插入管理失败"+e.getMessage());

            }
        }else{
            throw  new RuntimeException("管理不能为空");
        }
    }

    @Override
    public boolean updateAdmin(Admin admin) {

        if (admin!=null &&admin.getId()!=null ){
            try {
                if (dao.updateAdmin(admin)>0){
                    return true;
                }else{
                    return  false;
                }
            } catch (Exception e) {
                throw  new RuntimeException("插入管理失败"+e.getMessage());

            }
        }else{
            throw  new RuntimeException("管理不能为空");
        }
    }

    @Override
    public boolean deleteAdmin(Admin admin) {

        if (admin!=null  &&admin.getId()!=null){
            try {
                if (dao.deleteAdmin(admin)>0){
                    return true;
                }else{
                    return  false;
                }
            } catch (Exception e) {
                throw  new RuntimeException("插入管理失败"+e.getMessage());

            }
        }else{
            throw  new RuntimeException("管理不能为空");
        }
    }
}
