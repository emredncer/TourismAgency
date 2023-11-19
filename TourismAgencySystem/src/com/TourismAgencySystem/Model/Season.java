package com.TourismAgencySystem.Model;

import java.sql.Date;

public class Season {
    private int id;
    private int hotelId;
    private String name;
    private Date seasonStartDate; //değerlendirme formu 10
    private Date seasonEndingDate;  //değerlendirme formu 10
    private int adultPrice;
    private int childPrice; //price'ları burada belirlemek en mantılısı gibi gözüküyordu. her sezon farklı ücret olacak.

    public Season(int id, int hotelId, String name, Date checkInDate, Date checkOutDate, int adultPrice, int childPrice) {
        this.id = id;
        this.hotelId = hotelId;
        this.name = name;
        this.seasonStartDate = checkInDate;
        this.seasonEndingDate = checkOutDate;
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getSeasonStartDate() {
        return seasonStartDate;
    }

    public void setSeasonStartDate(Date seasonStartDate) {
        this.seasonStartDate = seasonStartDate;
    }

    public Date getSeasonEndingDate() {
        return seasonEndingDate;
    }

    public void setSeasonEndingDate(Date seasonEndingDate) {
        this.seasonEndingDate = seasonEndingDate;
    }

    public int getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(int adultPrice) {
        this.adultPrice = adultPrice;
    }

    public int getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(int childPrice) {
        this.childPrice = childPrice;
    }
}