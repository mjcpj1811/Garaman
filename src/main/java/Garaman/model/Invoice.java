package Garaman.model;

import java.sql.Date;
import java.util.List;

public class Invoice {
    private int id;
    private Date invoiceDate;
    private double totalAmount;
    private List<Request> requestList;
    private Customer customer;
    private SalesStaff salesStaff;

    public Invoice() {}

    public Invoice(int id, Date invoiceDate, double totalAmount, List<Request> requestList, Customer customer, SalesStaff salesStaff) {
        this.id = id;
        this.invoiceDate = invoiceDate;
        this.totalAmount = totalAmount;
        this.requestList = requestList;
        this.customer = customer;
        this.salesStaff = salesStaff;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getInvoiceDate() { return invoiceDate; }
    public void setInvoiceDate(Date invoiceDate) { this.invoiceDate = invoiceDate; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public List<Request> getRequestList() { return requestList; }
    public void setRequestList(List<Request> requestList) { this.requestList = requestList; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public SalesStaff getSalesStaff() { return salesStaff; }
    public void setSalesStaff(SalesStaff salesStaff) { this.salesStaff = salesStaff; }
}
