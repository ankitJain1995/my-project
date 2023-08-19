/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foodie.gui;

import javax.swing.JFrame;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.foodie.dao.OrderDAO;
import org.foodie.pojo.OrderPojo;
import org.foodie.pojo.UserCredentials;

/**
 *
 * @author AFROZ
 */
public class ViewOrdersFrame extends javax.swing.JFrame {

    /**
     * Creates new form ViewOrdersFrame
     */
    private ArrayList<OrderPojo> orderList;
    private JFrame showFrame;

    public ViewOrdersFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        lblUserProfile.setText("@" + UserCredentials.getUserName());
        loadNewOrderDetails(UserCredentials.getUserId());
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
        jtOrderList = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblUserProfile = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        btnOrderHistory = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("View Orders Frame");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtOrderList.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jtOrderList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PRODUCT NAME", "PRODUCT PRICE", "CUSTOMER NAME", "CUSTOMER PHONE NO", "ADDRESS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtOrderList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtOrderListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtOrderList);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-5, 105, 870, 450));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 65)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 102, 255));
        jLabel2.setText("View Orders");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(284, 0, 870, 550));

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUserProfile.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblUserProfile.setForeground(new java.awt.Color(51, 51, 51));
        jPanel2.add(lblUserProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 200, 34));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/foodie/icon/user.png"))); // NOI18N
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 280, 7));

        btnOrderHistory.setBackground(new java.awt.Color(255, 176, 9));
        btnOrderHistory.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnOrderHistory.setText("Order History");
        btnOrderHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderHistoryActionPerformed(evt);
            }
        });
        jPanel2.add(btnOrderHistory, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 220, 46));

        btnBack.setBackground(new java.awt.Color(255, 176, 9));
        btnBack.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        jPanel2.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, 180, 46));

        btnLogout.setBackground(new java.awt.Color(255, 176, 9));
        btnLogout.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        jPanel2.add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 460, 180, 46));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -6, 280, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtOrderListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtOrderListMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jtOrderList.getModel();
        System.out.println(model.getDataVector().get(jtOrderList.getSelectedRow()));
        System.out.println(jtOrderList.getSelectedRow());
        int index = jtOrderList.getSelectedRow();
        System.out.println("orderlist : \n" + orderList);
        System.out.println("order selected : \n" + orderList.get(index));
        int otp = orderList.get(index).getOtp();
        System.out.println("otp to confirm" + otp);
        String answer = JOptionPane.showInputDialog(this, "Please enter the otp to complete the order.", "Order Delivered", JOptionPane.INFORMATION_MESSAGE);
        System.out.println("answer is " + answer);
        if (answer == null) {
            return;
        }

        try {

            if (String.valueOf(otp).equals(answer)) {

                System.out.println("set order status as ordered.");
                boolean result = OrderDAO.confirmOrder(orderList.get(index).getOrderId());
                if (!result) {
                    JOptionPane.showMessageDialog(this, "Can't confirm the Order Now.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(this, "Order Delivered Successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                showFrame = new ViewOrdersFrame();
                showFrame.setVisible(true);
                this.dispose();
                return;
            }
            JOptionPane.showMessageDialog(this, "OTP is invalid.\nTry Again", "Error", JOptionPane.ERROR_MESSAGE);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jtOrderListMouseClicked

    private void btnOrderHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderHistoryActionPerformed
        // TODO add your handling code here:
        showFrame = new ViewOrderHistoryFrame();
        showFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnOrderHistoryActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        showFrame=new DeliveryStaffOptionFrame();
        showFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        showFrame=new DeliveryStaffLoginFrame();
        showFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

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
            java.util.logging.Logger.getLogger(ViewOrdersFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewOrdersFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewOrdersFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewOrdersFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewOrdersFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnOrderHistory;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jtOrderList;
    private javax.swing.JLabel lblUserProfile;
    // End of variables declaration//GEN-END:variables

    private void loadNewOrderDetails(String staffId) {
        try {
            orderList = OrderDAO.getNewOrderForDeliveryStaff(staffId);
            Object[] rows = new Object[6]; //No. of Coloums / Object Array because different return type
            DefaultTableModel model = (DefaultTableModel) jtOrderList.getModel();
            for (OrderPojo order : orderList) {
                rows[0] = order.getProductName();
                rows[1] = order.getProductPrice();
                rows[2] = order.getCustomerName();
                rows[3] = order.getCustomerPhoneNo();
                rows[4] = order.getCustomerAddress();
                model.addRow(rows);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
