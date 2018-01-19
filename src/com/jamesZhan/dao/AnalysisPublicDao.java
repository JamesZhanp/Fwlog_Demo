package com.jamesZhan.dao;

import com.jamesZhan.utils.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by 16255 on 2017/11/7.
 */
public class AnalysisPublicDao {
    public List getAllTableMes(int num){
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        List<Object> list = null;
        try{
            String hql = null;
            if(num == 0)
                hql="from SrcipanalysisEntity ";
            if(num == 1)
                hql = "from SrcportanalysisEntity ";
            if(num == 2)
                hql = "from DestipanalysisEntity ";
            if(num == 3)
                hql = "from DestportanalysisEntity ";
            Query query = session.createQuery(hql);
            list = query.list();
            tx.commit();
        }catch (Exception e){
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }finally {
            if(session != null)
                session.close();
        }
        return list;
    }
}
