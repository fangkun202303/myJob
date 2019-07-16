package com.moduls.myxxljobhttp.dao;

import com.moduls.myxxljobhttp.model.MyShard;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description: 分片自定义的持久层
 * @author: fangkun
 * @date: Created in 2019/7/15 13:59
 * @version: 1.0.0
 * @modified By:
 */
@Mapper
@Repository
public interface MyShardDao {
    /**
     * @description 根据条件获取相关分片的配置
     *
     * @param myShard
     * @return java.util.List<com.moduls.myxxljobhttp.model.MyShard>
     * @author fangkun
     * @date 2019/7/16 13:14
     */
    List<MyShard> getShardMessage(MyShard myShard);
}
