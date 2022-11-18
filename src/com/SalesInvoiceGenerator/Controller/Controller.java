package com.SalesInvoiceGenerator.Controller;

import com.SalesInvoiceGenerator.Model.Invoice;
import com.SalesInvoiceGenerator.Model.InvoicesTable;
import com.SalesInvoiceGenerator.Model.Line;
import com.SalesInvoiceGenerator.Model.LinesTable;
import com.SalesInvoiceGenerator.View.InvoiceDialog;
import com.SalesInvoiceGenerator.View.InvoiceFrame;
import com.SalesInvoiceGenerator.View.LineDialog;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Controller implements ActionListener, ListSelectionListener {
    private InvoiceFrame fR;
    private InvoiceDialog iD;
    private LineDialog lD;
    public Controller(InvoiceFrame fR) {
        this.fR = fR;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        System.out.println("Action: " + actionCommand);
        switch (actionCommand) {
            case "Load File":
                loadFile();
                break;
            case "Save File":
                saveFile();
                break;
            case "Create New Invoice":
                createNewInvoice();
                break;
            case "Delete Invoice":
                deleteInvoice();
                break;
            case "Create New Item":
                createNewItem();
                break;
            case "Delete Item":
                deleteItem();
                break;
            case "createInvoiceCancel":
                createInvoiceCancel();
                break;
            case "createInvoiceOK":
                createInvoiceOK();
                break;
            case "createLineOK":
                createLineOK();
                break;
            case "createLineCancel":
                createLineCancel();
                break;
        }
    }
    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedIndex = fR.getiT().getSelectedRow();
        if (selectedIndex != -1) {
            System.out.println("You have selected row: " + selectedIndex);
            Invoice currentInvoice = fR.getInv().get(selectedIndex);
            fR.getiNL().setText("" + currentInvoice.getNumber());
            fR.getiDL().setText(currentInvoice.getDate());
            fR.getcNL().setText(currentInvoice.getCustomerName());
            fR.getInvoiceTotalLabel().setText("" + currentInvoice.getInvoiceTotal());
            LinesTable linesTable = new LinesTable(currentInvoice.getLines());
            fR.getlT().setModel(linesTable);
            linesTable.fireTableDataChanged();
        }
    }
    private void loadFile() {
        JFileChooser fc = new JFileChooser();
        try {
            int result = fc.showOpenDialog(fR);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = fc.getSelectedFile();
                Path headerPath = Paths.get(headerFile.getAbsolutePath());
                List<String> headerLines = Files.readAllLines(headerPath);
                System.out.println("Invoices have been read");
                // 1,22-11-2020,Ali
                // 2,13-10-2021,Saleh
                // 3,09-01-2019,Ibrahim
                ArrayList<Invoice> invoicesArray = new ArrayList<>();
                for (String headerLine : headerLines) {
                    try {
                        String[] headerParts = headerLine.split(",");
                        int invoiceNum = Integer.parseInt(headerParts[0]);
                        String invoiceDate = headerParts[1];
                        String customerName = headerParts[2];

                        Invoice invoice = new Invoice(invoiceNum, invoiceDate, customerName);
                        invoicesArray.add(invoice);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(fR, "Error in line format", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                System.out.println("Check point");
                result = fc.showOpenDialog(fR);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File lineFile = fc.getSelectedFile();
                    Path linePath = Paths.get(lineFile.getAbsolutePath());
                    List<String> lineLines = Files.readAllLines(linePath);
                    System.out.println("Lines have been read");
                    for (String lineLine : lineLines) {
                        try {
                            String lineParts[] = lineLine.split(",");
                            int invoiceNum = Integer.parseInt(lineParts[0]);
                            String itemName = lineParts[1];
                            double itemPrice = Double.parseDouble(lineParts[2]);
                            int count = Integer.parseInt(lineParts[3]);
                            Invoice inv = null;
                            for (Invoice invoice : invoicesArray) {
                                if (invoice.getNumber() == invoiceNum) {
                                    inv = invoice;
                                    break;
                                }
                            }

                            Line line = new Line(itemName, itemPrice, count, inv);
                            inv.getLines().add(line);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(fR, "Error in line format", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    System.out.println("Check point");
                }
                fR.setInv(invoicesArray);
                InvoicesTable invoicesTable = new InvoicesTable(invoicesArray);
                fR.setInvoicesTableModel(invoicesTable);
                fR.getiT().setModel(invoicesTable);
                fR.getInvoicesTableModel().fireTableDataChanged();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(fR, "Cannot read file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void saveFile() {
        ArrayList<Invoice> invoices = fR.getInv();
        String headers = "";
        String lines = "";
        for (Invoice invoice : invoices) {
            String invCSV = invoice.getAsCSV();
            headers += invCSV;
            headers += "\n";

            for (Line line : invoice.getLines()) {
                String lineCSV = line.getAsCSV();
                lines += lineCSV;
                lines += "\n";
            }
        }
        System.out.println("Check point");
        try {
            JFileChooser fc = new JFileChooser();
            int result = fc.showSaveDialog(fR);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = fc.getSelectedFile();
                FileWriter hfw = new FileWriter(headerFile);
                hfw.write(headers);
                hfw.flush();
                hfw.close();
                result = fc.showSaveDialog(fR);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File lineFile = fc.getSelectedFile();
                    FileWriter lfw = new FileWriter(lineFile);
                    lfw.write(lines);
                    lfw.flush();
                    lfw.close();
                }
            }
        } catch (Exception ex) {

        }
    }
    private void createNewInvoice() {
        iD = new InvoiceDialog(fR);
        iD.setVisible(true);
    }
    private void deleteInvoice() {
        int selectedRow = fR.getiT().getSelectedRow();
        if (selectedRow != -1) {
            fR.getInv().remove(selectedRow);
            fR.getInvoicesTableModel().fireTableDataChanged();
        }
    }
    private void createNewItem() {
        lD = new LineDialog(fR);
        lD.setVisible(true);
    }
    private void deleteItem() {
        int selectedRow = fR.getlT().getSelectedRow();

        if (selectedRow != -1) {
            LinesTable linesTable = (LinesTable) fR.getlT().getModel();
            linesTable.getlN().remove(selectedRow);
            linesTable.fireTableDataChanged();
            fR.getInvoicesTableModel().fireTableDataChanged();
        }
    }
    private void createInvoiceCancel() {
        iD.setVisible(false);
        iD.dispose();
        iD = null;
    }
    private void createInvoiceOK() {
        String date = iD.getiDF().getText();
        String customer = iD.getcNF().getText();
        int num = fR.getNextInvoiceNum();
        try {
            String[] dateParts = date.split("-");  // "22-05-2013" -> {"22", "05", "2013"}  xy-qw-20ij
            if (dateParts.length < 3) {
                JOptionPane.showMessageDialog(fR, "Wrong date format", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int day = Integer.parseInt(dateParts[0]);
                int month = Integer.parseInt(dateParts[1]);
                int year = Integer.parseInt(dateParts[2]);
                if (day > 31 || month > 12) {
                    JOptionPane.showMessageDialog(fR, "Wrong date format", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Invoice invoice = new Invoice(num, date, customer);
                    fR.getInv().add(invoice);
                    fR.getInvoicesTableModel().fireTableDataChanged();
                    iD.setVisible(false);
                    iD.dispose();
                    iD = null;
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(fR, "Wrong date format", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void createLineOK() {
        String item = lD.getiNF().getText();
        String countStr = lD.getiCF().getText();
        String priceStr = lD.getiPF().getText();
        int count = Integer.parseInt(countStr);
        double price = Double.parseDouble(priceStr);
        int selectedInvoice = fR.getiT().getSelectedRow();
        if (selectedInvoice != -1) {
            Invoice invoice = fR.getInv().get(selectedInvoice);
            Line line = new Line(item, price, count, invoice);
            invoice.getLines().add(line);
            LinesTable linesTable = (LinesTable) fR.getlT().getModel();
            //linesTable.getlN().add(line);
            linesTable.fireTableDataChanged();
            fR.getInvoicesTableModel().fireTableDataChanged();
        }
        lD.setVisible(false);
        lD.dispose();
        lD = null;
    }
    private void createLineCancel() {
        lD.setVisible(false);
        lD.dispose();
        lD = null;
    }
}
