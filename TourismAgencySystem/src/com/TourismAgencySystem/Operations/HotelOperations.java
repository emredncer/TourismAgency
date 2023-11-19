package com.TourismAgencySystem.Operations;

import com.TourismAgencySystem.Helper.Config;
import com.TourismAgencySystem.Helper.DBConnector;
import com.TourismAgencySystem.Model.BoardType;
import com.TourismAgencySystem.Model.Hotel;
import com.TourismAgencySystem.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Integer.parseInt;

public class HotelOperations implements IOperations<Hotel> {

    @Override
    public void Create(Hotel model) {
        String query = "INSERT INTO Hotel (name, city, district, adress, email, phone, star, boardType, " +
                "hasFreeParking, hasWifi, hasSwimmingPool, hasGym, hasHotelConcierge, hasRoomService, hasSpa) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnector.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, model.getName());
            preparedStatement.setString(2, model.getCity());
            preparedStatement.setString(3, model.getDistrict());
            preparedStatement.setString(4, model.getAddress());
            preparedStatement.setString(5, model.getEmail());
            preparedStatement.setString(6, model.getPhone());
            preparedStatement.setInt(7, model.getStar());
            preparedStatement.setString(8, model.getBoardTypes());
            preparedStatement.setBoolean(9, model.isHasFreeParking());
            preparedStatement.setBoolean(10, model.isHasWifi());
            preparedStatement.setBoolean(11, model.isHasSwimmingPool());
            preparedStatement.setBoolean(12, model.isHasGym());
            preparedStatement.setBoolean(13, model.isHasHotelConcierge());
            preparedStatement.setBoolean(14, model.isHasRoomService());
            preparedStatement.setBoolean(15, model.isHasSpa());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();    //daha önce açıkladım Programı sonlandırmayan exception mesajını basar!
        }
    }

    @Override
    public Hotel GetFetchById(int id) {
        String query = "SELECT * FROM Hotel WHERE id = ?";
        try (Connection connection = DBConnector.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery(); //WRITE IŞLEMLERINDE executeQuery() !!!!!!!!

            if (resultSet.next()) {
                Hotel hotel = new Hotel();
                hotel.setId(resultSet.getInt("id"));
                hotel.setName(resultSet.getString("name"));
                hotel.setCity(resultSet.getString("city"));
                hotel.setDistrict(resultSet.getString("district"));
                hotel.setAddress(resultSet.getString("adress"));
                hotel.setEmail(resultSet.getString("email"));
                hotel.setPhone(resultSet.getString("phone"));
                hotel.setStar(resultSet.getByte("star"));
                hotel.setBoardTypes(resultSet.getString("boardType"));
                hotel.setHasFreeParking(resultSet.getBoolean("hasFreeParking"));
                hotel.setHasWifi(resultSet.getBoolean("hasWifi"));
                hotel.setHasSwimmingPool(resultSet.getBoolean("hasSwimmingPool"));
                hotel.setHasGym(resultSet.getBoolean("hasGym"));
                hotel.setHasHotelConcierge(resultSet.getBoolean("hasHotelConcierge"));
                hotel.setHasRoomService(resultSet.getBoolean("hasRoomService"));
                hotel.setHasSpa(resultSet.getBoolean("hasSpa"));


                return hotel;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Hata durumunda konsola hata mesajını yazdır
        }

        return null; // Eğer kayıt bulunamazsa, null döndürülebilir.
    }

    @Override
    public ArrayList<Hotel> GetAll() {
        ArrayList<Hotel> hotels = new ArrayList<>();
        String query = "SELECT * FROM Hotel";

        try (Connection connection = DBConnector.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Hotel hotel = new Hotel();
                hotel.setId(resultSet.getInt("id"));
                hotel.setName(resultSet.getString("name"));
                hotel.setCity(resultSet.getString("city"));
                hotel.setDistrict(resultSet.getString("district"));
                hotel.setAddress(resultSet.getString("adress"));
                hotel.setEmail(resultSet.getString("email"));
                hotel.setPhone(resultSet.getString("phone"));
                hotel.setStar(resultSet.getByte("star"));
                hotel.setBoardTypes(resultSet.getString("boardType"));
                hotel.setHasFreeParking(resultSet.getBoolean("hasFreeParking"));
                hotel.setHasWifi(resultSet.getBoolean("hasWifi"));
                hotel.setHasSwimmingPool(resultSet.getBoolean("hasSwimmingPool"));
                hotel.setHasGym(resultSet.getBoolean("hasGym"));
                hotel.setHasHotelConcierge(resultSet.getBoolean("hasHotelConcierge"));
                hotel.setHasRoomService(resultSet.getBoolean("hasRoomService"));
                hotel.setHasSpa(resultSet.getBoolean("hasSpa"));

                hotels.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hotels;
    }

    @Override
    public void Update(Hotel model) {
        String query = "UPDATE Hotel SET name = ?, city = ?, district = ?, adress = ?, email = ?, phone = ?, star = ?, " +
                "boardType = ?, hasFreeParking = ?, hasWifi = ?, hasSwimmingPool = ?, hasGym = ?, hasHotelConcierge = ?, " +
                "hasRoomService = ?, hasSpa = ? WHERE id = ?";

        try (Connection connection = DBConnector.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, model.getName());
            preparedStatement.setString(2, model.getCity());
            preparedStatement.setString(3, model.getDistrict());
            preparedStatement.setString(4, model.getAddress());
            preparedStatement.setString(5, model.getEmail());
            preparedStatement.setString(6, model.getPhone());
            preparedStatement.setByte(7, model.getStar());
            preparedStatement.setString(8, model.getBoardTypes()); // Eğer boardTypes bir stringse doğrudan atayabilirsiniz
            preparedStatement.setBoolean(9, model.isHasFreeParking());
            preparedStatement.setBoolean(10, model.isHasWifi());
            preparedStatement.setBoolean(11, model.isHasSwimmingPool());
            preparedStatement.setBoolean(12, model.isHasGym());
            preparedStatement.setBoolean(13, model.isHasHotelConcierge());
            preparedStatement.setBoolean(14, model.isHasRoomService());
            preparedStatement.setBoolean(15, model.isHasSpa());
            preparedStatement.setInt(16, model.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Delete(Hotel model) {
        String query = "DELETE FROM Hotel WHERE id = ?";

        try (Connection connection = DBConnector.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, model.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getIdByHotelName(String hotel_name){
        String query = "SELECT id FROM Hotel WHERE name = ?";
        try (Connection connection = DBConnector.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, hotel_name);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public void fillComboBoxWithHotelName(JComboBox<String> cb) {
        cb.removeAllItems();
        ArrayList<Hotel> hotels = GetAll();
        for (Hotel htl: hotels){
            cb.addItem(htl.getName());
        }
    }
    //bu metodu kullanarak var olmayan bir otel idsi ile oda ekleme yapılmasını önleyeceğim!
    public boolean isHotelExisting(String id){
        ArrayList<Hotel> allHotels = GetAll();
        for (Hotel hotel : allHotels){
            if (hotel.getId()==parseInt(id))
                return true;
        }
        return false;
    }

}
