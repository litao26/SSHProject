package com.xuetao.dao.impl;

import com.xuetao.dao.CategoryDao;
import com.xuetao.entity.Category;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class CategoryDaoImpl extends HibernateDaoSupport implements CategoryDao {
    @Override
    public Category save(Category category) {
        //System.out.println("Categorylgajfj");

        this.getHibernateTemplate().save(category);
        return null;
    }

    @Override
    public List<Category> getAllCategory() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Category.class);
        List<Category> list = (List<Category>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        return list;
    }
}
