/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piesusu;

import java.awt.List;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class Produk {
    private int id;
    private String nama;
    private int harga;
    private int stok;
    private Koneksi kon;
    
    public Produk(){
        kon = new Koneksi();
    }
    
    public Produk(String n, int h, int s){
        kon = new Koneksi();
        nama=n;
        harga=h;
        stok=s;
    }
    
    public List listProduk(){
        List hasil = new List();
        try {
            kon.start();
            String sql="select nama from produk";
            kon.rs=kon.st.executeQuery(sql);
            while(kon.rs.next()){
                hasil.add(kon.rs.getString("nama"));
            }
            kon.stop();
        } catch (SQLException ex) {
            Logger.getLogger(Produk.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }
    
    public int getHarga(String dt){
        int hasil = 0;
        try {
            kon.start();
            String sql="select harga from produk where nama = '"+dt+"'";
            kon.rs=kon.st.executeQuery(sql);
            if(kon.rs.next()){
                hasil=kon.rs.getInt("harga");
            }
            kon.stop();
        } catch (SQLException ex) {
            Logger.getLogger(Produk.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }
    
    public boolean create(){
        boolean hasil = false;
        kon.start();
        String sql="insert into produk (nama, harga, stok) value ('"+nama+"','"+harga+"','"+0+"')";
        try {
            kon.st.executeUpdate(sql);
            hasil=true;
        } catch (SQLException ex) {
            Logger.getLogger(Produk.class.getName()).log(Level.SEVERE, null, ex);
        }
        kon.stop();
        return hasil;
    }
    
    public boolean show(int data){
        boolean hasil = false;
        try {
            kon.start();
            String sql="select * from produk where id='"+data+"'";
            kon.rs=kon.st.executeQuery(sql);
            if(kon.rs.next()){
                nama=kon.rs.getString("nama");
                harga=kon.rs.getInt("harga");
                stok=kon.rs.getInt("stok");
            }
            hasil=true;
            kon.stop();
        } catch (SQLException ex) {
            Logger.getLogger(Produk.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }
    
    public boolean update(int data){
        boolean hasil = false;
        try {
            kon.start();
            String sql="update produk set nama='"+nama+"', harga='"+harga+"', stok='"+stok+"' where id='"+data+"'";
            kon.st.executeUpdate(sql);
            hasil=true;
            kon.stop();
        } catch (SQLException ex) {
            Logger.getLogger(Produk.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }
    
    public boolean delete(int data){
        boolean hasil = false;
        try {
            kon.start();
            String sql="delete from produk where id='"+data+"'";
            kon.st.executeUpdate(sql);
            hasil=true;
            kon.stop();
        } catch (SQLException ex) {
            Logger.getLogger(Produk.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }
    
    public DefaultTableModel getTable(){
        String[]judul={"NO","ID BARANG","NAMA BARANG","HARGA","STOK","",""};
        DefaultTableModel tabel = new DefaultTableModel(null,judul);
        try{
            kon.start();
            String query = "select * from produk";
            kon.rs=kon.st.executeQuery(query);
            String data[]=new String[7];
            int angka = 1;
            while(kon.rs.next()){
                data[0]=String.valueOf(angka);
                data[1]=kon.rs.getString(1);
                data[2]=kon.rs.getString(2);
                data[3]=kon.rs.getString(4);
                data[4]=kon.rs.getString(5);
                data[5]="update";
                data[6]="delete";
                tabel.addRow(data);
                angka++;
            }
            kon.stop();
        }
        catch(Exception e){};
        return tabel;
    }
}
