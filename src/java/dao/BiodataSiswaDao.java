/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.beanBiodata;
import pojo.BiodataSiswa;

/**
 *
 * @author Daffa PS
 */
public class BiodataSiswaDao extends BaseDao {
    
//    beanBiodata bean = new beanBiodata();
//    BiodataSiswa siswa = new BiodataSiswa();
    
    public BiodataSiswa getByNis(int nis) {
        return BiodataSiswa.class.cast(super.getById(BiodataSiswa.class, nis));
    }
}
