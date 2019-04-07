package com.mybaits.test.demo.mapper;

import com.mybaits.test.demo.bean.Employee;

public interface EmployeeMapper {
    public Employee getEmpById(Integer id);

    public int insertEmp(Employee e);
}
