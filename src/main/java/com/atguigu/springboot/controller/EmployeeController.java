package com.atguigu.springboot.controller;

import com.atguigu.springboot.dao.DepartmentDao;
import com.atguigu.springboot.dao.EmployeeDao;
import com.atguigu.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * 处理所有跟员工相关的请求
 * @author Shawn.Yang
 * @create 2020-05-14-22:58
 */
@Controller
public class EmployeeController {

    @Autowired
    @Qualifier("employeeDao")
    private EmployeeDao employeeDao;

    @Autowired
    @Qualifier("departmentDao")
    private DepartmentDao departmentDao;

    /**
     * 查询所有员工，返回列表页面
     * @return
     */
    @GetMapping(value = "/emps")
    public String list(Model model){
        Collection<Employee> emps = employeeDao.getAll();
        //放入请求域中进行共享
        model.addAttribute("emps",emps);

        //thymeleaf 默认就会拼串:
        // classpath:/templates/emp/list.html
        return "emp/list";
    }

    //来到员工添加页面
    @GetMapping("/emp")
    public String toAddPage(ModelMap modelMap){
        //来到添加页面，查出所有的部门，在页面显示
        modelMap.addAttribute("depts",departmentDao.getDepartments());
        return "emp/add";
    }

    //SpringMVC 自动将请求参数和入参对象的属性进行一一绑定；要求请求参数的名字和入参的属性名一致
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        System.out.println("保存的员工信息：" + employee);
        employeeDao.save(employee);

        //来到员工列表页面
        //redirect:重定向 /代表当前项目路径
        //forward:转发
        return "redirect:/emps";
    }

    //来到员工信息编辑页面，查出员工，回显
    @GetMapping("/emp/{empId}")
    public String toEditPage(@PathVariable("empId") Integer empId,Model model){
        Employee employee = employeeDao.get(empId);
        //查出部门
        model.addAttribute("depts",departmentDao.getDepartments());
        model.addAttribute("emp",employee);
        //回到修改页面，add是一个修改添加二合一的页面
        return "emp/add";
    }

    //修改员工信息
    @PutMapping("/emp")
    public String editEmp(Employee employee){
        System.out.println("修改的员工信息：" + employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //员工删除
    @DeleteMapping("/emp/{empId}")
    public String deleteEmployee(@PathVariable("empId")Integer empId){
        employeeDao.delete(empId);
        return "redirect:/emps";
    }

}
