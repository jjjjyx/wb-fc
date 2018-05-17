package jyx.dao;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import jyx.model.IntegralSet;

@Repository
public class IntegralSetDao extends BaseDao<IntegralSet, Integer> {

    public Map<String, Object> getIntegralSet() {
        String sql = "select rank,maxReward,minReward,intervalNums,cycle,";
        sql += " date_format(nextGrantTime,'%Y-%m-%d %H:%i:%s') nextGrantTime, ";
        sql += " date_format(lastGrantTime,'%Y-%m-%d %H:%i:%s') lastGrantTime from fc_integral_set ";
        if (session == null || !session.isOpen()) {
            session = sessionFactory.getCurrentSession();
        }
        SQLQuery query = session.createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> datas = query.list();
        if (datas != null && datas.size() > 0) {
            return datas.get(0);
        } else {
            return null;
        }
    }

    public boolean addIntegralSet(IntegralSet integralSet) {
        String sql = "INSERT INTO fc_integral_set(rank,maxReward,minReward,intervalNums,cycle) VALUES (?,?,?,?,?)";
        if (session == null || !session.isOpen()) {
            session = sessionFactory.getCurrentSession();
        }
        SQLQuery query = session.createSQLQuery(sql);
        query.setParameter(0, integralSet.getRank());
        query.setParameter(1, integralSet.getMaxReward());
        query.setParameter(2, integralSet.getMinReward());
        query.setParameter(3, integralSet.getIntervalNums());
        query.setParameter(4, integralSet.getCycle());
//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
//query.setParameter(5, format.format(integralSet.getLastGrantTime()));
//query.setParameter(6, format.format(integralSet.getNextGrantTime()));
        return query.executeUpdate() > 0 ? true : false;
    }

    public boolean updateIntegralSet(IntegralSet integralSet) {
        String sql = "update fc_integral_set set rank = ? ,maxReward = ?,minReward = ?,intervalNums = ?,cycle = ? ";
        if (session == null || !session.isOpen()) {
            session = sessionFactory.getCurrentSession();
        }
        SQLQuery query = session.createSQLQuery(sql);
        query.setParameter(0, integralSet.getRank());
        query.setParameter(1, integralSet.getMaxReward());
        query.setParameter(2, integralSet.getMinReward());
        query.setParameter(3, integralSet.getIntervalNums());
        query.setParameter(4, integralSet.getCycle());
        boolean b = query.executeUpdate() > 0 ? true : false;
//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
//query.setParameter(5, format.format(integralSet.getLastGrantTime()));
//query.setParameter(6, format.format(integralSet.getNextGrantTime()));
        return b;
    }
}
