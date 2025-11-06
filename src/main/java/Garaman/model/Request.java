package Garaman.model;

import java.sql.Date;
import java.util.List;

public class Request {
    private int id;
    private String type;
    private String description;
    private Date requestDate;
    private String status;
    private Member customer;
    private Staff salesStaff;

    private List<ServiceRequest> serviceRequestList;
    private List<SparePartRequest> sparePartRequestList;

    public Request() {}

    public Request(int id, String type, String description, Date requestDate, String status,
                   Member customer, Staff salesStaff) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.requestDate = requestDate;
        this.status = status;
        this.customer = customer;
        this.salesStaff = salesStaff;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getRequestDate() { return requestDate; }
    public void setRequestDate(Date requestDate) { this.requestDate = requestDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Member getCustomer() { return customer; }
    public void setCustomer(Member customer) { this.customer = customer; }

    public Staff getSalesStaff() { return salesStaff; }
    public void setSalesStaff(Staff salesStaff) { this.salesStaff = salesStaff; }

    public List<ServiceRequest> getServiceRequestList() {
        return serviceRequestList;
    }

    public void setServiceRequestList(List<ServiceRequest> serviceRequestList) {
        this.serviceRequestList = serviceRequestList;
    }

    public List<SparePartRequest> getSparePartRequestList() {
        return sparePartRequestList;
    }

    public void setSparePartRequestList(List<SparePartRequest> sparePartRequestList) {
        this.sparePartRequestList = sparePartRequestList;
    }

}
