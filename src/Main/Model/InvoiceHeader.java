package Main.Model;

import java.util.ArrayList;
import java.util.List;

public class InvoiceHeader {
    private final int invoiceNum;
    private final String invoiceDate;
    private final String customerName;
    private final List<InvoiceLine> lines;

    public InvoiceHeader(int invoiceNum, String invoiceDate, String customerName) {
        this.invoiceNum = invoiceNum;
        this.invoiceDate = invoiceDate;
        this.customerName = customerName;
        this.lines = new ArrayList<>();
    }

    public int getInvoiceNum() { return invoiceNum; }
    public String getInvoiceDate() { return invoiceDate; }
    public String getCustomerName() { return customerName; }
    public List<InvoiceLine> getLines() { return lines; }

    public double getInvoiceTotal() {
        return lines.stream().mapToDouble(InvoiceLine::getLineTotal).sum();
    }
}