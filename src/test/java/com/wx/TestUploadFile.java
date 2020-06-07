package com.wx;

import com.weixin.test.service.WeiXinService;
import com.weixin.test.util.Util;
import com.weixin.test.util.WeiXinParameterUtil;
import org.junit.Test;

/**
 * @Author: liucan
 * @Date: 2020/5/8 9:58
 */
public class TestUploadFile {

    /**
     * 上传临时素材（保存时间为3天）
     */
    @Test
    public  void uploadFile(){
        String file="C:/Users/lenovo/Desktop/test.png";
        String image = WeiXinService.uploadFile(file, "image");
        System.out.println(image);
    }


    /**
     * 获得临时素材
     */
    @Test
    public void GetFile(){
       String url="https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
       url=url.replace("ACCESS_TOKEN", WeiXinParameterUtil.getAccessToken())
               .replace("MEDIA_ID","1zh0Anuo_FEYAGl0c5iQlzGxrWPVn1vICYtrZiSa4yIWcY4YaZBMEMwbEAxAFcbD");

        String s = Util.get(url);
        System.out.println(s);

    }
}
