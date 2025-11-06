package Garaman.model;

public class SparePartStatistic extends SparePart {
    private int totalUsage;
    private double totalRevenue;

    public SparePartStatistic(int id, String name, int totalUsage, double totalRevenue) {
        super(id, name, null, 0, 0, 0);
        this.totalUsage = totalUsage;
        this.totalRevenue = totalRevenue;
    }

    public int getTotalUsage() { return totalUsage; }
    public double getTotalRevenue() { return totalRevenue; }
}
