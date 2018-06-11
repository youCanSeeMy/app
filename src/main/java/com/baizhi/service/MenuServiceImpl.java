package com.baizhi.service;

import com.baizhi.dao.MenuDAO;
import com.baizhi.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Service
@RequestMapping(value="menu")
public class MenuServiceImpl implements  MenuService {
    @Autowired
    private MenuDAO menuDAO;
    public MenuDAO getMenuDAO() {
        return menuDAO;
    }
    public void setMenuDAO(MenuDAO menuDAO) {
        this.menuDAO = menuDAO;
    }
    //展示所有菜单
    public List<Menu> showAll() {
        List<Menu> menus =  menuDAO.queryAll();
        return  menus;
    }
}

