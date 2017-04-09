/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piesusu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class Koneksi {
    public Connection conn;
    public Statement st;
    public ResultSet rs;
    String id, pass, driver, url;
    public Koneksi(){
        id = "root";
        pass = "";
        driver = "org.gjt.mm.mysql.Driver";
        url = "jdbc:mysql://localhost/pie_susu";
    }
    public void start(){
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url,id,pass);
            st = conn.createStatement();
        }catch(ClassNotFoundException | SQLException e){ System.out.println(e); }
    }
    public void stop(){
        try {
            st.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
