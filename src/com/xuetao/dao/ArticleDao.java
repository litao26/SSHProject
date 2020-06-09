package com.xuetao.dao;

import com.xuetao.entity.Article;
import com.xuetao.entity.Category;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface ArticleDao {
    List<Article> getALLArticle();
    /*获取总共记录数*/
    Integer getTotalCount(DetachedCriteria detachedCriteria);

    //查询分页数据
    List<Article> getPageData(DetachedCriteria detachedCriteria, Integer index, Integer pageSize);

    void delete(Article article);

    List<Category> getCategory(Integer parentid);

    void save(Article article);

    Article getOneArticle(Integer article_id);

    void update(Article article);
}
