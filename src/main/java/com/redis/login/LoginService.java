package com.redis.login;

import java.util.Map;

/**
 * @Author: liucan
 * @Date: 2020/3/2 11:42
 */
public interface LoginService {


    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    Map<String,Object> login(String username, String password);


    /**
     * 获得验证码
     * @param mobile
     * @return
     */
    String getCode(String mobile);

    /**
     * 新增用户
     * @param ruser
     */
    void register(Ruser ruser);
}
