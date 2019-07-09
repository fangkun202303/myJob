package com.moduls.myxxljob.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.moduls.myxxljob.service.MyServiceOfXxl;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.core.util.ShardingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @description: 分片
 * @program: my-xxl-job-http
 * @author: fangkun
 * @version： 1.0.0
 * @create: 2019-07-08
 */
@JobHandler(value = "myShardingJobHandler")
@Component
public class MyShardingJobHandler extends IJobHandler {

    private Logger logger = LoggerFactory.getLogger(MyShardingJobHandler.class);

    @Autowired
    private MyServiceOfXxl myServiceOfXxl;


    @Override
    public ReturnT<String> execute(String s) throws Exception {

        // 分片参数
        ShardingUtil.ShardingVO shardingVO = ShardingUtil.getShardingVo();
        XxlJobLogger.log("分片参数：当前分片序号 = {}, 总分片数 = {}", shardingVO.getIndex(), shardingVO.getTotal());

        logger.info(LocalTime.now().atDate(LocalDate.now()).format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"))+"分片参数：当前分片序号 = {"+shardingVO.getIndex()+"}, 总分片数 = {"+shardingVO.getTotal()+"}" );
        System.out.println(
                LocalTime.now().atDate(LocalDate.now()).format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"))+"分片参数：当前分片序号 = {"+shardingVO.getIndex()+"}, 总分片数 = {"+shardingVO.getTotal()+"}"
        );
        // 业务逻辑
        JSONArray objects = JSON.parseArray(s);
        for (int i = 0; i <objects.size() ; i++) {
            if(i%shardingVO.getTotal() ==shardingVO.getIndex()){
                JSONObject object = (JSONObject) objects.get(i);
                logger.info("这个分片处理的数据为：名称："+object.get("name")+"年龄："+object.get("age"));
                System.out.println("这个分片处理的数据为：名称："+object.get("name")+"年龄："+object.get("age"));
            }
        }
        return SUCCESS;
    }
}
