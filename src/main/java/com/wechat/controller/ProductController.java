package com.wechat.controller;

import com.wechat.domian.Product;
import com.wechat.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: liucan
 * @Date: 2020/6/3 10:43
 */

@Controller
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @PostMapping("/findAll")
    @ResponseBody
    public List<Product> findAll(Product product){
        List<Product> productList=productService.findAll(product);
        return  productList;
    }
}
