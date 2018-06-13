package com.baizhi.test;
import com.baizhi.dao.MenuDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class Testdao {
    @Autowired
    private MenuDAO menuDAO;
    @Test
    public void test1(){
        menuDAO.queryAll();
    }

}
