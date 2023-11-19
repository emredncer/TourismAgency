package com.TourismAgencySystem.Model;

public class BoardType {
    private int id;
    private int hotel_id;

    private String name; //burada name'e göre arama, ekleme vs. işlemleri yapacağım!
    private double priceRate; //pansiyon tipine göre

    public BoardType(int id, int hotel_id, String name, double priceRate) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.name = name;
        this.priceRate = priceRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPriceRate() {
        return priceRate;
    }

    public void setPriceRate(double priceRate) {
        this.priceRate = priceRate;
    }
}
