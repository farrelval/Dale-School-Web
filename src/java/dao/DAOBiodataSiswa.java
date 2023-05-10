/*/
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import bean.beanBiodata;
import pojo.BiodataSiswa;
import com.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author FARREL
 */
public class DAOBiodataSiswa {
    
    
    
    private List < BiodataSiswa > AllBio;
    
    public void addSiswa(BiodataSiswa newBio)
    {
        Transaction transObj = null;
        Session sessionObj = HibernateUtil.getSessionFactory().openSession();

        try
        {
            transObj = sessionObj.beginTransaction();
            sessionObj.save(newBio);

            System.out.println("Berhasil memasukkan data Siswa..");

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("berhasil simpan", newBio.getNis());

            transObj.commit();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            transObj.rollback();
        }
        finally
        {
            sessionObj.flush();
            sessionObj.close();
        }
    }
   
    
    public void deleteSiswa(BiodataSiswa Siswa)
    {
        Transaction transObj = null;
        Session sessionObj = HibernateUtil.getSessionFactory().openSession();

        try
        {
            transObj = sessionObj.beginTransaction();
            sessionObj.delete(Siswa);

            System.out.println("Berhasil menghapus data Siswa..");

            transObj.commit();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            transObj.rollback();
        }
        finally
        {
            sessionObj.flush();
            sessionObj.close();
        }
    }


    public void updateSiswa(BiodataSiswa Siswa)
    {
        Transaction transObj = null;
        Session sessionObj = HibernateUtil.getSessionFactory().openSession();

        try
        {
            transObj = sessionObj.beginTransaction();
            sessionObj.update(Siswa);

            System.out.println("Berhasil mengubah data Siswa..");

            transObj.commit();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            transObj.rollback();
        }
        finally
        {
            sessionObj.flush();
            sessionObj.close();
        }
    }  

    public List<BiodataSiswa> SearchById(int nis){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<BiodataSiswa> searchById = new ArrayList<>();
        try{
            session.beginTransaction();
            Query qu = session.createQuery("From BiodataSiswa U where U.nis =:nis");
            qu.setParameter("nis",nis);
            searchById = qu.list();
            int count = searchById.size();
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        finally{
            session.close();
        }
        return searchById;
    }
 
    public List< BiodataSiswa > getbyID(int nis) {
        Transaction transObj = null;
        Session sessionObj = HibernateUtil.getSessionFactory().openSession();
        List< BiodataSiswa > listStudents = new ArrayList();
        try{
            transObj = sessionObj.beginTransaction();
            Query query = sessionObj.createQuery("from BiodataSiswa where nis =:nis");
            query.setInteger("nis", nis);
            listStudents = query.list();
            transObj.commit();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        
        return listStudents;
    }

    public List<BiodataSiswa> retrieveStudents()
    {
        Transaction transObj = null;
        Session sessionObj = HibernateUtil.getSessionFactory().openSession();
        List listStudents = new ArrayList();

        try
        {
            transObj = sessionObj.beginTransaction();
            Query query = sessionObj.createQuery("from BiodataSiswa");

            listStudents = query.list();

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            transObj.commit();
        }

        return listStudents;
    }

    public List < BiodataSiswa > AllBio() { 
    Session session = HibernateUtil.getSessionFactory().openSession(); 
        try 
        { 
        session.beginTransaction(); 
        AllBio = session.createCriteria(BiodataSiswa.class).list(); 
        int count = AllBio.size(); 
        FacesMessage message1 = new 
        FacesMessage(FacesMessage.SEVERITY_INFO, "List Size",Integer.toString(count));//Debugging Purpose 
        //RequestContext.getCurrentInstance().showMessageInDialog(message1); 
        session.getTransaction().commit(); 
        } 
        catch (Exception e) 
        { 
        e.printStackTrace(); 
        session.getTransaction().rollback(); 
        } 
        session.close(); 
        return AllBio; 
 } 


}

