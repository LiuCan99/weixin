package com.wx;

import com.weixin.test.domain.button.*;
import net.sf.json.JSONObject;
import org.junit.Test;

/**
 * @Author: liucan
 * @Date: 2020/5/7 9:42
 */
public class TestButton {

    @Test
    public void testButton(){
        //创建菜单对象
        Button button=new Button();
        //第一个一级菜单
        button.getButton().add(new ClickButton("一级菜单","1"));
        //第二个一级菜单
        button.getButton().add(new ViewButton("百度一下","htttp://www.baidu.com"));
        //第三个一级菜单
        SubButton subButton = new SubButton("有子菜单");
        //第三个一级菜单下的子菜单
        subButton.getSub_button().add(new PhotoOrAlbumButton("传图识别文字","1"));
        subButton.getSub_button().add(new ViewButton("网易新闻","http://news.163.com"));
        subButton.getSub_button().add(new ClickButton("点击","31"));
        button.getButton().add(subButton);

        //转json
        JSONObject jsonObject = JSONObject.fromObject(button);
        System.out.println(jsonObject.toString());
    }



}
