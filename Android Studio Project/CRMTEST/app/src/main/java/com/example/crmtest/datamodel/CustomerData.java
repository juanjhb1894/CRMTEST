package com.example.crmtest.datamodel;

public class CustomerData {

    public int id;
    public String email;
    public String phone;
    public String name;
    public int status;
    public String created_at;

    public CustomerData(int id, String email, String phone, String name, int status, String created_at) {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.status = status;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }


    public String getPhone() {
        return phone;
    }


    public String getName() {
        return name;
    }


    public int getStatus() {
        return status;
    }


    public String getCreated_at() {
        return created_at;
    }

}
