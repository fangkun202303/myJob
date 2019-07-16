package com.moduls.myxxljobhttp.controller;

import com.alibaba.fastjson.JSON;
import com.moduls.myxxljobhttp.config.XxlJobProperties;
import com.moduls.myxxljobhttp.dao.MyShardDao;
import com.moduls.myxxljobhttp.model.MyShard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @program: my-elastic-job
 * @author: fangkun
 * @version： 1.0.0
 * @create: 2019-07-05
 */
@RestController
public class IndexController {

    @Autowired
    private XxlJobProperties xxlJobProperties;

    @Autowired
    private MyShardDao myShardDao;

    @GetMapping("runMyService")
    public String runService(){
        MyShard myShard=new MyShard();
        myShard.setApp_name(xxlJobProperties.getAppName());
        myShard.setIp(xxlJobProperties.getIp());
        myShard.setPort(xxlJobProperties.getPort());
        return JSON.toJSONString(myShardDao.getShardMessage(myShard));
    }
}
