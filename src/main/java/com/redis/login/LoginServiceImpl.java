package com.redis.login;

import com.github.pagehelper.util.StringUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: liucan
 * @Date: 2020/3/2 11:43
 */
@Service
@Transactional
public class LoginServiceImpl implements  LoginService {
    @Resource
    private LoginMapper loginMapper;

    private  static final Integer num=5;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    private static final String REDISKEY="user:register:code:";

    @Override
    public Map<String,Object> login(String username, String password) {
        /**
         *连续密码错误五次则账号锁定一小时
         */
        String key = Ruser.getLoginTimeLockKey(username);
        //剩余次数
        Integer residualCount=0;
        Map<String,Object> resMap=new HashMap<>();
        resMap.put("success",false);

        //1.如果账号被限制（返回提示信息）
       if(redisTemplate.hasKey(key)){
           //如果存在，该账户被限制（登录失败） 返回提示信息
           Long lockTime = redisTemplate.getExpire(key, TimeUnit.MINUTES);
           resMap.put("success",false);
           resMap.put("info","您的账号已被封禁，"+lockTime+"分钟之后恢复正常");
           return resMap;
       }

        //2登录成功（清除输入密码错误的次数）
        Example example=new Example(Ruser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",username);
        criteria.andEqualTo("password",password);
        Ruser user = loginMapper.selectOneByExample(example);
        if(user!=null){
            resMap.put("success",true);
            redisTemplate.delete(key);
            resMap.put("info","登录成功");
            return resMap;
        }

        //3.失败
        //3.1记录登录失败次数（判断redis中的登录次数key是否存在 user:loginCount:fail:用户名）
        String loginCountFailKey = Ruser.getLoginCountFailKey(username);
        if(!redisTemplate.hasKey(loginCountFailKey)){
            //如果不存在 是第一次登录失败次数为，key=user:loginCount:fail:用户名 value=1 ,设置失效时间
            redisTemplate.opsForValue().set(loginCountFailKey,"1",5,TimeUnit.MINUTES);
            residualCount=num-1;
            resMap.put("success",false);
            resMap.put("info","账号或密码错误，该账号还能尝试"+residualCount+"次");
            return resMap;
        }
        //3.2如果存在  查询登录失败次数 否则限制登录
        Integer loginCount = Integer.parseInt((String) redisTemplate.opsForValue().get(loginCountFailKey));
        redisTemplate.opsForValue().get(loginCountFailKey);
        if(loginCount<4){
            //结果<4 value+1
            redisTemplate.opsForValue().increment(loginCountFailKey,1);
            residualCount=5-loginCount-1;
            resMap.put("success",false);
            resMap.put("info","账号或密码错误，该账号还能尝试"+residualCount+"次");
            return resMap;
        }
           //限制登录
        redisTemplate.opsForValue().set(key,"1");
        redisTemplate.expire(key,1,TimeUnit.HOURS);
        resMap.put("success",false);
        resMap.put("info","您的账号登录过于频繁，为保证账号安全，该账号已被封禁，请于一小时后再次尝试");
        return resMap;
    }

    @Override
    public String getCode(String mobile) {

        //1.校验参数
        if(StringUtil.isEmpty(mobile)){
            return null;
        }

        //2.判断该电话号码是否已被注册
        Example example=new Example(Ruser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("mobile",mobile);
        Ruser user = loginMapper.selectOneByExample(example);
        //该号码已被注册
        if(user!=null){
            return null;
        }

        //3.产生验证码
        String code = RandomStringUtils.randomNumeric(4);
        System.out.println("手机验证码为：" + code);


        //4 并存放到reids中 , key:user:login:code:用户名 ， value：验证码 , 1,分钟
        redisTemplate.opsForValue().set( REDISKEY+mobile , code , 5 , TimeUnit.MINUTES);

        //5.发送短信 todo

        return code;
    }

    /**
     * 新增用户
     * @param ruser
     */
    @Override
    public void register(Ruser ruser) {
        String value = (String) redisTemplate.opsForValue().get(REDISKEY + ruser.getMobile());
        loginMapper.insert(ruser);
    }
}
