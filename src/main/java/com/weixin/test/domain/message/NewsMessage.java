package com.weixin.test.domain.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 图文消息
 * @Author: liucan
 * @Date: 2020/5/6 11:26
 */
@XStreamAlias("xml")
public class NewsMessage extends  BaseMassage {

    @XStreamAlias("ArticleCount")
    private String articleCount;

    @XStreamAlias("Articles")
    private List<Article> articles=new ArrayList<>();

    public String getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(String articleCount) {
        this.articleCount = articleCount;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public NewsMessage(Map<String, String> requestMap,List<Article> articles) {
        super(requestMap);
        this.setMsgType("news");
        this.articleCount=Integer.toString(articles.size());
        this.articleCount=articleCount;
        this.articles=articles;
    }

}
