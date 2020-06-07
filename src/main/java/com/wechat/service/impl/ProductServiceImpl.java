package com.wechat.service.impl;

import com.github.pagehelper.util.StringUtil;
import com.wechat.domian.Product;
import com.wechat.mapper.ProductMapper;
import com.wechat.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: liucan
 * @Date: 2020/6/3 10:42
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Override
    public List<Product> findAll(Product product) {

        Example example=new Example(Product.class);
        Example.Criteria criteria = example.createCriteria();

        if(StringUtil.isNotEmpty(product.getName())){
            criteria.andLike("name","%"+product.getName()+"%");
        }

        List<Product> productList = productMapper.selectByExample(example);
        return productList;

    }
}
