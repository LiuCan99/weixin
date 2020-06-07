package com.weixin.test.domain;

import lombok.Data;

import java.util.Date;

/**
 * @Author: liucan
 * @Date: 2020/5/10 15:25
 */
@Data
public class User {
    private Integer id;
    private String sex;
    private String name;
    private String age;
    private Date birthday;
    private String phone;

    private Integer role_id;
    private String portrait;
    private String password;
    private String state;





}
