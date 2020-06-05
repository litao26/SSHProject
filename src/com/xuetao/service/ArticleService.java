package com.xuetao.service;

import com.xuetao.entity.Article;
import com.xuetao.entity.Category;
import com.xuetao.entity.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface ArticleService {
    /*查询所有文章业务*/
    List<Article> getAllArticle();
    /*获取pageBean*/
    PageBean getPageData(DetachedCriteria detachedCriteria, Integer currPage, int pageSize);
    /*删除文章*/
    void delete(Article article);
    /*根据id获取分类*/
    List<Category> getCategory(Integer parentid);
}
