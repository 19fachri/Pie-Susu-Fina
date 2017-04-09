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
public class Pesanan {
    private int id;
    private String nama;
    private int total;
    private String status;
    private Date tanggal;
    private Koneksi kon;
    
    public Pesanan(){
        kon = new Koneksi();
    }
    
    public Pesanan(String n, int t, String s){
        kon = new Koneksi();
        nama = n;
        total = t;
        status = s;
    }
    
    public boolean create(List n, List hs, List j, List ht){
        boolean hasil = false;
        try {
            kon.start();
            String sql = "insert into pesanan (nama, biaya_total, status) value ('"+nama+"','"+total+"','"+status+"')";
            kon.st.executeUpdate(sql);
            String sql1 = "select id from pesanan order by id desc limit 1";
            kon.rs=kon.st.executeQuery(sql1);
            if(kon.rs.next()){
                id=kon.rs.getInt("id");
            }
            for(int i=0; i<n.getItemCount(); i++){ 
                String sql2 = "insert into pesanan_detail (id_pesanan, nama, harga_satuan, jumlah, harga_total) value ('"+id+"','"+n.getItem(i)+"','"+hs.getItem(i)+"','"+j.getItem(i)+"','"+ht.getItem(i)+"')";
                kon.st.executeUpdate(sql2);
            }
            hasil = true;
            kon.stop();
        } catch (SQLException ex) {
            Logger.getLogger(Belanjaan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }
    
    public boolean delete(int data){
        boolean hasil = false;
        try {
            kon.start();
            String sql="delete from pesanan where id='"+data+"'";
            kon.st.executeUpdate(sql);
            hasil=true;
            kon.stop();
        } catch (SQLException ex) {
            Logger.getLogger(Produk.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }
    
    public DefaultTableModel getTablePesanan(){
        String[]judul={"NO","ID","NAMA","TOTAL BIAYA","STATUS","TANGGAL","",""};
        DefaultTableModel tabel = new DefaultTableModel(null,judul);
        try{
            kon.start();
            String query = "select * from pesanan";
            kon.rs=kon.st.executeQuery(query);
            String data[]=new String[8];
            int angka = 1;
            while(kon.rs.next()){
                data[0]=String.valueOf(angka);
                data[1]=kon.rs.getString(1);
                data[2]=kon.rs.getString(2);
                data[3]=kon.rs.getString(3);
                data[4]=kon.rs.getString(4);
                data[5]=kon.rs.getString(5);
                data[6]="Cencel";
                data[7]="Kirim";
                tabel.addRow(data);
                angka++;
            }
            kon.stop();
        }
        catch(Exception e){};
        return tabel;
    }
    
    public DefaultTableModel getTablePesananDetail(int dt){
        String[]judul={"NO","NAMA BARANG","HARGA SATUAN","JUMLAH","HARGA TOTAL"};
        DefaultTableModel tabel = new DefaultTableModel(null,judul);
        try{
            kon.start();
            String query = "select * from pesanan_detail where id_pesanan='"+dt+"'";
            kon.rs=kon.st.executeQuery(query);
            String data[]=new String[5];
            int angka = 1;
            while(kon.rs.next()){
                data[0]=String.valueOf(angka);
                data[1]=kon.rs.getString("nama");
                data[2]=kon.rs.getString("harga_satuan");
                data[3]=kon.rs.getString("jumlah");
                data[4]=kon.rs.getString("harga_total");
                tabel.addRow(data);
                angka++;
            }
            kon.stop();
        }
        catch(Exception e){};
        return tabel;
    }
    
}
