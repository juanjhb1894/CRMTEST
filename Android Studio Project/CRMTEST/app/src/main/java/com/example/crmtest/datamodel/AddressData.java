package com.example.crmtest.datamodel;

public class AddressData {

    public int id;
    public String customer;
    public String address;
    public String city;
    public String country;
    public int main;
    public int status;
    public String created_at;

    public AddressData(int id, String customer, String address, String city, String country, int main, int status, String created_at) {
        this.id = id;
        this.customer = customer;
        this.address = address;
        this.city = city;
        this.country = country;;
        this.main = main;
        this.status = status;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public String getCustomer() {
        return customer;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public int getMain() {
        return main;
    }

    public int getStatus() {
        return status;
    }


    public String getCreated_at() {
        return created_at;
    }

}
