package com.xuetao.controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xuetao.entity.Article;
import com.xuetao.service.ArticleService;

import java.util.List;

public class ArticleAction extends ActionSupport {
    private ArticleService articleService;

    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    public String list(){
        //System.out.println("1234213");
        List<Article> allArticle = articleService.getAllArticle();
        //System.out.println(allArticle);
        //把数据存到值栈中，转发到jsp中
        ActionContext.getContext().getValueStack().set("allArticle", allArticle);
        return "list";
    }
}
