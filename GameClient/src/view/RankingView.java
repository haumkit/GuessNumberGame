/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vuvan
 */
public class RankingView extends javax.swing.JFrame {

    /**
     * Creates new form RankingView
     */
    private Vector<Vector<Object>> data;
    
    public RankingView() {
        initComponents();
    }
    public void setListRanking(Vector<Vector<Object>> vdata, Vector<String> vheader) {
        this.data = vdata;
        tblRanking.setModel(new DefaultTableModel(vdata, vheader));
        tblRanking.revalidate(); // Đảm bảo bảng được vẽ lại
        tblRanking.repaint();    //
    }
    
    public void resetTblRanking () {
        DefaultTableModel dtm = (DefaultTableModel) tblRanking.getModel();
        dtm.setRowCount(0);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRanking = new javax.swing.JTable();
        cbKieuSx = new javax.swing.JComboBox<>();
        btnSapXep = new javax.swing.JButton();
        lbKieuSx = new javax.swing.JLabel();
        btnThoat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel1.setText("Ranking");

        tblRanking.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tblRanking.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Tên", "Thắng", "Hòa", "Thua", "Điểm số"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblRanking.setRowHeight(30);
        jScrollPane2.setViewportView(tblRanking);

        cbKieuSx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tên", "Trận thắng", "Trận thua", "Trận hòa", "Điểm số" }));
        cbKieuSx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKieuSxActionPerformed(evt);
            }
        });

        btnSapXep.setText("Sắp xếp");
        btnSapXep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSapXepActionPerformed(evt);
            }
        });

        lbKieuSx.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbKieuSx.setText("Chọn kiểu sắp xếp:");

        btnThoat.setText("THOÁT");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(171, 171, 171)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnThoat)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbKieuSx)
                                .addGap(18, 18, 18)
                                .addComponent(cbKieuSx, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(55, 55, 55)
                        .addComponent(btnSapXep)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbKieuSx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSapXep)
                    .addComponent(lbKieuSx))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(btnThoat)
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbKieuSxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKieuSxActionPerformed
        
    }//GEN-LAST:event_cbKieuSxActionPerformed

    private void btnSapXepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSapXepActionPerformed
        
        Object choose = cbKieuSx.getSelectedItem();
        String choStr = choose.toString();
        System.out.println(choStr);
        switch (choStr) {
            
            case "Tên":
                Collections.sort(data, new Comparator<Vector<Object>>() {
                @Override
                    public int compare(Vector<Object> row1, Vector<Object> row2) {
                        String a1 = (String) row1.get(0);
                        String a2 = (String) row2.get(0);
                        return a1.compareTo(a2);
                    }
                });
                break;
            case "Trận thắng":
                Collections.sort(data, new Comparator<Vector<Object>>() {
                @Override
                    public int compare(Vector<Object> row1, Vector<Object> row2) {
                        int a1 = (int) row1.get(1);
                        int a2 = (int) row2.get(1);
                        return Integer.compare(a2, a1);
                    }
                });
                break;
            case "Trận hòa":
                Collections.sort(data, new Comparator<Vector<Object>>() {
                @Override
                    public int compare(Vector<Object> row1, Vector<Object> row2) {
                        int a1 = (int) row1.get(2);
                        int a2 = (int) row2.get(2);
                        return Integer.compare(a2, a1);
                    }
                });
                break;
            case "Trận thua":
                Collections.sort(data, new Comparator<Vector<Object>>() {
                @Override
                    public int compare(Vector<Object> row1, Vector<Object> row2) {
                        int a1 = (int) row1.get(3);
                        int a2 = (int) row2.get(3);
                        return Integer.compare(a2, a1);
                    }
                });
                break;
            
            case "Điểm số":
                Collections.sort(data, new Comparator<Vector<Object>>() {
                @Override
                    public int compare(Vector<Object> row1, Vector<Object> row2) {
                        int  a1 = (int) row1.get(4);
                        int  a2 = (int) row2.get(4);
                        return Float.compare(a2, a1);
                    }
                });
                break;
        }
        tblRanking.revalidate();
        tblRanking.repaint();
    }//GEN-LAST:event_btnSapXepActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        dispose();
    }//GEN-LAST:event_btnThoatActionPerformed

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
            java.util.logging.Logger.getLogger(RankingView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RankingView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RankingView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RankingView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                RankingView rankingView = new RankingView();
                rankingView.setLocationRelativeTo(null);
                rankingView.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSapXep;
    private javax.swing.JButton btnThoat;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JComboBox<String> cbKieuSx;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbKieuSx;
    private javax.swing.JTable tblRanking;
    // End of variables declaration//GEN-END:variables
}
