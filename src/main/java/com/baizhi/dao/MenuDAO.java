package com.baizhi.dao;
import com.baizhi.entity.Menu;

import java.util.List;

public interface MenuDAO {
    //展示所有菜单
    public List<Menu> queryAll();
}
