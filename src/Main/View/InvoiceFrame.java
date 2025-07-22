package Main.View;

import Main.Controller.Controller;
import Main.Model.InvoiceHeader;
import Main.Model.InvoiceHeaderTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceFrame extends JFrame {
    private final JTable invoicesTable;
    private final JTable linesTable = new JTable();
    private final JLabel invoiceNumberLabel = new JLabel();
    private final JLabel invoiceDateLabel = new JLabel();
    private final JLabel customerNameLabel = new JLabel();
    private final JLabel invoiceTotalLabel = new JLabel();

    private final JButton createInvoiceButton = new JButton("Add Invoice");
    private final JButton deleteInvoiceButton = new JButton("Delete Invoice");
    private final JButton createLineButton = new JButton("Add Item");
    private final JButton deleteLineButton = new JButton("Delete Item");

    private final Controller controller;
    private final List<InvoiceHeader> invoiceList = new ArrayList<>();
    private final InvoiceHeaderTableModel invoiceTableModel;

    public InvoiceFrame() {
        super("Sales Invoice Generator");
        controller = new Controller(this);
        invoiceTableModel = new InvoiceHeaderTableModel(invoiceList);
        invoicesTable = new JTable(invoiceTableModel);
        invoicesTable.getSelectionModel().addListSelectionListener(controller);
        setupUI();
    }

    private void setupUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Left panel - Invoices list
        JPanel left = new JPanel(new BorderLayout());
        left.add(new JScrollPane(invoicesTable), BorderLayout.CENTER);
        deleteInvoiceButton.setEnabled(false);
        createInvoiceButton.addActionListener(controller);
        deleteInvoiceButton.addActionListener(controller);
        JPanel leftButtons = new JPanel();
        leftButtons.add(createInvoiceButton);
        leftButtons.add(deleteInvoiceButton);
        left.add(leftButtons, BorderLayout.SOUTH);
        add(left, BorderLayout.WEST);

        // Right panel - Invoice details
        JPanel right = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.WEST;

        addLabelRight(right, gbc, 0, "No.:", invoiceNumberLabel);
        addLabelRight(right, gbc, 1, "Date:", invoiceDateLabel);
        addLabelRight(right, gbc, 2, "Customer:", customerNameLabel);
        addLabelRight(right, gbc, 3, "Total:", invoiceTotalLabel);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        right.add(new JScrollPane(linesTable), gbc);

        createLineButton.addActionListener(controller);
        deleteLineButton.addActionListener(controller);
        deleteLineButton.setEnabled(false);
        JPanel rightButtons = new JPanel();
        rightButtons.add(createLineButton);
        rightButtons.add(deleteLineButton);
        gbc.gridy = 5;
        right.add(rightButtons, gbc);

        add(right, BorderLayout.CENTER);

        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem load = new JMenuItem("Load File");
        JMenuItem save = new JMenuItem("Save File");
        load.setActionCommand("Load File");
        save.setActionCommand("Save File");
        load.addActionListener(controller);
        save.addActionListener(controller);
        file.add(load);
        file.add(save);
        menuBar.add(file);
        setJMenuBar(menuBar);

        pack();
        setLocationRelativeTo(null);
    }

    private void addLabelRight(JPanel panel, GridBagConstraints gbc, int row, String labelText, JLabel valueLabel) {
        gbc.gridy = row;
        gbc.gridx = 0;
        panel.add(new JLabel(labelText), gbc);
        gbc.gridx = 1;
        panel.add(valueLabel, gbc);
    }

    // Getters
    public Controller getController() { return controller; }
    public JTable getInvoicesTable() { return invoicesTable; }
    public JTable getLinesTable() { return linesTable; }
    public JLabel getInvoiceNumberLabel() { return invoiceNumberLabel; }
    public JLabel getInvoiceDateLabel() { return invoiceDateLabel; }
    public JLabel getCustomerNameLabel() { return customerNameLabel; }
    public JLabel getInvoiceTotalLabel() { return invoiceTotalLabel; }
    public JButton getDeleteInvoiceButton() { return deleteInvoiceButton; }
    public JButton getDeleteLineButton() { return deleteLineButton; }
    public List<InvoiceHeader> getInvoices() { return invoiceList; }
    public InvoiceHeaderTableModel getInvoiceTableModel() { return invoiceTableModel; }

    public void setInvoices(List<InvoiceHeader> newList) {
        invoiceList.clear();
        invoiceList.addAll(newList);
        invoiceTableModel.fireTableDataChanged();
    }

    public int getNextInvoiceNumber() {
        return invoiceList.stream().mapToInt(InvoiceHeader::getInvoiceNum).max().orElse(0) + 1;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}
        SwingUtilities.invokeLater(() -> new InvoiceFrame().setVisible(true));
    }
}