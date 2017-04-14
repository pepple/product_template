package com.pepple.webapp.dao.impl;

import com.pepple.webapp.dao.BaseHibernateDao;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
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
    private HibernateTemplate hibernateTemplate = getHibernateTemplate();
    private ClassMetadata classMetadata = this.getSessionFactory().getClassMetadata(pojoClass);

    public BaseHibernateDaoImpl(Class<T> pojoClass) {
        this.pojoClass = pojoClass;
    }

    /**
     * 根据一个id查找一个对象
     * @param uniqueId 唯一标识
     * @return 要查找的对象
     */
    @Override
    public T get(UniqueId uniqueId) {
        return hibernateTemplate.get(pojoClass,uniqueId);
    }

    /**
     * 根据多个id查找多个对象
     * @param list id的集合
     * @return 对象的集合
     */
    @Override
    public List<T> getByIds(List<UniqueId> list) {
        String pojoName = this.getPojoName();
        String keyName = this.getKeyName();
        String hql = " from " + pojoName + " where " + keyName + " in (:list)";
        Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameterList("list",list);
        return query.list();
    }

    /**
     * 根据id删除对象
     * @param uniqueId 唯一标识
     * @return 删除成功(true)/失败(false)
     */
    @Override
    public boolean delete(UniqueId uniqueId) {
        T obj = this.get(uniqueId);
        try{
            hibernateTemplate.delete(obj);
            return true;
        }
        catch (Exception e0) {
            logger.error("操作失败",e0);
        }
        return false;
    }

    /**
     * 根据id list删除指定的多个对象
     * @param list id list
     * @return 删除成功(true)/失败(false)
     */
    @Override
    public boolean deleteAll(List<UniqueId> list) {
        try {
            hibernateTemplate.deleteAll(list);
            hibernateTemplate.flush();
            return true;
        } catch (Exception e0) {
            logger.error("操作失败",e0);
        }
        return false;
    }

    /**
     * 保存指定的对象
     * @param obj 要保存的对象
     * @return 保存的对象
     */
    @Override
    public T save(T obj) {
        return hibernateTemplate.merge(obj);
    }

    @Override
    public T saveOrUpdate(T obj) {
        return null;
    }

    @Override
    public T addAll(List<T> objList) {
        return null;
    }

    @Override
    public T update(T obj) {
        hibernateTemplate.update(obj);
        return null;
    }

    /**
     * @return 实体类的名称
     */
    private String getPojoName() {
        return classMetadata.getEntityName();
    }

    private String getKeyName() {
        return classMetadata.getIdentifierPropertyName();
    }
}
