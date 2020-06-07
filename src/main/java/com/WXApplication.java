package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: liucan
 * @Date: 2020/2/25 15:39
 */

@SpringBootApplication
@EnableTransactionManagement
public class WXApplication {
    public static void main(String[] args) {
        SpringApplication.run(WXApplication.class,args);
    }
}
