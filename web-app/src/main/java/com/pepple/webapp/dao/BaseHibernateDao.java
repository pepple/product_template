package com.pepple.webapp.dao;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: pepple
 * @date: 2017/4/11 17:57
 */
public interface BaseHibernateDao<T,UniqueId extends Serializable> {
    /**
     * 根据主键获取对象
     * @param id
     * @return
     */
    public T get(UniqueId id);

    public List<T> getAll(List list);

    public boolean delete(UniqueId id);

    public boolean deleteAll(List list);

    public T add(T obj);

    public T addAll(List<T> objList);
}
