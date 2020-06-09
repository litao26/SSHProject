package com.xuetao.dao.impl;

import com.xuetao.dao.ArticleDao;
import com.xuetao.entity.Article;
import com.xuetao.entity.Category;
import net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class ArticleDaoImpl extends HibernateDaoSupport implements ArticleDao {
    @Override
    public List<Article> getALLArticle() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);
        List<Article> list = (List<Article>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        return list;
    }

    @Override
    public Integer getTotalCount(DetachedCriteria detachedCriteria) {
        //查询总记录
        detachedCriteria.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list.size() > 0) {
            return list.get(0).intValue();
        }
        return 0;
    }

    @Override
    public List<Article> getPageData(DetachedCriteria detachedCriteria, Integer index, Integer pageSize) {
        //清空查询总记录数条件
        detachedCriteria.setProjection(null);
        List<Article> list = (List<Article>) this.getHibernateTemplate().findByCriteria(detachedCriteria, index, pageSize);
        return list;
    }

    @Override
    public void delete(Article article) {
        this.getHibernateTemplate().delete(article);
    }

    @Override
    public List<Category> getCategory(Integer parentid) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Category.class);
        detachedCriteria.add(Restrictions.eq("parentid", parentid));
        List<Category> list = (List<Category>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        return list;
    }

    @Override
    public void save(Article article) {
        this.getHibernateTemplate().save(article);
    }

    @Override
    public Article getOneArticle(Integer article_id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);
        detachedCriteria.add(Restrictions.eq("article_id", article_id));
        List<Article> list = (List<Article>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public void update(Article article) {
        this.getHibernateTemplate().update(article);
    }
}
