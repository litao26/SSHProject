package com.xuetao.controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xuetao.entity.Article;
import com.xuetao.entity.Category;
import com.xuetao.entity.PageBean;
import com.xuetao.service.ArticleService;
import lombok.Setter;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.io.IOException;
import java.util.List;

public class WebAction extends ActionSupport {

    @Setter
    private ArticleService articleService;


    @Setter
    private Integer currPage = 1;
    @Setter
    private Integer parentid;
    @Setter
    private Integer cid;
    //前端分页
    public void getPageList(){
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);
        if (parentid != null){
            List<Category> categorys = articleService.getCategory(parentid);
            //构建数组
            Object[] cidArrays = new Object[categorys.size()];
            for (int i = 0; i < categorys.size(); i++) {
                Category category = categorys.get(i);
                cidArrays[i] = category.getCid();
            }
            //设置条件
            detachedCriteria.add(Restrictions.in("category.cid", cidArrays));
        }
        if(cid != null){
            detachedCriteria.add(Restrictions.eq("category.cid", cid));
        }
        //调用业务层
        PageBean pageBean = articleService.getPageData(detachedCriteria, currPage, 5);
        JsonConfig jsonConfig = new JsonConfig();
        //hibernate懒加载
        jsonConfig.setExcludes(new String[]{"handler", "hibernateLazyInitializer"});
        //以json格式响应给页面
        JSONObject jsonObject = JSONObject.fromObject(pageBean, jsonConfig);
        //响应给页面
        ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
        try {
            ServletActionContext.getResponse().getWriter().println(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Setter
    private Integer id;
    //根据id获取指定的文章
    public void  getDetail() throws IOException {
        Article oneArticle = articleService.getOneArticle(id);
        JsonConfig jsonConfig = new JsonConfig();
        //hibernate懒加载
        jsonConfig.setExcludes(new String[]{"handler", "hibernateLazyInitializer"});
        //以json格式响应给页面
        JSONObject jsonObject = JSONObject.fromObject(oneArticle, jsonConfig);
        //响应给页面
        ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().println(jsonObject.toString());
    }
}
