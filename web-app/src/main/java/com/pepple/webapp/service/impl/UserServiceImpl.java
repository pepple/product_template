package com.pepple.webapp.service.impl;

import com.pepple.webapp.dao.UserDao;
import com.pepple.webapp.pojo.User;
import com.pepple.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: pepple
 * @date: 2017/4/11 16:47
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }
}
