package com.weixin.test.domain.message;

/**
 * 微信公众号AccessToken
 * @Author: liucan
 * @Date: 2020/5/6 18:04
 */
public class AccessToken {
    private String accessToken;
    private long  expireTime;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public AccessToken() {
    }

    public AccessToken(String accessToken, String expireIn) {
        this.accessToken = accessToken;
        //计算过期时间
        expireTime=System.currentTimeMillis()+Integer.parseInt(expireIn)*1000;
    }

    /**
     * 判断token是否过期
     */
     public  boolean isExpired(){
         return  System.currentTimeMillis()>expireTime;
     }




}
