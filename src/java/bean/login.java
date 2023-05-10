/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import pojo.Login;

/**
 *
 * @author FARREL
 */
@ManagedBean (name = "login")
@SessionScoped
@Entity
public class login implements Serializable {
    
    @Id
    private int id;
    private String username;
    private String password;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean checkuser(){
        
        boolean found = false;
        Login user = null;
        String dbUsername = null;
        String dbPassword = null;
        
        try {
            
            SessionFactory sessionFactory= new Configuration().configure().buildSessionFactory();
            Session session= sessionFactory.openSession();
            Transaction trans = session.beginTransaction();
            
            Query query=session.createQuery("from Login where username=:username and password=:password");
            query.setParameter("username", username);
            query.setParameter("password", password);

            user = (Login)query.uniqueResult();
            dbUsername = user.getUsername();
            dbPassword = user.getPassword();
            
            
//            List list= query.list();
            
//            Criteria criteria = session.createCriteria(Login.class).add(Restrictions.eq("username", username));
//            user = (Login)criteria.uniqueResult();
//            trans.commit();
//            
//            found = user != null;
            
            
            
            
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return (username.equals(dbUsername) && password.equals(dbPassword));
    }
    
    public static void main(String[] args) {
        login log = new login();
        String username = "admin";
        String password = "admin1";
        log.setUsername(username);
        log.setPassword(password);
        
        System.out.println(log.checkuser());
        
    }
    
    
    public login() {
    }
    
}