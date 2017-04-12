package com.pepple.webapp.dao.impl;

import com.pepple.webapp.dao.BaseHibernateDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description:
 * @author: pepple
 * @date: 2017/4/11 17:59
 */
public class BaseHibernateDaoImpl implements BaseHibernateDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public <T> T getById(long id) {
        sessionFactory.getCurrentSession().createQuery("");
        return null;
    }
}
