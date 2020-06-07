package com.weixin.test.util;


import com.weixin.test.domain.template.TemplateData;
import com.weixin.test.domain.template.WxTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 模板信息操作
 * @Author: liucan
 * @Date: 2020/5/8 16:04
 */

public class TemplateUtils {

    /**
     * 发送模板
     * @param template
     */
    public  static  void sendTemplate(WxTemplate template){

        WxTemplate wxTemplate = new WxTemplate(template.getTouser(),template.getTemplate_id(),template.getData());

    }







}