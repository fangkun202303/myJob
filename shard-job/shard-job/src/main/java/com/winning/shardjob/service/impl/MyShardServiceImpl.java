package com.winning.shardjob.service.impl;

import com.winning.shardjob.dao.MyShardDao;
import com.winning.shardjob.model.MyShard;
import com.winning.shardjob.model.XxlJobGroup;
import com.winning.shardjob.service.MyShardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description: MyShardService的实现
 * @author: fangkun
 * @date: Created in 2019/7/15 13:58
 * @version: 1.0.0
 * @modified By:
 */
@Service
public class MyShardServiceImpl implements MyShardService {

    @Autowired
    private MyShardDao myShardDao;

    /**
     * @param myShard
     * @return java.lang.Integer
     * @description 添加分片
     * @author Administrator
     * @date 2019/7/15 13:53
     */
    @Transactional
    @Override
    public Integer save(MyShard myShard) {
        Integer save = myShardDao.save(myShard);
        return save==1? 1:2;
    }

    /**
     * @param myShard
     * @return java.lang.Integer
     * @description 修改
     * @author Administrator
     * @date 2019/7/15 13:55
     */
    @Transactional
    @Override
    public Integer update(MyShard myShard) {
        Integer update = myShardDao.update(myShard);
        return update==1? 1:3;
    }

    /**
     * @param id
     * @return java.lang.Integer
     * @description 删除
     * @author Administrator
     * @date 2019/7/15 13:55
     */
    @Transactional
    @Override
    public Integer delete(Integer id) {
        Integer delete = myShardDao.delete(id);
        return delete==1? 1:4;
    }

    /**
     * @return java.util.List<com.xxl.job.admin.myshard.model.MyShard>
     * @description 获取分片集合
     * @author Administrator
     * @date 2019/7/15 13:55
     */
    @Override
    public List<MyShard> myShardList(String app_name) {
        List<MyShard> myShards = myShardDao.myShardList(app_name);
        return myShards;
    }

    /**
     * @return java.util.List<com.xxl.job.admin.core.model.XxlJobGroup>
     * @description 获取执行器集合
     * @author Administrator
     * @date 2019/7/15 13:56
     */
    @Override
    public List<XxlJobGroup> xxlJobGroupList() {
        List<XxlJobGroup> xxlJobGroups = myShardDao.xxlJobGroupList();
        return xxlJobGroups;
    }

    @Override
    public List<MyShard> getShardList(){
        List<MyShard> shardList = myShardDao.getShardList();
        return shardList;
    }
}
