package com.baizhi.controller;

import com.baizhi.entity.Log;
import com.baizhi.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/log")
public class LogController {
    @Autowired
    private LogService logService;

    @RequestMapping(value = "/showAll", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public Map<String, Object> showAll(Integer page, Integer rows) {
        Integer begin = (page - 1) * rows + 1;
        List<Log> logs = logService.showAll();
        Integer end = null;
        if (page * rows <= logs.size()) {
            end = page * rows;
        } else {
            end = logs.size();
        }
        List<Log> logsByPage = new ArrayList<Log>();
        for (int i = begin - 1; i < end; i++) {
            logsByPage.add(logs.get(i));
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", logs.size());
        map.put("rows", logsByPage);
        return map;
    }
}