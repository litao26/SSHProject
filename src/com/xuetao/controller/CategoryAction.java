package com.xuetao.controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xuetao.entity.Category;
import com.xuetao.service.CategoryService;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;
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
        return "listAction";
    }
    public String list(){
        //System.out.println("哈哈哈");
        List<Category> list = categoryService.getAllCategory();
        //System.out.println(list);
        //把数据存到值栈当中
        ActionContext.getContext().getValueStack().set("categoryList",list);
        return "list";
    }

    public String updateUI() throws IOException {
        Category category2 = categoryService.getOneCategory(category.getCid());
        System.out.println(category2);
        //把数据给页面
        //以json格式响应给页面
        JSONArray jsonArray = JSONArray.fromObject(category2, new JsonConfig());
        System.out.println(jsonArray);
        //响应给页面
        ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
        return null;
    }

    public String update(){
        //System.out.println("123431");
        //调用业务层
        categoryService.update(category);
        return "listAction";
    }

    public String delete(){
        //System.out.println("12345");
        categoryService.delete(category);
        return "listAction";
    }
}
