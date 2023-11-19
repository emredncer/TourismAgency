package com.TourismAgencySystem.Model;

import java.sql.Date;

public class Reservation {
    private final double AGENCY_COMMITION_RATE = 0.05; //acente her satıştan %5 komisyon alacak.
    private int id;
    private int hotel_id;
    private int boardType_id;
    private int room_id;
    private int season_id;
    private double totalPrice;
    private int childCount;
    private int adultCount;
    private String customerTC;
    private String customerName;
    private Date checkInDate;
    private Date checkOutDate;
    //acentanın alacağı komisyonu hesaplayacak olan metodum.
    public double getCommissionRate() { return AGENCY_COMMITION_RATE; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCheckInDate() { return checkInDate;}
    public void setCheckInDate(Date date) { this.checkInDate = date; }

    public Date getCheckOutDate() { return checkOutDate;}
    public void setCheckOutDate(Date date) { this.checkOutDate = date; }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public int getBoardType_id() {
        return boardType_id;
    }

    public void setBoardType_id(int boardType_id) {
        this.boardType_id = boardType_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getSeason_id() {
        return season_id;
    }

    public void setSeason_id(int season_id) {
        this.season_id = season_id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    public int getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(int adultCount) {
        this.adultCount = adultCount;
    }

    public String getCustomerTC() {
        return customerTC;
    }

    public void setCustomerTC(String customerTC) {
        this.customerTC = customerTC;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}