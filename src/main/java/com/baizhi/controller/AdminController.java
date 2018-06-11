package com.baizhi.controller;

import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @RequestMapping(value="/login")
    public String login(@RequestParam(value="name") String name, @RequestParam(value="password")
            String password, HttpSession session){
        Boolean flag = adminService.login(name,password);
        if(flag==true){
            session.setAttribute("name",name);
            return "redirect:/main/main.jsp";
        }else {
            return "redirect:/main/login.jsp";
        }
    }

}
