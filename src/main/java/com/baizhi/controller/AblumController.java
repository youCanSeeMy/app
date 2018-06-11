package com.baizhi.controller;

import com.baizhi.entity.Ablum;
import com.baizhi.entity.Banner;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AblumService;
import com.baizhi.service.BannerService;
import com.baizhi.util.UUIDUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/ablum")
public class AblumController {
    @Autowired
    private AblumService ablumService;
    //显示所有专辑
    @RequestMapping(value = "/showAll", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public Map<String, Object> showAll(Integer page, Integer rows) {
        Integer begin = (page - 1) * rows + 1;
        List<Ablum> ablums = ablumService.showAll();
        Integer end = null;
        if (page * rows <= ablums.size()) {
            end = page * rows;
        } else {
            end = ablums.size();
        }
        List<Ablum> ablumsByPage = new ArrayList<Ablum>();
        for (int i = begin - 1; i < end; i++) {
            ablumsByPage.add(ablums.get(i));
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", ablums.size());
        map.put("rows", ablumsByPage);
        return map;
    }
    //添加专辑
    @RequestMapping(value="/addOne",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public void addOne(HttpServletRequest request,Ablum ablum,MultipartFile ablumFile){
        //上传文件
        //获得项目名
        String app = request.getSession().getServletContext().getRealPath("/");
        File file = new File(app);
        //获得webapps的lujing
        String webapps = file.getParent();
        //获得上传文件夹的路径
        String uploadPath = webapps+"/upload";
        File uploadFile = new File(uploadPath);
        if(!uploadFile.exists()){
            uploadFile.exists();
        }
        //改名
        String oldName = ablumFile.getOriginalFilename();
        String UUID = UUIDUtil.createUUID();
        String expension = FilenameUtils.getExtension(oldName);
        String newName = UUID+"."+expension;

        //上传
        try {
            ablumFile.transferTo(new File(uploadPath,newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //添加至数据库
        String id = UUIDUtil.createUUID();
        ablum.setId(id);
        ablum.setCoverImg(uploadPath+"/"+newName);
        ablumService.addOne(ablum);
    }
    //添加章节
    @RequestMapping(value="/addChapter",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public boolean addChapter(String id, HttpServletRequest request, Chapter chapter,MultipartFile music){
        //1.文件上传
        //上传文件，获得当前项目路径，再获得项目父路径，在父路径下建立文件夹
        //获得当前项目路径
        String appPath = request.getSession().getServletContext().getRealPath("/");
        File file = new File(appPath);
        //获得webapps的路径
        String webPath = file.getParent();
        //这是上传音乐的文件夹的路径，如果该路径不存在，则创建
        File musicPath = new File(webPath+"/music");
        if(!musicPath.exists()){
            musicPath.mkdir();
        }
        //将上传的音乐文件重命名，防止文件被覆盖
        //获得原始的文件名
        String oldName = music.getOriginalFilename();
        //获得文件名的扩展名
        String extension = FilenameUtils.getExtension(oldName);
        //重命名
        String uuid = UUIDUtil.createUUID();
        String newName = uuid+"."+extension;
        //上传至指定文件夹
        try {
            music.transferTo(new File(musicPath.getPath(),newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //2.添加数据库
        String uuid1 = UUIDUtil.createUUID();
        chapter.setId(uuid1);
        chapter.setAid(id);
        chapter.setDownPath(musicPath.getPath()+"/"+newName);
        chapter.setDuration(20);
        chapter.setSize(music.getSize()/1024/1024+"M");
        chapter.setTitle(oldName.substring(0,oldName.indexOf(".")));
        ablumService.addChapter(chapter);
        return true;
    }
    //下载文件
    @RequestMapping(value="/down",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public void down(String url, String oldName, HttpServletResponse response,HttpServletRequest request){
        //获得文件路径
        String downPath = url;
        File   downFile = new File(downPath);
        //2.将存储的文件响应出去
        //设置响应头 响应类型
        String fileName = null;
        try {
            fileName = new String(oldName.getBytes("UTF-8"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("content-disposition", "attachment;fileName=" + fileName);
        response.setContentType("audio/mpeg");
        //响应出去
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(FileUtils.readFileToByteArray(downFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}