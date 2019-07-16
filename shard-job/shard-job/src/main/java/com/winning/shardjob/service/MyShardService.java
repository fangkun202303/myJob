package com.winning.shardjob.service;

import com.winning.shardjob.model.MyShard;
import com.winning.shardjob.model.XxlJobGroup;

import java.util.List;

/**
 * @description: 分片业务自定义模块
 * @author: fangkun
 * @date: Created in 2019/7/15 13:45
 * @version: 1.0.0
 * @modified By:
 */
public interface MyShardService {

    /**
     * @description 添加分片
     *
     * @param myShard
     * @return java.lang.Integer
     * @author Administrator
     * @date 2019/7/15 13:53
     */
    Integer save(MyShard myShard);

    /**
     * @description 修改
     *
     * @param myShard
     * @return java.lang.Integer
     * @author Administrator
     * @date 2019/7/15 13:55
     */
    Integer update(MyShard myShard);

    /**
     * @description 删除
     *
     * @param id
     * @return java.lang.Integer
     * @author Administrator
     * @date 2019/7/15 13:55
     */
    Integer delete(Integer id);

    /**
     * @description 获取分片集合
     *
     * @param
     * @return java.util.List<com.xxl.job.admin.myshard.model.MyShard>
     * @author Administrator
     * @date 2019/7/15 13:55
     */
    List<MyShard> myShardList(String app_name);

    /**
     * @description 获取执行器集合
     *
     * @param
     * @return java.util.List<com.xxl.job.admin.core.model.XxlJobGroup>
     * @author Administrator
     * @date 2019/7/15 13:56
     */
    List<XxlJobGroup> xxlJobGroupList();

    /**
     * @description 获取全部数据
     *
     * @param
     * @return java.util.List<com.winning.shardjob.model.MyShard>
     * @author fangkun
     * @date 2019/7/16 9:00
     */
    List<MyShard> getShardList();
}
