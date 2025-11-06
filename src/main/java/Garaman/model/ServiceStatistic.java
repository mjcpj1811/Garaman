package Garaman.model;

public class ServiceStatistic extends Service{

    private int totalUsage;
    private double totalRevenue;
    public ServiceStatistic(int id, String name, int totalUsage, double totalRevenue) {
        super(id, name, null, 0);
        this.totalUsage = totalUsage;
        this.totalRevenue = totalRevenue;
    }

    public int getTotalUsage() { return totalUsage; }
    public double getTotalRevenue() { return totalRevenue; }

}
