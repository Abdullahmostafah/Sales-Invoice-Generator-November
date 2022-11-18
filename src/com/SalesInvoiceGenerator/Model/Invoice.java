package com.SalesInvoiceGenerator.Model;

import java.util.ArrayList;

public class Invoice {
    private int number;
    private String date;
    private String customerName;
    private ArrayList<Line> lines;
    
    public Invoice() {
    }
    public Invoice(int number, String date, String customerName) {
        this.number = number;
        this.date = date;
        this.customerName = customerName;
    }
    public double getInvoiceTotal() {
        double total = 0.0;
        for (Line line : getLines()) {
            total += line.getLineTotal();
        }
        return total;
    }
    public ArrayList<Line> getLines() {
        if (lines == null) {
            lines = new ArrayList<>();
        }
        return lines;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    @Override
    public String toString() {
        return "Invoice{" + "number=" + number + ", date=" + date + ", customerName=" + customerName + '}';
    }
    public String getAsCSV() {
        return number + "," + date + "," + customerName;
    }
}
