package com.xuetao.dao;

import com.xuetao.entity.Category;

import java.util.List;

public interface CategoryDao {
    public Category save(Category category);

    List<Category> getAllCategory();
}
