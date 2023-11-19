package com.TourismAgencySystem.Model;

import java.util.ArrayList;

public class Hotel {
    private int id;
    private String name;
    private String city;
    private String district;
    private String address;
    private String email;
    private String phone;
    private byte star;
    private String boardTypes;
    private boolean hasFreeParking;
    private boolean hasWifi;
    private boolean hasSwimmingPool;
    private boolean hasGym;
    private boolean hasHotelConcierge;
    private boolean hasRoomService;
    private boolean hasSpa;

    public Hotel() {
        this.id = id;
        this.name = name;
        this.city = city;
        this.district = district;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.star = star;
        this.boardTypes = boardTypes;
        this.hasFreeParking = hasFreeParking;
        this.hasWifi = hasWifi;
        this.hasSwimmingPool = hasSwimmingPool;
        this.hasGym = hasGym;
        this.hasHotelConcierge = hasHotelConcierge;
        this.hasRoomService = hasRoomService;
        this.hasSpa = hasSpa;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte getStar() {
        return star;
    }

    public void setStar(byte star) {
        this.star = star;
    }

    public String getBoardTypes() {
        return boardTypes;
    }

    public void setBoardTypes(String boardTypes) {
        this.boardTypes = boardTypes;
    }

    public boolean isHasFreeParking() {
        return hasFreeParking;
    }

    public void setHasFreeParking(boolean hasFreeParking) {
        this.hasFreeParking = hasFreeParking;
    }

    public boolean isHasWifi() {
        return hasWifi;
    }

    public void setHasWifi(boolean hasWifi) {
        this.hasWifi = hasWifi;
    }

    public boolean isHasSwimmingPool() {
        return hasSwimmingPool;
    }

    public void setHasSwimmingPool(boolean hasSwimmingPool) {
        this.hasSwimmingPool = hasSwimmingPool;
    }

    public boolean isHasGym() {
        return hasGym;
    }

    public void setHasGym(boolean hasGym) {
        this.hasGym = hasGym;
    }

    public boolean isHasHotelConcierge() {
        return hasHotelConcierge;
    }

    public void setHasHotelConcierge(boolean hasHotelConcierge) {
        this.hasHotelConcierge = hasHotelConcierge;
    }

    public boolean isHasRoomService() {
        return hasRoomService;
    }

    public void setHasRoomService(boolean hasRoomService) {
        this.hasRoomService = hasRoomService;
    }

    public boolean isHasSpa() {
        return hasSpa;
    }

    public void setHasSpa(boolean hasSpa) {
        this.hasSpa = hasSpa;
    }
}