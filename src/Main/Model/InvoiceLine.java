package Main.Model;

public class InvoiceLine {
    private final String itemName;
    private final double itemPrice;
    private final int itemCount;
    private final InvoiceHeader invoiceHeader;

    public InvoiceLine(String itemName, double itemPrice, int itemCount, InvoiceHeader invoiceHeader) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCount = itemCount;
        this.invoiceHeader = invoiceHeader;
    }

    public String getItemName() { return itemName; }
    public double getItemPrice() { return itemPrice; }
    public int getItemCount() { return itemCount; }
    public InvoiceHeader getInvoiceHeader() { return invoiceHeader; }
    public double getLineTotal() { return itemPrice * itemCount; }
}