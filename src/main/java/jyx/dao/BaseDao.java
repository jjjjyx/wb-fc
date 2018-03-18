package jyx.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.Table;

/**
 * 所有dao的基类
 * 有两个构造方法
 *
 * @param <T>  实体类型
 * @param <PK> 主键
 * @author jjjjyx
 * @version 1.1
 */
@Transactional
public class BaseDao<T, PK extends Serializable> {

    private boolean allowCreate = false;
    private boolean alwaysUseNewSession = false;

    protected Logger logger = LoggerFactory.getLogger(getClass());


    protected Class<T> entityClass;
    protected String tableName;
    protected Session session;
//    @Autowired
//    protected HibernateTemplate hibernateTemplate;
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
    @Autowired
    protected SessionFactory sessionFactory;


    /**
     * 通过反射获取子类确定的泛型类
     */
    @SuppressWarnings("unchecked")
    public BaseDao() {
        entityClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        Table table = (Table) entityClass.getAnnotation(Table.class);
        if (table != null)
            this.tableName = table.name();
        else {
            throw new IllegalArgumentException("该实体类没有表名");
        }
    }


    /**
     * 用于用于省略Dao层, 在Service层直接使用通用的BaseDao构造函数. 在构造函数中定义对象类型Class. eg.
     * BaseDao<User, Long> userDao = new BaseDao<User, Long>(User.class);
     */
    public BaseDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * 根据id加载PO实例, 如果二级缓存中存在, 则读取二级缓存的数据
     *
     * @param id
     * @return 返回相应的持久化PO实例
     */
    public T load(PK id) {
        Assert.notNull(id);

        if (logger.isTraceEnabled()) {
            logger.trace("load entity id[" + id + "], entityClass["
                    + entityClass + "]");
        }
        return (T) getSession().load(entityClass, id);
    }

    /**
     * 根据id获取PO实例, 如果一级缓存不存在, 则从数据库中读取
     *
     * @param id
     * @return 返回相应的持久化PO实例
     */
    public T get(PK id) {
        Assert.notNull(id);

        if (logger.isTraceEnabled()) {
            logger.trace("get entity id[" + id + "], entityClass["
                    + entityClass + "]");
        }
        T t = (T) getSession().get(entityClass, id);

        return t;
    }

    public T get(String hql, Object... params) {

        List<T> l = this.find(hql, params);
        if (l != null && l.size() > 0) {
            return l.get(0);
        }
        return null;
    }

    public T get(String hql, Map<String, Object> params) {
        Assert.notNull(hql);
        if (logger.isTraceEnabled()) {
            logger.trace("get entity hql[" + hql + "], entityClass["
                    + entityClass + "]");
        }

        Query q = this.getSession().createQuery(hql);
        this.setParameterToQuery(q, params);
        List<T> l = q.list();
        if (l != null && l.size() > 0) {
            return l.get(0);
        }
        return null;
    }


    /**
     * 删除PO
     *
     * @param entity
     */
    public void delete(T entity) {
        Assert.notNull(entity);

        if (logger.isTraceEnabled()) {
            logger.trace("delete entity[" + entity.toString()
                    + "], entityClass[" + entityClass + "], " + "information["
                    + ToStringBuilder.reflectionToString(entity) + "]");
        }
        getSession().delete(entity);
    }

    /**
     * 删除po 全部对象
     */
    public void deleteAll(List<T> entity) {
        if (logger.isTraceEnabled()) {
            logger.trace("delete entity[" + entity.toString()
                    + "], entityClass[" + entityClass + "], " + "information["
                    + ToStringBuilder.reflectionToString(entity) + "]");
        }
//		getSession().deleteAll(entity);
    }

    /**
     * 更新PO
     *
     * @param entity
     */
    public void update(T entity) {
        Assert.notNull(entity);

        if (logger.isTraceEnabled()) {
            logger.trace("update entity[" + entity.toString()
                    + "], entityClass[" + entityClass + "]," + " information["
                    + ToStringBuilder.reflectionToString(entity) + "]");
        }
        getSession().update(entity);
    }

    /**
     * 保存PO
     *
     * @param entity
     */
    public Serializable save(T entity) {
        Assert.notNull(entity);

        if (logger.isTraceEnabled()) {
            logger.trace("save entity[" + entity.toString() + "], entityClass["
                    + entityClass + "]," + " information["
                    + ToStringBuilder.reflectionToString(entity) + "]");
        }
        return getSession().save(entity);
    }

    /**
     * 保存或更新PO
     *
     * @param entity
     */
    public void saveOrUpdate(T entity) {
        Assert.notNull(entity);

        if (logger.isTraceEnabled()) {
            logger.trace("saveOrUpdate entity[" + entity.toString()
                    + "], entityClass[" + entityClass + "], " + "information["
                    + ToStringBuilder.reflectionToString(entity) + "]");
        }
        getSession().saveOrUpdate(entity);
    }

    /**
     * @param entity
     */
    public void merge(T entity) {
        Assert.notNull(entity);

        if (logger.isTraceEnabled()) {
            logger.trace("merge entity[" + entity.toString()
                    + "], entityClass[" + entityClass + "], " + "information["
                    + ToStringBuilder.reflectionToString(entity) + "]");
        }
        getSession().merge(entity);
    }


    /**
     * 获取PO的所有对象
     *
     * @return PO的所有对象
     */
    public List<T> loadAll() {

//		List<T> lst = getSession().loadDataAll(entityClass);
        Criteria criteria = getSession().createCriteria(entityClass);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//		criteria
        if (logger.isTraceEnabled()) {
            logger.trace("load all entities[" + entityClass + "]...");
        }
        List<T> lst = criteria.list();
        return lst; //lst;
    }

    /**
     * @param hql
     * @return
     */
    public List<T> find(String hql) {
        return this.find(hql, (Object[]) null);
    }


    /**
     * 执行带参的HQL查询
     *
     * @param hql
     * @param params
     * @return 查询结果
     */
    public List<T> find(String hql, Object[] params) {
        Assert.hasText(hql);

        if (logger.isTraceEnabled()) {
            logger.trace("find entities[{}], entityClass[{}], " + "params[{}]", hql, entityClass, Arrays.toString(params));
        }
        Query queryObject = getSession().createQuery(hql);
        this.setParameterToQuery(queryObject, params);

        List<T> lst = queryObject.list(); //getSession().find(hql, params);

        return lst;
    }

    public List<T> find(final String hql, final Map<String, Object> params) {
        return this.find(hql, 0, 0, params);
    }

    public List<T> find(final String hql, final int offset, final int max, final Map<String, Object> params) {
        Assert.hasText(hql);
        if (logger.isTraceEnabled()) {
            logger.trace("find entities[{}], entityClass[{}], " + "params[offset={}, max={},{}]", hql, entityClass, offset, max, params);
        }
        Query query = getSession().createQuery(hql);
        this.setParameterToQuery(query, params);
        query.setFirstResult(offset);
        query.setMaxResults(max);

        return query.list();
    }


    public List<T> findBySql(String sql, Object[] params) {
        Assert.hasText(sql);

        if (logger.isTraceEnabled()) {
            logger.trace("find entities[" + sql + "], entityClass["
                    + entityClass + "], " + "params["
                    + ToStringBuilder.reflectionToString(params) + "]");
        }
        SQLQuery sqlQuery = getSession().createSQLQuery(sql);
        sqlQuery.addEntity(this.entityClass);
        this.setParameterToQuery(sqlQuery, params);

        return sqlQuery.list();
    }

    public List<T> findBySql(final String sql, final Map<String, Object> params) {
        return findBySql(sql,0,0,params);
    }

    public List<T> findBySql(final String sql, final int offset, final int max, final Map<String, Object> params) {
        Assert.hasText(sql);
        if (logger.isTraceEnabled()) {
            logger.trace("find entities[{}], entityClass[{}], " + "params[offset={}, max={},{}]", sql, entityClass, offset, max, params);
        }
        SQLQuery sqlQuery = getSession().createSQLQuery(sql)
                .addEntity(this.entityClass);
        this.setParameterToQuery(sqlQuery, params);
        sqlQuery.setFirstResult(offset);
        sqlQuery.setMaxResults(max);

        return sqlQuery.list();
    }

    /**
     * 刷新session缓存, 将缓存同步数据库
     */
    public void flush() {
        getSession().flush();
    }




    public int executeHql(String hql) {
        Query q = getSession().createQuery(hql);
        return q.executeUpdate();
    }

    public int executeHql(String hql, Map<String, Object> params) {
        Query q = getSession().createQuery(hql);
        this.setParameterToQuery(q, params);
        return q.executeUpdate();
    }

    public int executeHql(String hql, Object... objects) {
        Query q = getSession().createQuery(hql);
        this.setParameterToQuery(q, objects);
        return q.executeUpdate();
    }

    /**
     * 统计个数
     * @param hql
     * @param params
     * @return
     */
    public int count(String hql, Map<String, Object> params) {
        Assert.hasText(hql);

        if(hql.matches("(?is)^from.+"))
            hql = "select count(*) "+ hql;

        if(!hql.matches("(?is)^select count.+")){
            if(logger.isWarnEnabled()) {
                logger.warn("错误查询总数:[{}] params={}", hql, params);
            }
            return 0;
        }

        if(logger.isTraceEnabled()) {
            logger.trace("count entities[{}], entityClass[{}], " + "params={}", hql, entityClass, params);
        }

        Query query = getSession().createQuery( hql);
        this.setParameterToQuery(query, params);
        return ((Number)query.uniqueResult()).intValue();

    }


    /**
     * 从当前线程获取session, 如果没有, 则新建一个 包中都可见，而且其子类也能访问 注:使用时, 请判断获取的session是否属于当前事务,
     * 如果取得的session不在事务上下文中, 需要手动release session
     *
     * @return
     */
    @SuppressWarnings("unused")
    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean isAllowCreate() {
        return allowCreate;
    }

    public void setAllowCreate(boolean allowCreate) {
        this.allowCreate = allowCreate;
    }

    public boolean isAlwaysUseNewSession() {
        return alwaysUseNewSession;
    }

    public void setAlwaysUseNewSession(boolean alwaysUseNewSession) {
        this.alwaysUseNewSession = alwaysUseNewSession;
    }

    protected void setParameterToQuery(Query q, Map<String, Object> params) {
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                if (params.get(key) instanceof Object[]) {
                    Object[] objs = (Object[]) params.get(key);
                    q.setParameterList(key, objs);
                } else if (params.get(key) instanceof Collection<?>) {
                    Collection<?> collection = (Collection<?>) params.get(key);
                    q.setParameterList(key, collection);
                } else {
                    q.setParameter(key, params.get(key));
                }
            }
        }
    }

    /**
     * @param q
     * @param params 当前支持普通对象,不支持集合与数组
     */
    protected void setParameterToQuery(Query q, Object... params) {
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                Object object = params[i];
                q.setParameter(String.valueOf(i), object);
            }
        }
    }

    /**
     * @param q
     * @param params 当前支持普通对象,不支持集合与数组
     */
    protected void setParameterToQuery(Query q, List<?> params) {
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                Object object = params.get(i);
                q.setParameter(i, object);
            }
        }
    }

}
