package com.xuetao.controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xuetao.entity.Category;
import com.xuetao.service.CategoryService;

import java.util.List;

public class CategoryAction extends ActionSupport implements ModelDriven<Category> {

    private Category category = new Category();
    @Override
    public Category getModel() {
        return category;
    }
    private CategoryService categoryService;

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public String add(){
        //System.out.println(category);
        /*调用业务层*/
        categoryService.save(category);
        return null;
    }
    public String list(){
        //System.out.println("哈哈哈");
        List<Category> list = categoryService.getAllCategory();
        //System.out.println(list);
        //把数据存到值栈当中
        ActionContext.getContext().getValueStack().set("categoryList",list);
        return "list";
    }


}
