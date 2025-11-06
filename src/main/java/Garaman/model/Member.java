package Garaman.model;

import java.sql.Date;

public class Member {
    private int id;
    private String name;
    private String username;
    private String password;
    private Date birthday;
    private String address;
    private String email;
    private String phoneNumber;
    private String role;
    private String gender;

    public Member() {}

    public Member(int id, String name, String username, String password, Date birthday,
                  String address, String email, String phoneNumber, String role, String gender) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.gender = gender;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Date getBirthday() { return birthday; }
    public void setBirthday(Date birthday) { this.birthday = birthday; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }


}
