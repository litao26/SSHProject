package com.xuetao.controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xuetao.entity.Article;
import com.xuetao.entity.Category;
import com.xuetao.entity.PageBean;
import com.xuetao.service.ArticleService;
import lombok.Setter;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.io.IOException;
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

    /*获取分页数据*/
    @Setter
    private Integer currPage = 1;
    //搜索关键字
    @Setter
    private String keyWord;


    public  String pageList(){
        //System.out.println(currPage);
        System.out.println(keyWord);
        //设置查询条件
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);
        if(keyWord != null){
            //添加条件
            detachedCriteria.add(Restrictions.like("article_title","%"+keyWord+"%"));
        }
        //调用业务层
        PageBean pageBean = articleService.getPageData(detachedCriteria, currPage, 5);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "list";
    }
    @Setter
    private Integer article_id;
    public String delete(){
        //System.out.println(article_id);
        Article article = new Article();
        article.setArticle_id(article_id);
        articleService.delete(article);
        return "delete";
    }
    @Setter
    private Integer parentid;
    public String getCategory() throws IOException {
        //System.out.println(parentid);
        //根据parentid查询分类
        List<Category> list = articleService.getCategory(parentid);
        System.out.println(list);
        //以json格式响应给页面
        JSONArray jsonArray = JSONArray.fromObject(list, new JsonConfig());
        System.out.println(jsonArray);
        //响应给页面
        ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().println(jsonArray.toString());

        return null;
    }
}
