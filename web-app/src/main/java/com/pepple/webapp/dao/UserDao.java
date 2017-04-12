package com.pepple.webapp.dao;

import com.pepple.webapp.pojo.User;

/**
 * @description:
 * @author: pepple
 * @date: 2017/4/11 16:18
 */
public interface UserDao {
    public User getUserById(long id);
}
