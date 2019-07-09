package com.xuetao.controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xuetao.entity.User;
import com.xuetao.service.LoginService;

public class LoginAction extends ActionSupport implements ModelDriven<User> {
    private User user = new User();

    @Override
    public User getModel() {
        return user;
    }

    //注入loginService
    private LoginService loginService;

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
    * 方法实现说明 登录功能
    * @author      litao
    * @param       user
    * @return      String
    * @exception
    * @date        2020/5/29 19:49
    */
    public String login(){
        //System.out.println("进来了");
        //System.out.println(user);
        User resUser = loginService.login(user);
        if(resUser == null){
            //错误信息回显
            this.addActionError("用户名或密码错误");
            return LOGIN;
        }else {
            //保存用户信息
            ActionContext.getContext().getSession().put("curUser",resUser);
            return SUCCESS;
        }
    }
    /**
    * 方法实现说明    退出功能
    * @author      litao
    * @param
    * @return      String
    * @exception
    * @date        2020/5/29 19:49
    */
    public String loginOut(){
        //System.out.println("退出");
        //清空当前用户的session
        ActionContext.getContext().getSession().remove("curUser");
        return "login_out";
    }

}
