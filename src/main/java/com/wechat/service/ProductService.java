package com.wechat.service;

import com.wechat.domian.Product;

import java.util.List;

/**
 * @Author: liucan
 * @Date: 2020/6/3 10:42
 */
public interface ProductService {
    List<Product> findAll(Product product);
}
