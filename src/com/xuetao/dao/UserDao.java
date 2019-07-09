package com.xuetao.dao;

import com.xuetao.entity.User;

public interface UserDao {
    public User getUser(String username, String password);
}
