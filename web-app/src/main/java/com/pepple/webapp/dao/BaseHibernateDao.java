package com.pepple.webapp.dao;

/**
 * @description:
 * @author: pepple
 * @date: 2017/4/11 17:57
 */
public interface BaseHibernateDao {
    public <T> T getById(long id);
}
