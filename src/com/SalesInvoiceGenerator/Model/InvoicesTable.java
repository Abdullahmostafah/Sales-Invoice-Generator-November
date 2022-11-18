package com.SalesInvoiceGenerator.Model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class InvoicesTable extends AbstractTableModel {
    private ArrayList<Invoice> inv;
    private String[] cL = {"No.", "Date", "Customer", "Total"};
    public InvoicesTable(ArrayList<Invoice> inv) {
        this.inv = inv;
    }
    @Override
    public int getRowCount() {
        return inv.size();
    }
    @Override
    public int getColumnCount() {
        return cL.length;
    }
    @Override
    public String getColumnName(int column) {
        return cL[column];
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Invoice invoice = inv.get(rowIndex);
        switch (columnIndex) {
            case 0: return invoice.getNumber();
            case 1: return invoice.getDate();
            case 2: return invoice.getCustomerName();
            case 3: return invoice.getInvoiceTotal();
            default : return "";
        }
    }
}
