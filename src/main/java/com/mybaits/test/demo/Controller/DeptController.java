package com.mybaits.test.demo.Controller;

import com.mybaits.test.demo.bean.Departments;
import com.mybaits.test.demo.bean.Employee;
import com.mybaits.test.demo.mapper.DepartmentMapper;
import com.mybaits.test.demo.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Controller
public class DeptController {
//    @Autowired
//    DepartmentMapper departmentMapper;
//    @Autowired
//    EmployeeMapper employeeMapper;
//
//    @GetMapping("dept/{id}")
//    public Departments getDeptById(@PathVariable("id") Integer id) {
//        return departmentMapper.getDeptById(id);
//    }
//
//    @GetMapping("/dept")
//    public Departments insertDept(Departments d) {
//        departmentMapper.insertDept(d);
//        return d;
//
//    }
//
//    @GetMapping("/emp/{id}")
//    public Employee getEmp(@PathVariable("id") Integer id) {
//        return employeeMapper.getEmpById(id);
//
//    }
}
