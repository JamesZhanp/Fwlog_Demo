package com.jamesZhan.dao;

import com.jamesZhan.entity.SensitiveportEntity;
import com.jamesZhan.utils.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by 16255 on 2017/11/5.
 */
public class SensitivePortDao {
    public List FindAll(){
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        Query query = session.createQuery("select sPort from SensitiveportEntity sPort");
        List<SensitiveportEntity> sPorts = query.list();
        tx.commit();
        session.close();
        return sPorts;
    }
}
