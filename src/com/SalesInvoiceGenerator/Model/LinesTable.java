package com.SalesInvoiceGenerator.Model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
public class LinesTable extends AbstractTableModel {
    private ArrayList<Line> lN;
    private String[] cL = {"No.", "Item Name", "Item Price", "Count", "Item Total"};
    public LinesTable(ArrayList<Line> lN) {
        this.lN = lN;
    }
    public ArrayList<Line> getlN() {
        return lN;
    }
    @Override
    public int getRowCount() {
        return lN.size();
    }
    @Override
    public int getColumnCount() {
        return cL.length;
    }
    @Override
    public String getColumnName(int x) {
        return cL[x];
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Line line = lN.get(rowIndex);
        switch(columnIndex) {
            case 0: return line.getInvoice().getNumber();
            case 1: return line.getItem();
            case 2: return line.getPrice();
            case 3: return line.getCount();
            case 4: return line.getLineTotal();
            default : return "";
        }
    }
}
