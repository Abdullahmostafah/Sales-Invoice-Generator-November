package Main.Model;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class InvoiceHeaderTableModel extends AbstractTableModel {
    private final List<InvoiceHeader> invoices;
    private final String[] columns = {"No.", "Date", "Customer", "Total"};

    public InvoiceHeaderTableModel(List<InvoiceHeader> invoices) {
        this.invoices = invoices;
    }

    @Override
    public int getRowCount() {
        return invoices.size();
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
        InvoiceHeader inv = invoices.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> inv.getInvoiceNum();
            case 1 -> inv.getInvoiceDate();
            case 2 -> inv.getCustomerName();
            case 3 -> inv.getInvoiceTotal();
            default -> "";
        };
    }
}