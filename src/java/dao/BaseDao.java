/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.util.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Dea Fitria
 */
public class BaseDao {
    
    HibernateUtil hibernateUtil = new HibernateUtil();
    Session session;
    Transaction trans;
    protected List<Object> listData;
    protected Object data;
    
    public List<Object> getAll(Class pojo) {
        session = hibernateUtil.getSessionFactory().openSession();
        trans = null;
        try {
            trans = session.beginTransaction();
            
            Criteria criteria = session.createCriteria(pojo); 
            listData = criteria.list();
            
            trans.commit();
        }
        catch(HibernateException e) {
            e.getMessage();
        }
        finally {
            session.close();
        }
        return listData;
    }
    
    public List<Object> getAllWithFetch(Class pojo, List<String> fetchs) {
        session = hibernateUtil.getSessionFactory().openSession();
        trans = null;
        try {
            trans = session.beginTransaction();
            
            Criteria criteria = session.createCriteria(pojo);
            fetchs.forEach((fetch) -> {
                criteria.setFetchMode(fetch, FetchMode.EAGER);
            });
            listData = criteria.list();
            
            trans.commit();
        }
        catch(HibernateException e) {
            e.getMessage();
        }
        finally {
            session.close();
        }
        return listData;
    }
    
    public Object getById(Class pojo, int id) {
        session = hibernateUtil.getSessionFactory().openSession();
        trans = null;
        try {
            trans = session.beginTransaction();
            
            Criteria criteria = session.createCriteria(pojo).add(Restrictions.eq("id", id));
            data = criteria.uniqueResult();
            
            trans.commit();
        }
        catch(HibernateException e) {
            e.getMessage();
        }
        finally {
            session.close();
        }
        return data;
    }
    
    public Object getByIdWithFetch(Class pojo, int id, List<String> fetchs) {
        session = hibernateUtil.getSessionFactory().openSession();
        trans = null;
        try {
            trans = session.beginTransaction();
            
            Criteria criteria = session.createCriteria(pojo).add(Restrictions.eq("ID", id));
            fetchs.forEach((fetch) -> {
                criteria.setFetchMode(fetch, FetchMode.EAGER);
            });
            
            data = criteria.uniqueResult();
            trans.commit();
        }
        catch(HibernateException e) {
            e.getMessage();
        }
        finally {
            session.close();
        }
        return data;
    }
    
    public Object getByProperty(Class pojo, String property, Object value) {
        session = hibernateUtil.getSessionFactory().openSession();
        try {
            trans = session.beginTransaction();
            
            Criteria criteria = session.createCriteria(pojo).add(Restrictions.eq(property, value));
            data = criteria.uniqueResult();
            
            trans.commit();
        }
        catch(HibernateException e) {
            e.getMessage();
        }
        finally {
            session.close();
        }
        return data;
    }
    
    public boolean update(Object pojo){
        session = hibernateUtil.getSessionFactory().openSession();
        trans = null;
        boolean succeed = false;
        try {
            trans = session.beginTransaction();
            session.update(pojo);
            trans.commit();
            succeed = true;
        }
        catch (Exception e){
            System.out.println(e);
        }
        finally {
            session.close();
        }
        
        return succeed;
    }
    
    public boolean create(Object pojo){
        session = hibernateUtil.getSessionFactory().openSession();
        trans = null;
        boolean succeed = false;
        try {
            trans = session.beginTransaction();
            session.save(pojo);
            trans.commit();
            succeed = true;
        }
        catch (Exception e){
            System.out.println(e);
        }
        finally {
            session.close();
        }
        
        return succeed;
    }
    
    public boolean delete(Object pojo){
        session = hibernateUtil.getSessionFactory().openSession();
        trans = null;
        boolean succeed = false;
        try {
            trans = session.beginTransaction();
            session.delete(pojo);
            trans.commit();
            succeed = true;
        }
        catch (Exception e){
            System.out.println(e);
        }
        finally {
            session.close();
        }
        
        return succeed;
    }
    
}