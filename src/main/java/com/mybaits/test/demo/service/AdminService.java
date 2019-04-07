package com.mybaits.test.demo.service;

import com.mybaits.test.demo.bean.Admin;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AdminService {

    public List<Admin> getAdmin( );
    public Admin getAdminByNameAndPwd(Admin admin);

    public boolean insertAdmin(Admin admin);

    public boolean updateAdmin(Admin admin);

    public boolean deleteAdmin(Admin admin);
}
