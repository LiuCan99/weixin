package com.rabbitmq.learn.work;

/**
 * @Author: liucan
 * @Date: 2020/6/5 16:36
 */
public enum WorkEnum {
    QUEUE_NAME("test_work_queue","队列名称");
    private String key;

    private String info;

    WorkEnum(String key, String info) {
        this.key = key;
        this.info = info;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }}
