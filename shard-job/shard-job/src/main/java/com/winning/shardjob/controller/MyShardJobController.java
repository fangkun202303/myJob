package com.winning.shardjob.controller;

import com.winning.shardjob.model.MyShard;
import com.winning.shardjob.model.XxlJobGroup;
import com.winning.shardjob.service.MyShardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 分片业务自定义模块
 * @author: fangkun
 * @date: Created in 2019/7/15 13:41
 * @version: 1.0.0
 * @modified By:
 */
@RestController
@RequestMapping("/shard")
public class MyShardJobController {

    @Autowired
    private MyShardService myShardService;

    @PostMapping("save")
    public Map<String,Object> save(MyShard myShard){
        Map<String,Object> data=new HashMap<>(4);
        Integer save = myShardService.save(myShard);
        data.put("code",save);
        data.put("msg",save==1? "添加成功！":"添加失败！");
        return data;
    }

    @PutMapping("update")
    public Map<String,Object> update(MyShard myShard){
        Map<String,Object> data=new HashMap<>(4);
        Integer update = myShardService.update(myShard);
        data.put("code",update);
        data.put("msg",update==1? "修改成功！":"修改失败！");
        return data;
    }

    @DeleteMapping("delete")
    public Map<String,Object> delete(Integer id){
        Map<String,Object> data=new HashMap<>(4);
        Integer delete = myShardService.delete(id);
        data.put("code",delete);
        data.put("msg",delete==1? "删除成功！":"删除失败！");
        return data;
    }

    @GetMapping("myShardList")
    public Map<String,Object> myShardList(String app_name){
        Map<String,Object> data=new HashMap<>(4);
        List<MyShard> myShards = myShardService.myShardList(app_name);
        data.put("code",myShards.size()>=0? 1:5);
        data.put("msg",myShards.size()>=0? "成功！":"失败！");
        data.put("myShards",myShards);
        return data;
    }

    @GetMapping("xxlJobGroupList")
    public Map<String,Object> xxlJobGroupList(){
        Map<String,Object> data=new HashMap<>(4);
        List<XxlJobGroup> xxlJobGroups = myShardService.xxlJobGroupList();
        data.put("code",xxlJobGroups.size()>=0? 1:5);
        data.put("msg",xxlJobGroups.size()>=0? "成功！":"失败！");
        data.put("xxlJobGroups",xxlJobGroups);
        return data;
    }

    @GetMapping("getShardList")
    public Map<String,Object> getShardList(){
        Map<String,Object> data=new HashMap<>(4);
        List<MyShard> shardList = myShardService.getShardList();
        data.put("code",shardList.size()>=0? 1:5);
        data.put("msg",shardList.size()>=0? "成功！":"失败！");
        data.put("shardList",shardList);
        return data;
    }
}
