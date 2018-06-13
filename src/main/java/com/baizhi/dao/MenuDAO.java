package com.baizhi.dao;
import com.baizhi.entity.Menu;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MenuDAO {
    //展示所有菜单
    public List<Menu> queryAll();
}
