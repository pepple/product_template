package com.pepple.webapp.dao.impl;

import com.pepple.webapp.dao.UserDao;
import com.pepple.webapp.pojo.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @author: pepple
 * @date: 2017/4/11 16:37
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public User getUserById(long id) {
        String hql = "from User where id = " + id;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return (User) query.uniqueResult();
    }
}
