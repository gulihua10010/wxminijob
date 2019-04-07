package com.mybaits.test.demo.mapper;

import com.mybaits.test.demo.bean.Departments;
import org.apache.ibatis.annotations.*;

//@Mapper
public interface DepartmentMapper {
    @Select("select * from department  where id=#{id}")
    public Departments getDeptById(Integer id);
    @Delete("delete * from department where id=#{id}")
    public int deleteDept(Integer id);
    @Options(useGeneratedKeys =true,keyProperty = "id")
    @Insert("insert into department(department_name) values(#{departmentName})")
    public  int insertDept(Departments d);
    @Update("update department set department_name=#{departmentName} where id=#{id}")
    public  int update(Departments d);
}
