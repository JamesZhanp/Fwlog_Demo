package com.jamesZhan.dao;

import com.jamesZhan.utils.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by 16255 on 2017/11/3.
 * @author jamesZhan
 * this is the common function which is used in many different entity
 * just like insert and so on
 */
public class PublicFunctionDao {
    //访问数据的阈值，若是小于，则可以舍去
    private double MIN_INTERVAL = 3.00;
    /**
     * put the message into different database table
     * @param  object
     * */

    public void insert(Object object){
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        try{
            session.save(object);
            tx.commit();
        }catch (Exception e){
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }finally {
            if (session != null){
                session.close();
            }
        }
    }

    /**
     * find the same type and statTime and endTime's ID
     * @param statisticsValue
     * @param startTime
     * @param endTime
     * @param num
     * */

    public Integer findID(String statisticsValue,String startTime,String endTime,int num){
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        Integer ID = null;
        try{
            String hql = null;
            if(num == 0)
                hql = "select o1.id from OriginalsrcipEntity o1 where o1.statisticsValue= :value and o1.startTime = :st and o1.endTime = :et";
            if(num == 1)
                hql = "select o2.id from OriginalsrcportEntity o2 where o2.statisticsValue= :value and o2.startTime = :st and o2.endTime = :et";
            if(num == 2)
                hql = "select o3.id from OriginaldestipEntity o3 where o3.statisticsValue= :value and o3.startTime = :st and o3.endTime = :et";
            if(num == 3)
                hql = "select o4.id from OriginaldestportEntity o4 where o4.statisticsValue= :value and o4.startTime = :st and o4.endTime = :et";
            Query  query = session.createQuery(hql);
            query.setString("value",statisticsValue);
            query.setString("st",startTime);
            query.setString("et",endTime);
            List list = query.list();
            if(list.size() > 0){
//                get the first message
                ID = (Integer)query.list().get(0);
            }
        }catch (Exception e){
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }finally {
            if(session != null)
                session.close();
        }
        return ID;
    }

    /**
     * update the count
     * use the subscript to representative the different table
     * @param ID
     * @param num
     * */

    public void update(Integer ID,int num){
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        try{
            String hql = null;
            if(num == 0)
                hql = "update OriginalsrcipEntity o1 set o1.count = o1.count + 1 where o1.id = :id";
            if(num == 1)
                hql = "update OriginalsrcportEntity o2 set o2.count = o2.count + 1 where o2.id = :id";
            if(num == 2)
                hql = "update OriginaldestipEntity o3 set o3.count = o3.count + 1 where o3.id = :id";
            if(num == 3)
                hql = "update OriginaldestportEntity o4 set o4.count = o4.count + 1 where o4.id = :id";

            Query query = session.createQuery(hql);
            query.setLong("id",ID);
            query.executeUpdate();
            tx.commit();
        }catch(Exception e){
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }finally{
            if(session != null){
                session.close();
            }
        }

    }

    /**
     * to get the group of the table
     * @param num
     * */
    public List getAllMessage(int num){
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        List<Object> list = new LinkedList<>();
        try{
            String hql = null;
            if(num == 0)
                hql = "from OriginalsrcipEntity o1 group by o1.statisticsValue";
            if(num == 1)
                hql = "from OriginalsrcportEntity o2 group by o2.statisticsValue";
            if (num == 2)
                hql = "from OriginaldestipEntity o3 group by o3.statisticsValue";
            if(num == 3)
                hql = "from OriginaldestportEntity o4 group by o4.statisticsValue";
            Query query = session.createQuery(hql);
            list = query.list();
            tx.commit();
        }catch(Exception e){
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }finally {
            if(session != null)
                session.close();
        }
        return list;

    }

    /**
     * get the detail message of every group
     * @param statisticsValue
     * @param num
     * */
    public List getDetailForGroup(String statisticsValue,int num){
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        List<Object> list = null;
        try{
            String hql = null;
            if (num == 0)
                hql = "select o1 from OriginalsrcipEntity o1 where o1.statisticsValue = :value and o1.count >= 3.00";
            if (num == 1)
                hql = "select o2 from OriginalsrcportEntity o2 where o2.statisticsValue = :value and o2.count >= 3.00";
            if (num == 2)
                hql = "select o3 from OriginaldestipEntity o3 where o3.statisticsValue = :value and o3.count >= 3.00";
            if(num == 3)
                hql = "select o4 from OriginaldestportEntity o4 where o4.statisticsValue = :value and o4.count >= 3.00";
            Query query = session.createQuery(hql);
            query.setString("value",statisticsValue);
            list = query.list();
            tx.commit();
        }catch(Exception e){
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
