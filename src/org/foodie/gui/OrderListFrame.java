/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foodie.gui;

import javax.swing.JFrame;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.foodie.dao.OrderDAO;
import org.foodie.pojo.OrderPojo;
import org.foodie.pojo.OwnerProfile;

/**
 *
 * @author AFROZ
 */
public class OrderListFrame extends javax.swing.JFrame {

    private JFrame showFrame;
    
    public OrderListFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        lblOwnerProfile.setText("@" + OwnerProfile.getOwnerName());
        loadOrderHistoryDetails(OwnerProfile.getCompanyId());
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tbOrderList = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblOwnerProfile = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        btnAddFood = new javax.swing.JButton();
        btnAddStaff = new javax.swing.JButton();
        btnViewFood = new javax.swing.JButton();
        btnViewStaff = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Order List Frame");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbOrderList.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tbOrderList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PRODUCT NAME", "PRODUCT PRICE", "CUSTOMER NAME", "STAFF NAME", "ADDRESS", "REVIEW"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbOrderList);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 870, 460));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 65)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(129, 120, 177));
        jLabel2.setText("Order List");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, -1, -1));

        jPanel3.setBackground(new java.awt.Color(129, 120, 177));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblOwnerProfile.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblOwnerProfile.setForeground(new java.awt.Color(51, 51, 51));
        jPanel3.add(lblOwnerProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 200, 34));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/foodie/icon/user.png"))); // NOI18N
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 197, 280, 10));

        btnAddFood.setBackground(new java.awt.Color(193, 224, 127));
        btnAddFood.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnAddFood.setText("Add Food");
        btnAddFood.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFoodActionPerformed(evt);
            }
        });
        jPanel3.add(btnAddFood, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 165, 40));

        btnAddStaff.setBackground(new java.awt.Color(193, 224, 127));
        btnAddStaff.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnAddStaff.setText("Add Staff");
        btnAddStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddStaffActionPerformed(evt);
            }
        });
        jPanel3.add(btnAddStaff, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 165, 40));

        btnViewFood.setBackground(new java.awt.Color(193, 224, 127));
        btnViewFood.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnViewFood.setText("View Food");
        btnViewFood.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewFoodActionPerformed(evt);
            }
        });
        jPanel3.add(btnViewFood, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, 165, 40));

        btnViewStaff.setBackground(new java.awt.Color(193, 224, 127));
        btnViewStaff.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnViewStaff.setText("View Staff");
        btnViewStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewStaffActionPerformed(evt);
            }
        });
        jPanel3.add(btnViewStaff, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, 165, 40));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 490, 100, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -6, 280, 560));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddFoodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFoodActionPerformed
        // TODO add your handling code here:
        showFrame = new AddFoodFrame();
        showFrame.setVisible(Boolean.TRUE);
        this.dispose();
    }//GEN-LAST:event_btnAddFoodActionPerformed

    private void btnAddStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddStaffActionPerformed
        // TODO add your handling code here:
        showFrame = new AddDeliveryStaffFrame();
        showFrame.setVisible(Boolean.TRUE);
        this.dispose();
    }//GEN-LAST:event_btnAddStaffActionPerformed

    private void btnViewFoodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewFoodActionPerformed

        // TODO add your handling code here:
        showFrame = new ViewFoodFrame();
        showFrame.setVisible(Boolean.TRUE);
        this.dispose();
    }//GEN-LAST:event_btnViewFoodActionPerformed

    private void btnViewStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewStaffActionPerformed
        // TODO add your handling code here:
        showFrame = new ViewDeliveryStaffFrame();
        showFrame.setVisible(Boolean.TRUE);
        this.dispose();
    }//GEN-LAST:event_btnViewStaffActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        showFrame=new SellerOptionFrame();
        showFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(OrderListFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderListFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderListFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderListFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrderListFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddFood;
    private javax.swing.JButton btnAddStaff;
    private javax.swing.JButton btnViewFood;
    private javax.swing.JButton btnViewStaff;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblOwnerProfile;
    private javax.swing.JTable tbOrderList;
    // End of variables declaration//GEN-END:variables

    private void loadOrderHistoryDetails(String companyId) {
        try {
            ArrayList<OrderPojo> orderList = OrderDAO.getOrderHistoryForSeller(companyId);
            Object[] rows = new Object[6]; //No. of Coloums / Object Array because different return type
            DefaultTableModel model = (DefaultTableModel) tbOrderList.getModel();
            for (OrderPojo order : orderList) {
                rows[0] = order.getProductName();
                rows[1] = order.getProductPrice();
                rows[2] = order.getCustomerName();
                rows[3] = order.getDeliveryStaffName();
                rows[4] = order.getCustomerAddress();
                rows[5] = order.getReview();
                model.addRow(rows);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(OrderListFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
