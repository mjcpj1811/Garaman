package Garaman.model;

public class SparePart {
    private int id;
    private String name;
    private String description;
    private int stockQuantity;
    private double purchasePrice;
    private double sellingPrice;

    public SparePart() {}

    public SparePart(int id, String name, String description, int stockQuantity, double purchasePrice, double sellingPrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.stockQuantity = stockQuantity;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }

    public double getPurchasePrice() { return purchasePrice; }
    public void setPurchasePrice(double purchasePrice) { this.purchasePrice = purchasePrice; }

    public double getSellingPrice() { return sellingPrice; }
    public void setSellingPrice(double sellingPrice) { this.sellingPrice = sellingPrice; }
}
