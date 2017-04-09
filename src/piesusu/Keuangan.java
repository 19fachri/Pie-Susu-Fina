/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piesusu;

import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class Keuangan {
    private int id;
    private int kode;
    private Date tanggal;
    private int jumlah;
    private String keterangan;
    private int total;
    private Koneksi kon;
    
    public Keuangan(){
        kon = new Koneksi();
    }
    
    public Keuangan(int ko, int j, String k){
        kon = new Koneksi();
        kode = ko;
        jumlah = j;
        keterangan = k;
    }
    
    public boolean create(){
        boolean hasil = false;
        try {
            kon.start();
            String query = "select total from keuangan order by id desc limit 1";
            kon.rs=kon.st.executeQuery(query);
            if(kon.rs.next()){
                total=kon.rs.getInt("total");
            }
            if(keterangan.equals("masuk")){
                total=total+jumlah;
            }else{
                total=total-jumlah;
            }
            String sql = "insert into keuangan (kode, jumlah, keterangan, total) value ('"+kode+"','"+jumlah+"','"+keterangan+"','"+total+"')";
            kon.st.executeUpdate(sql);
            hasil = true;
            kon.stop();
        } catch (SQLException ex) {
            Logger.getLogger(Keuangan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }
    
    public DefaultTableModel getTableKeuangan(){
        String[]judul={"NO","KODE","TANGGAL","JUMLAH","KETERANGAN","TOTAL",""};
        DefaultTableModel tabel = new DefaultTableModel(null,judul);
        try{
            kon.start();
            String query = "select * from keuangan";
            kon.rs=kon.st.executeQuery(query);
            String data[]=new String[7];
            int angka = 1;
            while(kon.rs.next()){
                data[0]=String.valueOf(angka);
                data[1]=kon.rs.getString(2);
                data[2]=kon.rs.getString(3);
                data[3]=kon.rs.getString(4);
                data[4]=kon.rs.getString(5);
                data[5]=kon.rs.getString(6);
                data[6]="Detail";
                tabel.addRow(data);
                angka++;
            }
            kon.stop();
        }
        catch(Exception e){};
        return tabel;
    }
    
    public DefaultTableModel getTableKeuanganDetail(int dt){
        String[]judul={"NO","NAMA BARANG","HARGA SATUAN","JUMLAH","HARGA TOTAL",""};
        DefaultTableModel tabel = new DefaultTableModel(null,judul);
        try{
            kon.start();
            String query = "select * from keuangan_detail where id_keuangan='"+dt+"'";
            kon.rs=kon.st.executeQuery(query);
            String data[]=new String[5];
            int angka = 1;
            while(kon.rs.next()){
                data[0]=String.valueOf(angka);
                data[1]=kon.rs.getString("nama");
                data[2]=kon.rs.getString("harga_satuan");
                data[3]=kon.rs.getString("jumlah");
                data[4]=kon.rs.getString("total");
                tabel.addRow(data);
                angka++;
            }
            kon.stop();
        }
        catch(Exception e){};
        return tabel;
    }
    
    public DefaultTableModel filterTableKeuangan(Date awal, Date akhir){
        String[]judul={"NO","NO DB","TANGGAL","JUMLAH","KETERANGAN","TOTAL",""};
        DefaultTableModel tabel = new DefaultTableModel(null,judul);
        try{
            kon.start();
            String query = "SELECT * FROM keuangan WHERE tanggal > '"+awal+"' and tanggal < '"+akhir+"'";
            kon.rs=kon.st.executeQuery(query);
            String data[]=new String[7];
            int angka = 1;
            while(kon.rs.next()){
                data[0]=String.valueOf(angka);
                data[1]=kon.rs.getString(1);
                data[2]=kon.rs.getString(2);
                data[3]=kon.rs.getString(3);
                data[4]=kon.rs.getString(4);
                data[5]=kon.rs.getString(5);
                data[6]="Detail";
                tabel.addRow(data);
                angka++;
            }
            kon.stop();
        }
        catch(Exception e){};
        return tabel;
    }
}
