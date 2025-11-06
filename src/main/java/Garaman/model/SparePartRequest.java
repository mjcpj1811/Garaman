package Garaman.model;

public class SparePartRequest {
    private int id;
    private int quantity;
    private SparePart sparePart;

    public SparePartRequest() {}

    public SparePartRequest(int id, int quantity, SparePart sparePart) {
        this.id = id;
        this.quantity = quantity;
        this.sparePart = sparePart;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public SparePart getSparePart() { return sparePart; }
    public void setSparePart(SparePart sparePart) { this.sparePart = sparePart; }
}
