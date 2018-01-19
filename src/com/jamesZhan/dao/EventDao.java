package com.jamesZhan.dao;

import com.jamesZhan.entity.EventEntity;
import com.jamesZhan.utils.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by 16255 on 2017/11/10.
 */
public class EventDao {
    public List getAllEvent(){
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        List<EventEntity> list = new LinkedList<>();
        try{
            Query query = session.createQuery("from EventEntity ");
            list = query.list();
            tx.commit();

        }catch(Exception e){
            if(tx != null)
                tx.commit();
            e.printStackTrace();
        }finally{
            if(session != null)
                session.close();
        }
        return list;
    }
}
