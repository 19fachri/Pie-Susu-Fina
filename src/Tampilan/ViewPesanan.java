/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tampilan;

import javax.swing.JOptionPane;
import piesusu.Keuangan;
import piesusu.Pesanan;
import piesusu.Produk;

/**
 *
 * @author USER
 */
public class ViewPesanan extends javax.swing.JFrame {

    /**
     * Creates new form Pesanan
     */
    public ViewPesanan() {
        initComponents();
        awal();
    }
    
    public void awal(){
        Pesanan p = new Pesanan();
        tabelPesanan.setModel(p.getTablePesanan());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

        jButton1.setText("Tambah");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tabelPesanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelPesanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelPesananMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelPesanan);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pesanan.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(330, 330, 330))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ViewTambahPesanan vtp = new ViewTambahPesanan();
        vtp.awal();
        vtp.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tabelPesananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPesananMouseClicked
        // TODO add your handling code here:
        Pesanan p = new Pesanan();
        if(tabelPesanan.getSelectedColumn()==6){
            //hapus
            if(JOptionPane.showConfirmDialog(rootPane, "cencel ??")==0){
                if(p.delete(Integer.parseInt(tabelPesanan.getValueAt(tabelPesanan.getSelectedRow(), 1).toString()))){
                    JOptionPane.showMessageDialog(rootPane, "Pesanan Berhasil DIcancel");
                }else{
                    JOptionPane.showMessageDialog(rootPane, "Pesanan Gagal DIcancel");
                }
            }
        }else if(tabelPesanan.getSelectedColumn()==7){
            Keuangan k = new Keuangan(Integer.parseInt(tabelPesanan.getValueAt(tabelPesanan.getSelectedRow(), 1).toString()),Integer.parseInt(tabelPesanan.getValueAt(tabelPesanan.getSelectedRow(), 3).toString()) , "masuk");
            if(k.create()){
                if(p.delete(Integer.parseInt(tabelPesanan.getValueAt(tabelPesanan.getSelectedRow(), 1).toString()))){
                    JOptionPane.showMessageDialog(rootPane, "Pesanan Sudah Dikirim");
                }else{
                    JOptionPane.showMessageDialog(rootPane, "Pesanan Gagal Dikirim");
                }
            }else{
                JOptionPane.showMessageDialog(rootPane, "Pesanan Gagal Disimpan");
            }
        }else if(tabelPesanan.getSelectedColumn()<6){
            ViewDetailPesanan vdp = new ViewDetailPesanan();
            vdp.setVisible(true);
            vdp.awal(tabelPesanan.getValueAt(tabelPesanan.getSelectedRow(), 1).toString(),tabelPesanan.getValueAt(tabelPesanan.getSelectedRow(), 2).toString(),tabelPesanan.getValueAt(tabelPesanan.getSelectedRow(), 3).toString(),tabelPesanan.getValueAt(tabelPesanan.getSelectedRow(), 5).toString());
        }else{}
        tabelPesanan.setModel(p.getTablePesanan());
    }//GEN-LAST:event_tabelPesananMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewPesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewPesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewPesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewPesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewPesanan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static final javax.swing.JTable tabelPesanan = new javax.swing.JTable();
    // End of variables declaration//GEN-END:variables
}
