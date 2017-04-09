/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piesusu;

import java.awt.List;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class Belanjaan {
    private int id;
    private int total;
    private Date tanggal;
    private Koneksi kon;
    
    public Belanjaan(){
        kon = new Koneksi();
    }
    
    public Belanjaan(int t){
        kon = new Koneksi();
        total = t;
    }
    
    public int getLastId(){
        int hasil = 0;
        try {
            kon.start();
            String sql1 = "select id from belanjaan order by id desc limit 1";
            kon.rs=kon.st.executeQuery(sql1);
            if(kon.rs.next()){
                hasil = kon.rs.getInt("id");
            }
            kon.stop();
        } catch (SQLException ex) {
            Logger.getLogger(Belanjaan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }
    
    public boolean create(List n, List hs, List j, List ht){
        boolean hasil = false;
        try {
            kon.start();
            String sql = "insert into belanjaan (total) value ('"+total+"')";
            kon.st.executeUpdate(sql);
            sql = "select id from belanjaan order by id desc limit 1";
            kon.rs=kon.st.executeQuery(sql);
            if(kon.rs.next()){
                id=kon.rs.getInt("id");
            }
            for(int i=0; i<n.getItemCount(); i++){ 
                String sql2 = "insert into belanjaan_detail (id_belanjaan, nama, harga_satuan, jumlah, harga_total) value ('"+id+"','"+n.getItem(i)+"','"+hs.getItem(i)+"','"+j.getItem(i)+"','"+ht.getItem(i)+"')";
                kon.st.executeUpdate(sql2);
            }
            hasil = true;
            kon.stop();
        } catch (SQLException ex) {
            Logger.getLogger(Belanjaan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }
    
    public DefaultTableModel getTableBelanjaan(){
        String[]judul={"NO","ID","TOTAL","TANGGAL",""};
        DefaultTableModel tabel = new DefaultTableModel(null,judul);
        try{
            kon.start();
            String query = "select * from belanjaan";
            kon.rs=kon.st.executeQuery(query);
            String data[]=new String[6];
            int angka = 1;
            while(kon.rs.next()){
                data[0]=String.valueOf(angka);
                data[1]=kon.rs.getString(1);
                data[2]=kon.rs.getString(2);
                data[3]=kon.rs.getString(3);
                data[4]="Detail";
                tabel.addRow(data);
                angka++;
            }
            kon.stop();
        }
        catch(Exception e){};
        return tabel;
    }
    
    public DefaultTableModel getTableBelanjaanDetail(int dt){
        String[]judul={"NO","NAMA BARANG","HARGA SATUAN","JUMLAH","HARGA TOTAL"};
        DefaultTableModel tabel = new DefaultTableModel(null,judul);
        try{
            kon.start();
            String query = "select * from belanjaan_detail where id_belanjaan='"+dt+"'";
            kon.rs=kon.st.executeQuery(query);
            String data[]=new String[5];
            int angka = 1;
            while(kon.rs.next()){
                data[0]=String.valueOf(angka);
                data[1]=kon.rs.getString(3);
                data[2]=kon.rs.getString(4);
                data[3]=kon.rs.getString(5);
                data[4]=kon.rs.getString(6);
                tabel.addRow(data);
                angka++;
            }
            kon.stop();
        }
        catch(Exception e){};
        return tabel;
    }
    
}
