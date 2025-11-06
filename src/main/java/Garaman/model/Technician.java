package Garaman.model;

public class Technician extends Staff {
    private String skills;

    public Technician() { super(); }

    public Technician(String position, String skills) {
        super(position);
        this.skills = skills;
    }

    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }
}
