package Garaman.model;

import java.sql.Date;
import java.util.List;

public class SparePartReceipt {
    private int id;
    private Date receiptDate;
    private double totalAmount;
    private Supplier supplier;
    private Staff warehouseStaff;
    private List<SparePartReceiptDetail> sparePartReceiptDetails;
    public SparePartReceipt() {}

    public SparePartReceipt(int id, Date receiptDate, double totalAmount, Supplier supplier, Staff warehouseStaff, List<SparePartReceiptDetail> sparePartReceiptDetails) {
        this.id = id;
        this.receiptDate = receiptDate;
        this.totalAmount = totalAmount;
        this.supplier = supplier;
        this.warehouseStaff = warehouseStaff;
        this.sparePartReceiptDetails = sparePartReceiptDetails;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getReceiptDate() { return receiptDate; }
    public void setReceiptDate(Date receiptDate) { this.receiptDate = receiptDate; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public Supplier getSupplier() { return supplier; }
    public void setSupplier(Supplier supplier) { this.supplier = supplier; }

    public Staff getWarehouseStaff() { return warehouseStaff; }
    public void setWarehouseStaff(Staff warehouseStaff) {this.warehouseStaff = warehouseStaff; }

    public List<SparePartReceiptDetail> getSparePartReceiptDetails() {
        return sparePartReceiptDetails;
    }

    public void setSparePartReceiptDetails(List<SparePartReceiptDetail> sparePartReceiptDetails) {
        this.sparePartReceiptDetails = sparePartReceiptDetails;
    }
}
