package com.baizhi.controller;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.entity.ProvinceMap;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.util.UUIDUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
@RequestMapping(value="/user")
public class UserController {
    @Autowired
    private UserService userService;

    //分页展示所有用户
    @RequestMapping(value = "/showAll", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public Map<String, Object> showAll(Integer page, Integer rows) {
        Integer begin = (page - 1) * rows + 1;
        List<User> users = userService.showAll();
        Integer end = null;
        if (users.size() > page * rows) {
            end = page * rows;
        } else {
            end = users.size();
        }
        List<User> showByPage = new ArrayList<User>();
        for (int i = begin - 1; i < end; i++) {
            showByPage.add(users.get(i));
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", users.size());
        map.put("rows", showByPage);
        return map;
    }

    //改变用户的状态
    @ResponseBody
    @RequestMapping(value = "/update", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Boolean updateStatus(String id, String status) {
        try {
            userService.updateStatus(id, status);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //统计活跃用户
    @ResponseBody
    @RequestMapping(value = "/userActive", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Integer> userActive() {
        List<Integer> list1 = userService.queryDay();
        for (Integer i : list1) {
            System.out.println(i);
        }
        return userService.queryDay();
    }

    //用户统计
    @ResponseBody
    @RequestMapping(value = "/userMap", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public String userMap(String sex) {
        List<ProvinceMap> provinceMaps = userService.queryProvince(sex);
        JSONObject jsonObject = new JSONObject();
        List<String> list = new ArrayList<String>();
        for (ProvinceMap p : provinceMaps) {
            jsonObject.put("name", p.getProvince());
            jsonObject.put("value", p.getCount());
            String s1 = JSONObject.toJSONString(jsonObject);
            list.add(s1);
        }
        //如果是list集合，那边则接收不到
        return list.toString();
    }

    //用户表导出
    @ResponseBody
    @RequestMapping(value = "/exportUser", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public void exportUser(String titles, String fileds, HttpServletResponse response) {
        //将标题行，数据行分割
        String[] title = titles.split(",");
        String[] filed = fileds.split(",");

        //标题行的填充
        //创建工作铺
        Workbook workbook = new HSSFWorkbook();
        //创建工作表
        Sheet sheet = workbook.createSheet("用户表");

        //创建单元格日期样式
        CellStyle cellStyle = workbook.createCellStyle();//先创建一个样式
//        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        DataFormat dataFormat = workbook.createDataFormat();//创建一种日期形式
        short format = dataFormat.getFormat("yyyy年MM月dd日");//是怎样的日期形式
        cellStyle.setDataFormat(format);//把日期形式赋给单元格样式------》单元格

        //创建行
        Row row = sheet.createRow(0);
        //确定单元格的个数
        for (int i = 0; i < title.length; i++) {
            //创建单元格
            Cell cell = row.createCell(i);

            //填充单元格
            String s = title[i];
            cell.setCellValue(s);
        }
        //数据行的填充
        List<User> users = userService.showAll();
        //确定数据行的行数
        for (int i = 0; i < users.size(); i++) {
            Row row1 = sheet.createRow(i + 1);
            //确定单元格的个数
            for (int j = 0; j < filed.length; j++) {
                //填充单元格
                Cell cell = row1.createCell(j);
                //使单元格宽度自适应
                sheet.autoSizeColumn((short) i);
                //得到单元格填充的内容
                //得到拼接的方法名
                String methodName = "get" + filed[j].substring(0, 1).toUpperCase() + filed[j].substring(1);
                //得到类对象
                Class<? extends User> userClass = users.get(i).getClass();
                Object invoke = null;
                try {
                    //利用反射，调用方法getDeclaredMethod：返回方法对象；
                    //invoke：在不知道对象的前提下，通过配置的参数来调用方法
                    invoke = userClass.getDeclaredMethod(methodName, null).invoke(users.get(i), null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //如果得到的结果是data类型的，转换
                if (invoke instanceof Date) {
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue((Date) invoke);
                } else {
                    cell.setCellValue(invoke + "");
                }
            }

        }


        //设置响应头，响应类型
        String name = "用户表.xls";
        String fileName = null;
        try {
            //这里ISO8859-1不能改变，改变会导致表名为中文时无法显示
            fileName = new String(name.getBytes("UTF-8"), "ISO8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("content-disposition", "attachment;fileName=" + fileName);
        response.setContentType("application/vnd.ms-excel");

        //将创建的表写出去
        try {
            workbook.write(response.getOutputStream());
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    //用户表导入
    @ResponseBody
    @RequestMapping(value = "/importUser", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public void importUser() throws NoSuchMethodException {
        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook(new FileInputStream("E:\\user.xls"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<User> users = new ArrayList<User>();
        //获取文件内容,getLastRow(),得到该表最后一行下标数
        HSSFSheet sheet = workbook.getSheet("用户表");
        //获取每一行（除去标题行）

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            HSSFRow row = sheet.getRow(i);
            List list = new ArrayList();
            //获取每一列
            User user = new User();
            user.setId(UUIDUtil.createUUID());
            user.setPhoneNum(row.getCell(1).getStringCellValue());
            user.setUsername(row.getCell(2).getStringCellValue());
            user.setPassword(row.getCell(3).getStringCellValue());
            user.setSalt(row.getCell(4).getStringCellValue());
            user.setDharmaName(row.getCell(5).getStringCellValue());
            user.setProvince(row.getCell(6).getStringCellValue());
            user.setCity(row.getCell(7).getStringCellValue());
            user.setSex(row.getCell(8).getStringCellValue());
            user.setSign(row.getCell(9).getStringCellValue());
            user.setHeadPic(row.getCell(10).getStringCellValue());
            user.setStatus(row.getCell(11).getStringCellValue());
            user.setDate(row.getCell(12).getDateCellValue());
            users.add(user);
        }
        userService.addAll(users);
    }
}