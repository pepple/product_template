package com.pepple.webapp.dao.impl;

import com.pepple.webapp.dao.BaseHibernateDao;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: pepple
 * @date: 2017/4/11 17:59
 */
public class BaseHibernateDaoImpl<T,UniqueId extends Serializable> extends HibernateDaoSupport implements BaseHibernateDao<T,UniqueId> {
    protected Logger logger = Logger.getLogger(getClass());
    private Class<T> pojoClass;

    public BaseHibernateDaoImpl(Class<T> pojoClass) {
        this.pojoClass = pojoClass;
    }

    @Override
    public T get(UniqueId uniqueId) {
        return getHibernateTemplate().get(pojoClass,uniqueId);
    }

    @Override
    public List<T> getAll(List list) {
        return null;
    }

    @Override
    public boolean delete(UniqueId uniqueId) {
        return false;
    }

    @Override
    public boolean deleteAll(List list) {
        return false;
    }

    @Override
    public T add(T obj) {
        return null;
    }

    @Override
    public T addAll(List<T> objList) {
        return null;
    }
}
