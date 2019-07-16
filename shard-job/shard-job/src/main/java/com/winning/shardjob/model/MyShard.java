package com.winning.shardjob.model;

/**
 * @description: 分片的自定义模型
 * @author: fangkun
 * @date: Created in 2019/7/15 13:46
 * @version: 1.0.0
 * @modified By:
 */
public class MyShard {
    // 分片的id
    private Integer id;
    // 执行器名称
    private String app_name;
    // 分片名称
    private String shard_name;
    // 分片ip
    private String ip;
    // 分片端口号
    private String port;
    // 分片排序
    private Integer orde;

    // 分片扩展字段
    private String extend;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getShard_name() {
        return shard_name;
    }

    public void setShard_name(String shard_name) {
        this.shard_name = shard_name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Integer getOrde() {
        return orde;
    }

    public void setOrde(Integer orde) {
        this.orde = orde;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    @Override
    public String toString() {
        return "MyShard{" +
                "id=" + id +
                ", app_name='" + app_name + '\'' +
                ", shard_name='" + shard_name + '\'' +
                ", ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", orde=" + orde +
                ", extend='" + extend + '\'' +
                '}';
    }
}
