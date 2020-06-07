package com.weixin.test.util;

import com.weixin.test.domain.button.*;
import net.sf.json.JSONObject;

/**
 * 创建菜单：
 * url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
 *
 *
 * @Author: liucan
 * @Date: 2020/5/7 10:16
 */
public class CreateMenu {
    public static void main(String[] args) {
        //创建菜单对象
        Button button=new Button();
        //第一个一级菜单
        button.getButton().add(new PhotoOrAlbumButton("读取图片中文字","1"));
        //第二个一级菜单
        button.getButton().add(new ClickButton("生日查询","2"));

        //第三个一级菜单（有子菜单）
        SubButton subButton = new SubButton("有子菜单");
        button.getButton().add(new PhotoOrAlbumButton("这是一个菜单","31"));
        subButton.getSub_button().add(new ViewButton("网易新闻","http://news.163.com"));
        subButton.getSub_button().add(new ClickButton("点击","32"));
        button.getButton().add(subButton);

        //转json
        JSONObject jsonObject = JSONObject.fromObject(button);

        //准备url
        String url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
        String data=jsonObject.toString();
        url=url.replace("ACCESS_TOKEN", WeiXinParameterUtil.getAccessToken());
        //发送请求
        String result = Util.post(url, data).toString();
        System.out.println(result);
    }

}
