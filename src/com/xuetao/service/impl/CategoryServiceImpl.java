package com.xuetao.service.impl;

import com.xuetao.dao.CategoryDao;
import com.xuetao.entity.Category;
import com.xuetao.service.CategoryService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao;

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public void save(Category category) {
        //System.out.println(category+"--------");
        categoryDao.save(category);
        /*调用*/
    }

    @Override
    public List<Category> getAllCategory() {
        //调用dao查询所有分类
        List<Category> list = categoryDao.getAllCategory();
        return list;
    }
}
