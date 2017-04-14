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
     * 根据一个id查找一个对象
     * @param id 唯一标识
     * @return 要查找的对象
     */
    public T get(UniqueId id);

    /**
     * 根据多个id查找多个对象
     * @param list id的集合
     * @return 对象的集合
     */
    public List<T> getByIds(List<UniqueId> list);

    /**
     * 根据id删除对象
     * @param id 唯一标识
     * @return 删除成功(true)/失败(false)
     */
    public boolean delete(UniqueId id);

    /**
     * 根据id list删除指定的多个对象
     * @param list id list
     * @return 删除成功(true)/失败(false)
     */
    public boolean deleteAll(List<UniqueId> list);

    /**
     * 保存指定的对象
     * @param obj 要保存的对象
     * @return 保存的对象
     */
    public T save(T obj);

    public T saveOrUpdate(T obj);

    public T addAll(List<T> objList);

    public T update(T obj);

}
