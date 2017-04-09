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
public class Mitra {
    private int id;
    private String nama;
    private String pemilik;
    private String no;
    private String alamat;
    private Koneksi kon;
    
    public Mitra(){
        kon = new Koneksi();
    }
    
    public Mitra(String n, String p, String no, String a){
        kon = new Koneksi();
        nama=n;
        pemilik=p;
        this.no=no;
        alamat=a;
    }
    
    public String getNama(){
        return nama;
    }
    
    public String getPemilik(){
        return pemilik;
    }
    
    public String getKontak(){
        return no;
    }
    
    public String getAlamat(){
        return alamat;
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
        String sql="insert into mitra (nama, pemilik, no, alamat) value ('"+nama+"','"+pemilik+"','"+no+"','"+alamat+"')";
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
            String sql="select * from mitra where id='"+data+"'";
            kon.rs=kon.st.executeQuery(sql);
            if(kon.rs.next()){
                id=kon.rs.getInt("id");
                nama=kon.rs.getString("nama");
                pemilik=kon.rs.getString("pemilik");
                no=kon.rs.getString("no");
                alamat=kon.rs.getString("alamat");
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
            String sql="update mitra set nama='"+nama+"', pemilik='"+pemilik+"', no='"+no+"', alamat='"+alamat+"' where id='"+data+"'";
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
            String sql="delete from mitra where id='"+data+"'";
            kon.st.executeUpdate(sql);
            hasil=true;
            kon.stop();
        } catch (SQLException ex) {
            Logger.getLogger(Produk.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }
    
    public DefaultTableModel getTable(){
        String[]judul={"NO","KODE","NAMA MITRA","KONTAK","",""};
        DefaultTableModel tabel = new DefaultTableModel(null,judul);
        try{
            kon.start();
            String query = "select * from mitra";
            kon.rs=kon.st.executeQuery(query);
            String data[]=new String[6];
            int angka = 1;
            while(kon.rs.next()){
                data[0]=String.valueOf(angka);
                data[1]=kon.rs.getString(1);
                data[2]=kon.rs.getString(2);
                data[3]=kon.rs.getString(4);
                data[4]="update";
                data[5]="delete";
                tabel.addRow(data);
                angka++;
            }
            kon.stop();
        }
        catch(Exception e){};
        return tabel;
    }
    
}
