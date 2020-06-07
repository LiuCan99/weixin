package com.wx;

import org.junit.Test;

/**
 * @Author: liucan
 * @Date: 2020/5/13 12:01
 */
public class TestBase {

    /**
     * 编写程序求7进制数123456012的10进制结果
     * 尽量可能减少代码
     * 回避幂运算
     * 不允许使用math类
     */

    @Test
    public void test(){
        int i = toTen("123456012", 7);
        System.out.println(i);
    }

    /**
     * 数字转为十进制
     * @param str 需要转换的数字
     * @param num 该数的进制
     * @return
     */
    private static int toTen(String str,int num) {
        int baes=1;  //记录进制数
        int reNum=0; //记录返回的数

        for(int i=str.length()-1;i>=0;i--){
            //char转为int 并计算进制
            reNum+=(str.charAt(i)-'0')*baes;
            baes*=num;
        }
        return reNum;
    }




}
