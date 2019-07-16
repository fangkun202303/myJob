package com.moduls.myxxljobhttp.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.moduls.myxxljobhttp.config.XxlJobProperties;
import com.moduls.myxxljobhttp.dao.MyShardDao;
import com.moduls.myxxljobhttp.model.MyShard;
import com.moduls.myxxljobhttp.service.MyServiceOfXxl;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.core.util.ShardingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    @Autowired
    private XxlJobProperties xxlJobProperties;

    @Autowired
    private MyShardDao myShardDao;

    @Override
    public ReturnT<String> execute(String s) throws Exception {

//        // 分片参数
//        ShardingUtil.ShardingVO shardingVO = ShardingUtil.getShardingVo();
//        XxlJobLogger.log("分片参数：当前分片序号 = {}, 总分片数 = {}", shardingVO.getIndex(), shardingVO.getTotal());
//
//        logger.info(LocalTime.now().atDate(LocalDate.now()).format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"))+"分片参数：当前分片序号 = {"+shardingVO.getIndex()+"}, 总分片数 = {"+shardingVO.getTotal()+"}" );
//        System.out.println(
//                LocalTime.now().atDate(LocalDate.now()).format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"))+"分片参数：当前分片序号 = {"+shardingVO.getIndex()+"}, 总分片数 = {"+shardingVO.getTotal()+"}"
//        );
//
//
//        // 业务逻辑
//        JSONArray objects = JSON.parseArray(s);
//        for (int i = 0; i <objects.size() ; i++) {
//            if(i%shardingVO.getTotal() ==shardingVO.getIndex()){
//                JSONObject object = (JSONObject) objects.get(i);
//                logger.info("这个分片处理的数据为：名称："+object.get("name")+"年龄："+object.get("age"));
//                System.out.println("这个分片处理的数据为：名称："+object.get("name")+"年龄："+object.get("age"));
//            }
//        }

        /**
         * 根据ip，分片序号
         */
        MyShard myShard=new MyShard();
        myShard.setApp_name(xxlJobProperties.getAppName());
        myShard.setIp(xxlJobProperties.getIp());
        myShard.setPort(xxlJobProperties.getPort());
        List<MyShard> shardMessage = myShardDao.getShardMessage(myShard);
        System.out.println("这个分片处理的数据是："+JSON.toJSONString(shardMessage));
        return SUCCESS;
    }
}
