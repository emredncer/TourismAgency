package com.TourismAgencySystem.Model;

public class Room {
    private int id;
    private String name;
    private int stock;
    private int hotel_Id;
    private int bedCount;
    private boolean hasTv;
    private boolean hasMinibar;
    private boolean hasSafeBox;

    public Room(int id, String name, int stock, int hotelId, int bedCount, boolean hasTv, boolean hasMinibar, boolean hasSafeBox) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.hotel_Id = hotelId;
        this.bedCount = bedCount;
        this.hasTv = hasTv;
        this.hasMinibar = hasMinibar;
        this.hasSafeBox = hasSafeBox;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getHotel_Id() {
        return hotel_Id;
    }

    public void setHotel_Id(int hotel_Id) {
        this.hotel_Id = hotel_Id;
    }

    public int getBedCount() {
        return bedCount;
    }

    public void setBedCount(int bedCount) {
        this.bedCount = bedCount;
    }

    public boolean isHasTv() {
        return hasTv;
    }

    public void setHasTv(boolean hasTv) {
        this.hasTv = hasTv;
    }

    public boolean isHasMinibar() {
        return hasMinibar;
    }

    public void setHasMinibar(boolean hasMinibar) {
        this.hasMinibar = hasMinibar;
    }

    public boolean isHasSafeBox() {
        return hasSafeBox;
    }

    public void setHasSafeBox(boolean hasSafeBox) {
        this.hasSafeBox = hasSafeBox;
    }
}


