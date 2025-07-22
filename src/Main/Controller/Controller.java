package Main.Controller;

import Main.Model.InvoiceHeader;
import Main.Model.InvoiceLine;
import Main.Model.InvoiceHeaderTableModel;
import Main.Model.InvoiceLineTableModel;
import Main.View.InvoiceDialog;
import Main.View.InvoiceFrame;
import Main.View.LineDialog;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Controller implements ActionListener, ListSelectionListener {
    private final InvoiceFrame frame;
    private InvoiceDialog invoiceDialog;
    private LineDialog lineDialog;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public Controller(InvoiceFrame frame) {
        this.frame = frame;
        dateFormat.setLenient(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "Load File" -> loadFile();
            case "Save File" -> saveFile();
            case "Add Invoice" -> openInvoiceDialog();
            case "Delete Invoice" -> deleteInvoice();
            case "Add Item" -> openLineDialog();
            case "Delete Item" -> deleteLine();
            case "createInvoiceOK" -> createInvoiceOK();
            case "createInvoiceCancel" -> closeInvoiceDialog();
            case "createLineOK" -> createLineOK();
            case "createLineCancel" -> closeLineDialog();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int idx = frame.getInvoicesTable().getSelectedRow();
        frame.getDeleteInvoiceButton().setEnabled(idx != -1);
        frame.getDeleteLineButton().setEnabled(false);
        if (idx == -1) {
            clearDetails();
            return;
        }

        InvoiceHeader inv = frame.getInvoices().get(idx);
        showInvoiceDetails(inv);
    }

    private void showInvoiceDetails(InvoiceHeader inv) {
        frame.getInvoiceNumberLabel().setText(String.valueOf(inv.getInvoiceNum()));
        frame.getInvoiceDateLabel().setText(inv.getInvoiceDate());
        frame.getCustomerNameLabel().setText(inv.getCustomerName());
        frame.getInvoiceTotalLabel().setText(String.valueOf(inv.getInvoiceTotal()));

        InvoiceLineTableModel linesModel = new InvoiceLineTableModel(inv.getLines());
        frame.getLinesTable().setModel(linesModel);
        linesModel.fireTableDataChanged();

        frame.getLinesTable().getSelectionModel().addListSelectionListener(e -> {
            frame.getDeleteLineButton().setEnabled(frame.getLinesTable().getSelectedRow() != -1);
        });
    }

    private void clearDetails() {
        frame.getInvoiceNumberLabel().setText("");
        frame.getInvoiceDateLabel().setText("");
        frame.getCustomerNameLabel().setText("");
        frame.getInvoiceTotalLabel().setText("");
        frame.getLinesTable().setModel(new InvoiceLineTableModel(new ArrayList<>()));
    }

    private void loadFile() {
        JFileChooser fc = new JFileChooser();
        try {
            if (fc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                var headerList = loadHeaders(fc.getSelectedFile());
                if (fc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                    loadLines(fc.getSelectedFile(), headerList);
                    frame.setInvoices(headerList);
                    frame.getInvoiceTableModel().fireTableDataChanged();
                } else {
                    JOptionPane.showMessageDialog(frame, "Lines file must be selected.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "IO error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private List<InvoiceHeader> loadHeaders(File headerFile) throws IOException {
        List<InvoiceHeader> list = new ArrayList<>();
        for (String row : Files.readAllLines(headerFile.toPath())) {
            var parts = row.trim().split(",");
            if (parts.length == 3) {
                try {
                    int num = Integer.parseInt(parts[0].trim());
                    String date = parts[1].trim();
                    String cust = parts[2].trim();
                    list.add(new InvoiceHeader(num, date, cust));
                } catch (NumberFormatException ignored) {}
            }
        }
        return list;
    }

    private void loadLines(File lineFile, List<InvoiceHeader> headList) throws IOException {
        for (String row : Files.readAllLines(lineFile.toPath())) {
            var parts = row.trim().split(",");
            if (parts.length == 4) {
                try {
                    int invNum = Integer.parseInt(parts[0].trim());
                    String item = parts[1].trim();
                    double price = Double.parseDouble(parts[2].trim());
                    int count = Integer.parseInt(parts[3].trim());

                    headList.stream()
                            .filter(h -> h.getInvoiceNum() == invNum)
                            .findFirst()
                            .ifPresent(h -> h.getLines().add(new InvoiceLine(item, price, count, h)));
                } catch (NumberFormatException ignored) {}
            }
        }
    }

    private void saveFile() {
        JFileChooser fc = new JFileChooser();
        try {
            if (fc.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
                var headerFile = fc.getSelectedFile();
                try (FileWriter w = new FileWriter(headerFile)) {
                    for (var inv : frame.getInvoices()) {
                        w.write(inv.getInvoiceNum() + "," + inv.getInvoiceDate() + "," + inv.getCustomerName() + "\n");
                    }
                }
                if (fc.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
                    var lineFile = fc.getSelectedFile();
                    try (FileWriter w = new FileWriter(lineFile)) {
                        for (var inv : frame.getInvoices()) {
                            for (var ln : inv.getLines()) {
                                w.write(ln.getInvoiceHeader().getInvoiceNum() + "," +
                                        ln.getItemName() + "," +
                                        ln.getItemPrice() + "," +
                                        ln.getItemCount() + "\n");
                            }
                        }
                    }
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error saving: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openInvoiceDialog() {
        invoiceDialog = new InvoiceDialog(frame);
        invoiceDialog.setVisible(true);
    }

    private void deleteInvoice() {
        int idx = frame.getInvoicesTable().getSelectedRow();
        if (idx != -1) {
            frame.getInvoices().remove(idx);
            frame.getInvoiceTableModel().fireTableDataChanged();
            clearDetails();
        }
    }

    private void openLineDialog() {
        if (frame.getInvoicesTable().getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(frame, "Please select an invoice first.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        lineDialog = new LineDialog(frame);
        lineDialog.setVisible(true);
    }

    private void deleteLine() {
        int invIdx = frame.getInvoicesTable().getSelectedRow();
        int lineIdx = frame.getLinesTable().getSelectedRow();

        if (invIdx != -1 && lineIdx != -1) {
            InvoiceHeader inv = frame.getInvoices().get(invIdx);
            inv.getLines().remove(lineIdx);

            InvoiceLineTableModel lt = (InvoiceLineTableModel) frame.getLinesTable().getModel();
            lt.fireTableDataChanged();
            frame.getInvoiceTableModel().fireTableDataChanged();

            // Update the total
            frame.getInvoiceTotalLabel().setText(String.valueOf(inv.getInvoiceTotal()));
        }
    }

    private void createInvoiceOK() {
        var dateStr = invoiceDialog.getInvoiceDateField().getText().trim();
        var cust = invoiceDialog.getCustomerNameField().getText().trim();
        try {
            if (cust.isEmpty()) throw new IllegalArgumentException("Customer name is required");
            dateFormat.parse(dateStr);

            var inv = new InvoiceHeader(frame.getNextInvoiceNumber(), dateStr, cust);
            frame.getInvoices().add(inv);
            frame.getInvoiceTableModel().fireTableDataChanged();
            closeInvoiceDialog();
        } catch (ParseException pe) {
            JOptionPane.showMessageDialog(frame, "Use DD-MM-YYYY format", "Invalid Date", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ia) {
            JOptionPane.showMessageDialog(frame, ia.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createLineOK() {
        var item = lineDialog.getItemNameField().getText().trim();
        var cnt = lineDialog.getItemCountField().getText().trim();
        var prc = lineDialog.getItemPriceField().getText().trim();

        try {
            if (item.isEmpty()) throw new IllegalArgumentException("Item name is required");
            int count = Integer.parseInt(cnt);
            double price = Double.parseDouble(prc);

            if (count <= 0 || price <= 0) {
                throw new IllegalArgumentException("Values must be positive");
            }

            int invIdx = frame.getInvoicesTable().getSelectedRow();
            InvoiceHeader inv = frame.getInvoices().get(invIdx);
            inv.getLines().add(new InvoiceLine(item, price, count, inv));

            ((InvoiceLineTableModel) frame.getLinesTable().getModel()).fireTableDataChanged();
            frame.getInvoiceTableModel().fireTableDataChanged();

            // Update the total
            frame.getInvoiceTotalLabel().setText(String.valueOf(inv.getInvoiceTotal()));

            closeLineDialog();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid number format", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void closeInvoiceDialog() {
        if (invoiceDialog != null) {
            invoiceDialog.dispose();
            invoiceDialog = null;
        }
    }

    private void closeLineDialog() {
        if (lineDialog != null) {
            lineDialog.dispose();
            lineDialog = null;
        }
    }
}