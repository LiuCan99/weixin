package com.weixin.test.mapper;

/**
 * @Author: liucan
 * @Date: 2020/5/10 15:32
 */

import com.weixin.test.domain.User;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface  UserMapper extends Mapper<User> {
}
