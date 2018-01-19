package com.jamesZhan.dao;

import com.jamesZhan.entity.OriginaldestportEntity;
import com.jamesZhan.utils.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by 16255 on 2017/11/7.
 * @author jamesZhan
 */
public class DestPortAnalysisDao {
    public List getAllDestPort(){
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        List<OriginaldestportEntity> list = null;
        try{
            String hql = "from OriginaldestportEntity o group by o.statisticsValue";
            Query query = session.createQuery(hql);
            list = query.list();
            tx.commit();
        }catch(Exception e){
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }finally {
            if(session != null){
                session.close();
            }
        }
        return list;
    }

    /**
     * to get the access history where statisticsValue = :value
     * @param statisticsValue
     * */
    public List getAccessList(String statisticsValue){
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        List<OriginaldestportEntity> list = null;
        try {
            String hql = "from OriginaldestportEntity o where o.statisticsValue = :type order by o.startTime";
            Query query = session.createQuery(hql);
            query.setString("type",statisticsValue);
            list = query.list();
            tx.commit();
        }catch (Exception e){
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }finally {
            if (session != null)
                session.close();
        }
        return list;
    }
}
