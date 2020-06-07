package com.weixin.test.service;

import com.weixin.test.domain.User;
import com.weixin.test.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * @Author: liucan
 * @Date: 2020/5/10 15:31
 */
@Service
@Transactional
public class UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 根据名称查询
     * @param name
     * @return
     */
    public  User findUserByName(String name){

        Example example=new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name",name);

        User user = userMapper.selectOneByExample(example);
        return user;
    }
}
