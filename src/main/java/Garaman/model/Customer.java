package Garaman.model;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Member {
    private List<Appointment> appointments;

    public Customer() {
        super();
        this.appointments = new ArrayList<>();
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void addAppointment(Appointment appointment) {
        if (appointments == null) appointments = new ArrayList<>();
        appointments.add(appointment);
    }
}
