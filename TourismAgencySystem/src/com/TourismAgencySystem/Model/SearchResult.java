package com.TourismAgencySystem.Model;

public class SearchResult {
    private String city;
    private String district;
    private String hotel_name;
    private int bedCount;
    private boolean free_parking;
    private boolean wifi;
    private byte star;
    private boolean pool;
    private boolean gym;
    private boolean concierge;
    private boolean service;
    private boolean spa;
    private boolean tv;
    private boolean miniBar;
    private boolean safeBox;
    private int season_id;
    private int board_type_id;
    private  int room_id;

    public SearchResult(String city, String district, String hotel_name, int bedCount, boolean free_parking, boolean wifi, byte star, boolean pool, boolean gym, boolean service, boolean spa, boolean tv, boolean miniBar, boolean safeBox, int season_id, int board_type_id, int room_id,boolean concierge) {
        this.city = city;
        this.district = district;
        this.hotel_name = hotel_name;
        this.bedCount = bedCount;
        this.free_parking = free_parking;
        this.wifi = wifi;
        this.star = star;
        this.pool = pool;
        this.gym = gym;
        this.service = service;
        this.spa = spa;
        this.tv = tv;
        this.miniBar = miniBar;
        this.safeBox = safeBox;
        this.season_id = season_id;
        this.board_type_id = board_type_id;
        this.room_id = room_id;
        this.concierge = concierge;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public int getBedCount() {
        return bedCount;
    }

    public void setBedCount(int bedCount) {
        this.bedCount = bedCount;
    }

    public boolean isFree_parking() {
        return free_parking;
    }

    public void setFree_parking(boolean free_parking) {
        this.free_parking = free_parking;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public byte getStar() {
        return star;
    }

    public void setStar(byte star) {
        this.star = star;
    }

    public boolean isPool() {
        return pool;
    }

    public void setPool(boolean pool) {
        this.pool = pool;
    }

    public boolean isGym() {
        return gym;
    }

    public void setGym(boolean gym) {
        this.gym = gym;
    }

    public boolean isService() {
        return service;
    }

    public void setService(boolean service) {
        this.service = service;
    }

    public boolean isSpa() {
        return spa;
    }

    public void setSpa(boolean spa) {
        this.spa = spa;
    }

    public boolean isTv() {
        return tv;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    public boolean isMiniBar() {
        return miniBar;
    }

    public void setMiniBar(boolean miniBar) {
        this.miniBar = miniBar;
    }

    public boolean isSafeBox() {
        return safeBox;
    }

    public void setSafeBox(boolean safeBox) {
        this.safeBox = safeBox;
    }

    public int getSeason_id() {
        return season_id;
    }

    public void setSeason_id(int season_id) {
        this.season_id = season_id;
    }

    public int getBoard_type_id() {
        return board_type_id;
    }

    public void setBoard_type_id(int board_type_id) {
        this.board_type_id = board_type_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public boolean isConcierge() {
        return concierge;
    }

    public void setConcierge(boolean concierge) {
        this.concierge = concierge;
    }
}
