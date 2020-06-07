package com.redis.login;

import com.github.pagehelper.util.StringUtil;
import com.redis.cofig.ResultCode;
import com.redis.cofig.ResultModel;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author: liucan
 * @Date: 2020/3/2 11:42
 */
@RestController
@RequestMapping("/user")
public class LoginConroller {

    @Resource
    private  RedisTemplate<String, Object> redisTemplate;

    @Resource
    private  LoginService loginService;



    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public ResultModel login(@RequestParam("username")String username,
                             @RequestParam("password")String password   ){
        Map<String,Object> resMap= loginService.login(username,password);
        boolean success = (boolean) resMap.get("success");
        String info= (String) resMap.get("info");
        if(!success){
          //账号被限制登录
          return ResultModel.failure(ResultCode.FAILURE,info);
        }
        return ResultModel.success(ResultCode.SUCCESS,info);

    }

    /**
     * 获得验证码
     * @param mobile
     * @return
     */
    @PostMapping("/getCode")
    public ResultModel getCode(@RequestParam("mobile")String mobile){
        String code= loginService.getCode(mobile);
        if(StringUtil.isNotEmpty(code)){
            return ResultModel.success(ResultCode.SUCCESS);
        }
        return ResultModel.failure(ResultCode.FAILURE,"该号码已被注册");
    }

    /**
     * 注册（添加用户）
     * @param username
     * @param password
     * @param mobile
     * @return
     */
    @PostMapping("/register")
    public ResultModel register(@RequestParam("username")String username,
                                 @RequestParam("password")String password,
                                @RequestParam("mobile")String mobile){
        Ruser ruser=new Ruser();
        ruser.setUsername(username);
        ruser.setMobile(mobile);
        ruser.setPassword(password);

        loginService.register(ruser);
        return ResultModel.success(ResultCode.SUCCESS);
    }

}
