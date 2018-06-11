package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import com.baizhi.util.UUIDUtil;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping(value="/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;
    @RequestMapping(value="/showAll",produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
//    public List<Banner> showAll()
//    {
//       List<Banner> banners = bannerService.showAll();
//       return  banners;
//    }
    public Map<String,Object> showAll(Integer page, Integer rows){
        Integer begin = (page-1)*rows+1;
        List<Banner> banners = bannerService.showAll();
        Integer end = null;
        if(page*rows<=banners.size()){
            end = page*rows;
        }else{
            end = banners.size();
        }
        List<Banner> bannersByPage = new ArrayList<Banner>();
        for(int i=begin-1;i<end;i++){
            bannersByPage.add(banners.get(i));
        }
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("total",banners.size());
        map.put("rows",bannersByPage);
        return  map;
    }
    //删除轮播图
    @RequestMapping(value="delete",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    //返回boolean值，页面才会自动更新
    public boolean delete(String id){
        try {
            bannerService.deleteBanner(id);
            return  true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //修改轮播图的转台
    @RequestMapping(value="update",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public boolean update(String id,String status){
        try {
            bannerService.updateBanner(id, status);
            return  true;
        } catch (Exception e) {
            e.printStackTrace();
            return  false;
        }
    }
    //添加轮播图
    @RequestMapping(value = "/add",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public void addBanner(MultipartFile img, Banner banner, HttpServletRequest request){
        //1.上传图片，并解决文件覆盖的问题
        //文件上传步骤：获得输入流，输出流，copy
        //获取当前项目路径（即是输入流）
        String projectPath = request.getSession().getServletContext().getRealPath("/");
        File file = new File(projectPath);
        //获取web项目的路径，------》上传文件夹的路径
        String webappsPath = file.getParent();
        //上传文件夹的路径
        File uploadPath = new File(webappsPath+"/upload");
        //如果上传文件夹不存在，则创建上传文件夹路径
        if(!uploadPath.exists()){
            uploadPath.mkdir();
        }
        //获取原始文件名
        String  oldFileName = img.getOriginalFilename();
        //获取原始文件名后面的扩展名
        String extension = FilenameUtils.getExtension(oldFileName);
        //将原始文件名重命名，防止文件名重复，文件被覆盖
        String newName = UUIDUtil.createUUID();
        newName = newName+"."+extension;
        //输入流-----》输出流
        try {
            //上传指定的文件夹
            img.transferTo(new File(uploadPath.getPath(),newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //2.存储到数据库
        banner.setId(UUIDUtil.createUUID());
        banner.setImgPath("http://localhost:9999/upload/"+newName);
        bannerService.addBanner(banner);
    }
}
