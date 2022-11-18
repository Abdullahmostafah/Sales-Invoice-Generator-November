package com.SalesInvoiceGenerator.View;

import javax.swing.*;
import java.awt.*;
public class InvoiceDialog extends JDialog {
    private JTextField cNF;
    private JTextField iDF;
    private JLabel cNL;
    private JLabel iDL;
    private JButton oB;
    private JButton cB;

    public InvoiceDialog(InvoiceFrame frame) {
        cNL = new JLabel("Customer Name:");
        cNF = new JTextField(20);
        iDL = new JLabel("Invoice Date:");
        iDF = new JTextField(20);
        oB = new JButton("OK");
        cB = new JButton("Cancel");
        oB.setActionCommand("createInvoiceOK");
        cB.setActionCommand("createInvoiceCancel");
        oB.addActionListener(frame.getCtrl());
        cB.addActionListener(frame.getCtrl());
        setLayout(new GridLayout(3, 2));
        add(iDL);
        add(iDF);
        add(cNL);
        add(cNF);
        add(oB);
        add(cB);
        pack();
    }
    public JTextField getcNF() {
        return cNF;
    }
    public JTextField getiDF() {
        return iDF;
    }
}
