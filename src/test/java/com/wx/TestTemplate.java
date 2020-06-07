package com.wx;

import com.weixin.test.domain.template.TemplateData;
import com.weixin.test.domain.template.WxTemplate;
import com.weixin.test.service.WeiXinService;
import com.weixin.test.util.*;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


/**
 * @Author: liucan
 * @Date: 2020/5/7 16:01
 */

public class TestTemplate {

    /**
     * 发送模板消息
     */
    @Test
    public void sendTemplateMessage(){

        String touser="orNwy0R4FKFPoJe1w4EY4Jd-4aaU";
        String template_id="l5c_b0_AMC2z5Dg-c6nbzXV3tuxmzK3UhBM0kSlvxLY";

        Map<String, TemplateData> data = new HashMap<String,TemplateData>();
        TemplateData first = new TemplateData();
        first.setColor("#000000");
        first.setValue("您有新的反馈信息！");
        data.put("first", first);
        TemplateData company = new TemplateData();
        company.setColor("#000000");
        company.setValue("传智播客");
        data.put("company", company);
        TemplateData time = new TemplateData();
        time.setColor("#000000");
        time.setValue("2014年9月22日");
        data.put("time", time);
        TemplateData result = new TemplateData();
        result.setColor("#173177");
        result.setValue("面试通过");
        data.put("result", result);
        TemplateData remark = new TemplateData();
        remark.setColor("#173177");
        remark.setValue("请您联系我们得人事尽快入职");
        data.put("remark", remark);

        WxTemplate wxTemplate = new WxTemplate(touser,template_id,data);

        WeiXinService.sendTemplateMessage(wxTemplate);
    }


    @Test
    public void aa(){
        TulingApiUtil tulingApiProcess = new TulingApiUtil();
        String aa = tulingApiProcess.getTulingResult("你好");
        System.out.println(aa);
    }

}
