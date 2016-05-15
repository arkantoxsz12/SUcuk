package com.sucuk.sucuk;

/**
 * Created by idokurel on 15.05.2016.
 */
public class OrderItem {
    private String name;
    private double price;
    private String date;
    private String order;
    private String address;
    private String payment;
    private String phone;
    private String status;

    public OrderItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String orderNum) {
        this.name = orderNum;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
