package com.SalesInvoiceGenerator.View;

import javax.swing.*;
import java.awt.*;

public class LineDialog extends JDialog{
    private JTextField iNF;
    private JTextField iCF;
    private JTextField iPF;
    private JLabel iNL;
    private JLabel iCL;
    private JLabel iPL;
    private JButton oB;
    private JButton cB;
    
    public LineDialog(InvoiceFrame fR) {
        iNF = new JTextField(20);
        iNL = new JLabel("Item Name");
        iCF = new JTextField(20);
        iCL = new JLabel("Item Count");
        iPF = new JTextField(20);
        iPL = new JLabel("Item Price");
        oB = new JButton("OK");
        cB = new JButton("Cancel");
        oB.setActionCommand("createLineOK");
        cB.setActionCommand("createLineCancel");
        oB.addActionListener(fR.getCtrl());
        cB.addActionListener(fR.getCtrl());
        setLayout(new GridLayout(4, 2));
        add(iNL);
        add(iNF);
        add(iCL);
        add(iCF);
        add(iPL);
        add(iPF);
        add(oB);
        add(cB);
        pack();
    }
    public JTextField getiNF() {
        return iNF;
    }
    public JTextField getiCF() {
        return iCF;
    }
    public JTextField getiPF() {
        return iPF;
    }
}
