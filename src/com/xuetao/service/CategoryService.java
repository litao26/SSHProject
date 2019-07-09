package com.xuetao.service;

import com.xuetao.entity.Category;

import java.util.List;

public interface CategoryService {
    public void save(Category category);

    public List<Category> getAllCategory();
}
