package com.baizhi.controller;

import com.baizhi.entity.Menu;
import com.baizhi.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
@Controller
@RequestMapping(value="/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @RequestMapping(value="/showAll",produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public List<Menu> showAll()
    {
       List<Menu> menus = menuService.showAll();
       System.out.println(menus);
       return menus;
    }
}