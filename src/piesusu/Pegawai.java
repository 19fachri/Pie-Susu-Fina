/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piesusu;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class Pegawai{
    private String nama;
    private String password;
    private Koneksi kon;
    
    public Pegawai(){
        kon = new Koneksi();
    }
    
    public Pegawai(String n, String p){
        kon = new Koneksi();
        nama=n;
        password=p;
    }
    
    public boolean login() {
        boolean hasil=false;
        try {
            kon.start();
            String sql="select * from Pegawai where nama='"+nama+"' && password='"+password+"'";
            kon.rs=kon.st.executeQuery(sql);
            if(kon.rs.next()){
                hasil=true;
            }
            kon.stop();
        } catch (SQLException ex) {
            Logger.getLogger(Pegawai.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }
}
