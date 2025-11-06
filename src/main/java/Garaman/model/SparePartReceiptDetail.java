package Garaman.model;

public class SparePartReceiptDetail {
    private int id;
    private int quantity;
    private double totalPrice;
    private SparePart sparePart;

    public SparePartReceiptDetail() {}

    public SparePartReceiptDetail(int id, int quantity, double totalPrice, SparePartReceipt sparePartReceipt, SparePart sparePart) {
        this.id = id;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.sparePart = sparePart;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public SparePart getSparePart() { return sparePart; }
    public void setSparePart(SparePart sparePart) {this.sparePart = sparePart; }

}
