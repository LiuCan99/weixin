package com.wechat.domian;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: liucan
 * @Date: 2020/6/3 10:40
 */
@Table(name = "product")
@Data
public class Product {

    @Id
    private Integer id;
    private Double price;
    private String name;
    private Integer num;
}
