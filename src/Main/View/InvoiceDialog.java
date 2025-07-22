package Main.View;

import javax.swing.*;
import java.awt.*;

public class InvoiceDialog extends JDialog {
    private final JTextField customerNameField = new JTextField(20);
    private final JTextField invoiceDateField = new JTextField(20);
    private final JButton okButton = new JButton("OK");
    private final JButton cancelButton = new JButton("Cancel");

    public InvoiceDialog(InvoiceFrame frame) {
        super(frame, "Create Invoice", true);
        okButton.setActionCommand("createInvoiceOK");
        cancelButton.setActionCommand("createInvoiceCancel");
        okButton.addActionListener(frame.getController());
        cancelButton.addActionListener(frame.getController());

        setLayout(new GridLayout(3, 2, 5, 5));
        add(new JLabel("Customer Name:")); add(customerNameField);
        add(new JLabel("Date (DD-MM-YYYY):")); add(invoiceDateField);
        add(okButton); add(cancelButton);
        pack();
        setLocationRelativeTo(frame);
    }

    public JTextField getCustomerNameField() { return customerNameField; }
    public JTextField getInvoiceDateField() { return invoiceDateField; }
}