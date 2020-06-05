package com.xuetao.service.impl;

import com.xuetao.dao.ArticleDao;
import com.xuetao.entity.Article;
import com.xuetao.entity.Category;
import com.xuetao.entity.PageBean;
import com.xuetao.service.ArticleService;
import lombok.Setter;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public class ArticleServiceImpl  implements ArticleService {
    @Setter
    private ArticleDao articleDao;

    @Override
    public List<Article> getAllArticle() {
        List<Article> allArticle = articleDao.getALLArticle();
        return allArticle;
    }

    @Override
    public PageBean getPageData(DetachedCriteria detachedCriteria, Integer currPage, int pageSize) {
        PageBean<Article> pageBean = new PageBean<>();
        //设置当前页
        pageBean.setCurrentPage(currPage);
        //设置一页有多少条数据
        pageBean.setPageSize(pageSize);
        //获取总记录数
        //从数据库中查询总记录
        Integer totalCount = articleDao.getTotalCount(detachedCriteria);
        pageBean.setTotalCount(totalCount);
        //设置总页数
        pageBean.setTotalPage(pageBean.getTotalPage());
        //设置数据当前页数据
        //查询数据库
        List<Article> list = articleDao.getPageData(detachedCriteria, pageBean.getIndex(), pageBean.getPageSize());
        //计算
        pageBean.setList(list);
        //System.out.println(pageBean);
        return pageBean;
    }

    @Override
    public void delete(Article article) {
        articleDao.delete(article);
    }

    @Override
    public List<Category> getCategory(Integer parentid) {
        List<Category> list = articleDao.getCategory(parentid);
        return list;
    }
}
