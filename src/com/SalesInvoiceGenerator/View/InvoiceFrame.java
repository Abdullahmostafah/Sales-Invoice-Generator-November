package com.SalesInvoiceGenerator.View;

import com.SalesInvoiceGenerator.Controller.Controller;
import com.SalesInvoiceGenerator.Model.Invoice;
import com.SalesInvoiceGenerator.Model.InvoicesTable;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InvoiceFrame extends javax.swing.JFrame {
    public InvoiceFrame() {
        this.getContentPane().setBackground(Color.white);
        initComponents();
    }
    @SuppressWarnings("unchecked")

    private void initComponents() {
        jSP1 = new javax.swing.JScrollPane();
        iT = new JTable();
        iT.getSelectionModel().addListSelectionListener(ctrl);
        iT.setModel(getInvoicesTableModel());
        cIB = new javax.swing.JButton();
        cIB.addActionListener(ctrl);
        dIB = new javax.swing.JButton();
        dIB.addActionListener(ctrl);
        jL1 = new JLabel();
        jL2 = new JLabel();
        jL3 = new JLabel();
        jL4 = new JLabel();
        cNL = new JLabel();
        iDL = new JLabel();
        iNL = new JLabel();
        invoiceTotalLabel = new JLabel();
        jSP2 = new javax.swing.JScrollPane();
        lT = new JTable();
        cLB = new javax.swing.JButton();
        cLB.addActionListener(ctrl);
        dLB = new javax.swing.JButton();
        dLB.addActionListener(ctrl);
        jMB1 = new javax.swing.JMenuBar();
        jM1 = new javax.swing.JMenu();
        lMFI = new javax.swing.JMenuItem();
        lMFI.addActionListener(ctrl);
        sFMI = new javax.swing.JMenuItem();
        sFMI.addActionListener(ctrl);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new Color(204, 204, 204));
        jSP1.setBackground(this.getContentPane().getBackground());
        iT.setBackground(this.getContentPane().getBackground());
        iT.setModel(new javax.swing.table.DefaultTableModel(new Object [][] {}, new String [] {}));
        jSP1.setViewportView(iT);
        cIB.setBackground(this.getContentPane().getBackground());
        cIB.setText("Create New Invoice");
        dIB.setBackground(this.getContentPane().getBackground());
        dIB.setText("Delete Invoice");
        dIB.setOpaque(false);
        jL1.setBackground(this.getContentPane().getBackground());
        jL1.setText("Inoice Number");
        jL1.setOpaque(true);
        jL2.setBackground(this.getContentPane().getBackground());
        jL2.setText("Invoice Date");
        jL2.setOpaque(true);
        jL3.setBackground(this.getContentPane().getBackground());
        jL3.setText("Customer Name");
        jL3.setOpaque(true);
        jL4.setBackground(this.getContentPane().getBackground());
        jL4.setText("Invoice Total");
        jL4.setOpaque(true);
        cNL.setBackground(this.getContentPane().getBackground());
        cNL.setOpaque(true);
        iDL.setBackground(this.getContentPane().getBackground());
        iDL.setOpaque(true);
        iNL.setBackground(this.getContentPane().getBackground());
        iNL.setOpaque(true);
        invoiceTotalLabel.setBackground(this.getContentPane().getBackground());
        invoiceTotalLabel.setOpaque(true);
        jSP2.setBackground(this.getContentPane().getBackground());
        lT.setBackground(this.getContentPane().getBackground());
        lT.setModel(new javax.swing.table.DefaultTableModel(new Object [][] {}, new String [] {}));
        jSP2.setViewportView(lT);
        cLB.setBackground(this.getContentPane().getBackground());
        cLB.setText("Create New Item");
        dLB.setBackground(this.getContentPane().getBackground());
        dLB.setText("Delete Item");
        jM1.setText("File");
        lMFI.setText("Load File");
        jM1.add(lMFI);
        sFMI.setText("Save File");
        jM1.add(sFMI);
        jMB1.add(jM1);
        setJMenuBar(jMB1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(cIB)
                        .addGap(131, 131, 131)
                        .addComponent(dIB)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jL4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(invoiceTotalLabel))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jL1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(iNL))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jL2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(iDL))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jL3)
                                    .addGap(18, 18, 18)
                                    .addComponent(cNL)))
                            .addComponent(jSP2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(cLB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dLB)
                        .addGap(133, 133, 133))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jL1)
                            .addComponent(iNL))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jL2)
                            .addComponent(iDL))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jL3)
                            .addComponent(cNL))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jL4)
                            .addComponent(invoiceTotalLabel))
                        .addGap(18, 18, 18)
                        .addComponent(jSP2, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSP1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dIB)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cIB)
                        .addComponent(cLB)
                        .addComponent(dLB)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(InvoiceFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InvoiceFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InvoiceFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InvoiceFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InvoiceFrame().setVisible(true);
            }
        });
    }
    private javax.swing.JButton cIB;
    private javax.swing.JButton cLB;
    private JLabel cNL;
    private javax.swing.JButton dIB;
    private javax.swing.JButton dLB;
    private JLabel iDL;
    private JLabel iNL;
    private JTable iT;
    private JLabel invoiceTotalLabel;
    private JLabel jL1;
    private JLabel jL2;
    private JLabel jL3;
    private JLabel jL4;
    private javax.swing.JMenu jM1;
    private javax.swing.JMenuBar jMB1;
    private javax.swing.JScrollPane jSP1;
    private javax.swing.JScrollPane jSP2;
    private JTable lT;
    private javax.swing.JMenuItem lMFI;
    private javax.swing.JMenuItem sFMI;
    // End of variables declaration//GEN-END:variables
    private ArrayList<Invoice> inv;
    private Controller ctrl = new Controller(this);
    private InvoicesTable invT;
    public ArrayList<Invoice> getInv() {
        if (inv == null) inv = new ArrayList<>();
        return inv;
    }
    public void setInv(ArrayList<Invoice> inv) {
        this.inv = inv;
    }
    public InvoicesTable getInvoicesTableModel() {
        if (invT == null) {
            invT = new InvoicesTable(getInv());
        }
        return invT;
    }
    public void setInvoicesTableModel(InvoicesTable invoicesTable) {
        this.invT = invoicesTable;
    }
    public JLabel getcNL() {
        return cNL;
    }
    public JLabel getiDL() {
        return iDL;
    }
    public JLabel getiNL() {
        return iNL;
    }
    public JTable getiT() {
        return iT;
    }
    public JLabel getInvoiceTotalLabel() {
        return invoiceTotalLabel;
    }
    public JTable getlT() {
        return lT;
    }
    public Controller getCtrl() {
        return ctrl;
    }
    public int getNextInvoiceNum() {
        int num = 0;
        for (Invoice invoice : getInv()) {
            if (invoice.getNumber() > num)
                num = invoice.getNumber();
        }
        return ++num;
    }
}
