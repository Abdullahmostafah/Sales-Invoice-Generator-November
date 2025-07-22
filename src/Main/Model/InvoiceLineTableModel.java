package Main.Model;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class InvoiceLineTableModel extends AbstractTableModel {
    private final List<InvoiceLine> lines;
    private final String[] columns = {"Item", "Price", "Count", "Total"};

    public InvoiceLineTableModel(List<InvoiceLine> lines) {
        this.lines = lines;
    }

    @Override
    public int getRowCount() {
        return lines.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceLine line = lines.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> line.getItemName();
            case 1 -> line.getItemPrice();
            case 2 -> line.getItemCount();
            case 3 -> line.getLineTotal();
            default -> "";
        };
    }
}