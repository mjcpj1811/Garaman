package Garaman.model;

public class ServiceRequest {
    private int id;
    private int quantity;
    private Service service;
    private Technician technician;

    public ServiceRequest() {}

    public ServiceRequest(int id, int quantity, Service service, Technician technician) {
        this.id = id;
        this.quantity = quantity;
        this.service = service;
        this.technician = technician;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public Service getService() { return service; }
    public void setService(Service service) { this.service = service; }

    public Technician getTechnician() { return technician; }
    public void setTechnician(Technician technician) { this.technician = technician; }
}
