package Main.View;

import javax.swing.*;
import java.awt.*;

public class LineDialog extends JDialog {
    private final JTextField itemNameField = new JTextField(20);
    private final JTextField itemCountField = new JTextField(20);
    private final JTextField itemPriceField = new JTextField(20);
    private final JButton okButton = new JButton("OK");
    private final JButton cancelButton = new JButton("Cancel");

    public LineDialog(InvoiceFrame frame) {
        super(frame, "Add Item", true);
        okButton.setActionCommand("createLineOK");
        cancelButton.setActionCommand("createLineCancel");
        okButton.addActionListener(frame.getController());
        cancelButton.addActionListener(frame.getController());

        setLayout(new GridLayout(4,2,5,5));
        add(new JLabel("Item Name:")); add(itemNameField);
        add(new JLabel("Count:")); add(itemCountField);
        add(new JLabel("Price:")); add(itemPriceField);
        add(okButton); add(cancelButton);
        pack();
        setLocationRelativeTo(frame);
    }

    public JTextField getItemNameField() { return itemNameField; }
    public JTextField getItemCountField() { return itemCountField; }
    public JTextField getItemPriceField() { return itemPriceField; }
}