/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.BiodataSiswaDao;
import dao.DAOBiodataSiswa;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.primefaces.context.RequestContext;
import pojo.BiodataSiswa;

/**
 *
 * @author FARREL
 */
@ManagedBean (name = "beanBiodata")
@SessionScoped
@Entity
public class beanBiodata implements Serializable {

    
    private int nis;
    private String name;
    private String gender;
    private Date birth;
    private String address;
    private int class_;
    private String religion;

    private String msg;
    @OneToMany
    private List<BiodataSiswa> listStudents;
    @OneToMany
    private List<BiodataSiswa> searchByNis;

    
    BiodataSiswa biodata = new BiodataSiswa();
    BiodataSiswa newBio = new BiodataSiswa();
    @Id
    private Long id;

//    BiodataSiswaDao dao = new BiodataSiswaDao();
    
    /**
     * Creates a new instance of test
     */
    public beanBiodata() {
    }
    
    
    public int getNis() {
        return nis;
    }

    public void setNis(int nis) {
        this.nis = nis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getClass_() {
        return class_;
    }

    public void setClass_(int class_) {
        this.class_ = class_;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    
    public List<BiodataSiswa> getListStudents() {
        return listStudents;
    }

    public void setListStudents(List<BiodataSiswa> listStudents) {
        this.listStudents = listStudents;
    }

    public BiodataSiswa getBiodata() {
        return biodata;
    }

    public void setBiodata(BiodataSiswa biodata) {
        this.biodata = biodata;
    }
    
     public BiodataSiswa getNewBio() {
        return newBio;
    }

    public void setNewBio(BiodataSiswa newBio) {
        this.newBio = newBio;
    }
    
    public List<BiodataSiswa> getSearchByNis() {
        return searchByNis;
    }

    public void setSearchByNis(List<BiodataSiswa> searchByNis) {
        this.searchByNis = searchByNis;
    }
    
    public void clearAll(){
        this.nis = 0;
        this.name = null;
        this.gender = null;
        this.birth = null;
        this.address = null;
        this.class_ = 0;
        this.religion = null;
    }
    
    
    public void saveData()
    {
        DAOBiodataSiswa murid = new DAOBiodataSiswa();
        murid.addSiswa(newBio);
        this.msg = "Your data has been submitted!";
        this.clearAll();
    }
    
    public void updateData(){
        DAOBiodataSiswa murid = new DAOBiodataSiswa();
        murid.updateSiswa(biodata);
        this.msg = "Your data has been updated!";
        this.clearAll();
    }
    
    public void deleteData() {
        DAOBiodataSiswa murid = new DAOBiodataSiswa();
        murid.deleteSiswa(biodata);
        this.msg = "Your data has been deleted!";
        this.clearAll();
    }

    /**
     *
     * @return
     */
    public String searchByNislist()
    {
//        this.msg = null;
//        DAOBiodataSiswa siswa = new DAOBiodataSiswa();
//        searchByNis = siswa.SearchById(nis);
//        return searchByNis;
        
//        BiodataSiswa siswa = dao.getByNis(nis);
//        this.nis = siswa.getNis();
//        this.name = siswa.getName();
//        this.gender = siswa.getGender();
//        this.birth = siswa.getBirth();
//        this.address = siswa.getAddress();
//        this.class_ = siswa.getClass_();
//        this.religion = siswa.getReligion();
        
        return "Siswa.xhtml";
    }
 
    
    public void SearchByNis()
    {
        DAOBiodataSiswa murid = new DAOBiodataSiswa();
        searchByNis = murid.getbyID(nis);
        int count = searchByNis.size();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Number of Record Selected:", Integer.toString(count));

        RequestContext.getCurrentInstance().showMessageInDialog(message);
        
    }
    
    public List<BiodataSiswa> ListStudents()
    {
        this.msg = null;
        DAOBiodataSiswa siswa = new DAOBiodataSiswa();
        listStudents = siswa.AllBio();
        return listStudents;     
    }
 
    public List<BiodataSiswa> getAllRecords()
    {
        this.msg = null;
        DAOBiodataSiswa students = new DAOBiodataSiswa();
        listStudents = students.retrieveStudents();
        return listStudents;     
    }
    
    public void changeBiodata (BiodataSiswa biodata){
        this.biodata = biodata;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
//    public static void main(String[] args) {
//        beanBiodata bean = new beanBiodata();
//        DAOBiodataSiswa dao = new DAOBiodataSiswa();
//        bean.setNis(11223344);
//        List<BiodataSiswa> list = bean.searchByNislist();
//        
//        System.out.println(bean.searchByNis.get(0));
//        System.out.println(bean.searchByNis.get(0).getName());
//        System.out.println(bean.searchByNis.get(0).getAddress());
//    }
    
    
}
