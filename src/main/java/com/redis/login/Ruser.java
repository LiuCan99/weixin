package com.redis.login;


import javax.persistence.Table;

/**
 * @Author: liucan
 * @Date: 2020/3/2 15:26
 */
@Table(name = "ruser")
public class Ruser  {
    private String username;
    private String password;
    private String mobile;

    /**
     * 登录失败次数key user:loginCount:fail:用户名
     */
    public static String getLoginCountFailKey(String username){
        return "user:loginCount:fail:"+username;
    }
    /**
     *锁定限制登录key user:loginTime:lock:用户名
     */
    public  static String getLoginTimeLockKey(String username){
        return "user:loginTime:lock:"+username;
    }

    /**
     *验证码
     */
    private  static String getCode(String mobile){
        return "user:login:code:"+mobile;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Ruser() {
    }

    public Ruser(String username, String password, String mobile) {
        this.username = username;
        this.password = password;
        this.mobile = mobile;
    }


}
