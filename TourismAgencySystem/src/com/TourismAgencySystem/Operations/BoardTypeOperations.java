package com.TourismAgencySystem.Operations;

import com.TourismAgencySystem.Model.BoardType;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


import com.TourismAgencySystem.Helper.DBConnector;
import com.TourismAgencySystem.Model.Hotel;

import javax.swing.*;


public class BoardTypeOperations implements IOperations<BoardType> {
    static Logger logger = Logger.getLogger(BoardTypeOperations.class.getName());
    HotelOperations hotelOps = new HotelOperations();
    @Override
    public void Create(BoardType model) {
        String query = "INSERT INTO BoardType ( hotel_id, name, priceRate) VALUES (?, ?, ?)"; //Board type_id hariç tablodan almam gerek!

        try (Connection connection = DBConnector.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, model.getHotel_id());   //hotel_id benim için bu noktada freign key!
            preparedStatement.setString(2, model.getName());
            preparedStatement.setDouble(3, model.getPriceRate());   //pansiyon tipi çarpanını tablodan çektim
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();    //Tüm programı patlatmamak adına try scope'undaki işlemlerden herhangibiri hatalı olduğunda
            //alakalı exception fırlatıldı ve program çalışmayı sürdürecek!(SQLexception olduğu aşikar!)
        }
    }

    @Override
    public BoardType GetFetchById(int id) {
        String query = "SELECT * FROM BoardType WHERE id = ?";
        BoardType boardType = null;

        try (Connection connection = DBConnector.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                boardType = new BoardType(0, 0, "", 0d);
                boardType.setId(resultSet.getInt("id"));
                boardType.setHotel_id(resultSet.getInt("hotel_id"));
                boardType.setName(resultSet.getString("name"));
                boardType.setPriceRate(resultSet.getDouble("priceRate"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return boardType;
    }

    @Override
    public ArrayList<BoardType> GetAll() {
        ArrayList<BoardType> boardTypes = new ArrayList<BoardType>();
        String query = "SELECT * FROM BoardType";

        try (Connection connection = DBConnector.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                BoardType boardType = new BoardType(0, 0, "", 0d);

                boardType.setId(resultSet.getInt("id"));
                boardType.setHotel_id(resultSet.getInt("hotel_id"));
                boardType.setName(resultSet.getString("name"));
                boardType.setPriceRate(resultSet.getDouble("priceRate"));
                boardTypes.add(boardType);
            }
        } catch (SQLException e) {
        }

        return boardTypes;
    }

    @Override
    public void Update(BoardType model) {       //Veritabanındaki pansiyonTipi verilerini güncelleyeceğim metot.
        String query = "UPDATE BoardType SET hotel_id = ?, name = ?, priceRate = ? WHERE id = ?";

        try (Connection connection = DBConnector.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, model.getHotel_id());
            preparedStatement.setString(2, model.getName());
            preparedStatement.setDouble(3, model.getPriceRate());
            preparedStatement.setInt(4, model.getId());
            preparedStatement.executeUpdate();  //OKUMA İŞLEMLERİ İÇİN executeUpdate(); !!!!
        } catch (SQLException e) {
            logger.getName();
            e.printStackTrace();    //amaç belli!

            for (int i = 0; i < 1000; i++) {
                //logging messages
                logger.log(Level.INFO, "Msg" + i);
                //excepteion ya da error aldığım bunarı loglayacak ve
                //konsolda gösterecek metot!!!

            }

        }
    }

    @Override
    public void Delete(BoardType model) {
        String query = "DELETE FROM BoardType WHERE id = ?";

        try (Connection connection = DBConnector.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, model.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String getHotelsBoardtypesByID(int hotel_id) { //bu metod gelen hotel_id içine boardtype isimlerini bir araya toplayacak.
        String query = "SELECT name FROM BoardType WHERE hotel_id = ?";
        StringBuilder fetchedBoardTypes = new StringBuilder();

        try (Connection connection = DBConnector.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, hotel_id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String boardTypeName = resultSet.getString("name");
                fetchedBoardTypes.append(boardTypeName).append(", ");
            }
            if (fetchedBoardTypes.length() > 0) {
                fetchedBoardTypes.delete(fetchedBoardTypes.length() - 2, fetchedBoardTypes.length());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return fetchedBoardTypes.toString();
    }


    public void fillComboBoxWithHotels(JComboBox<String> comboBox) {
        ArrayList<Hotel> hotels = hotelOps.GetAll();
        for (Hotel hotel: hotels){
            comboBox.addItem(hotel.getName());
        }
    }

    public void fillComboBoxWithBoardTypes(JComboBox<String> comboBox) {
        comboBox.addItem("Yarım Pansiyon");
        comboBox.addItem("Tam Pansiyon");
        comboBox.addItem("Sadece Yatak");
        comboBox.addItem("Herşey Dahil");
        comboBox.addItem("Tam Pansiyon Plus");
        comboBox.addItem("Deluxe");
        comboBox.addItem("Ultra Herşey Dahil");
    }
    public void fillComboBoxWithHotelName(JComboBox<String> cb, String hotel_name) {
        cb.removeAllItems();
        ArrayList<Hotel> hotels = hotelOps.GetAll();
        //ismi otel name e eşit olanı çevirecek (liste) ilkini seçtim, orElse ile de eğer karşılığı yoksa  null döndürdüm.
        Hotel selectedHotel = hotels.stream().filter(e -> e.getName().equals(hotel_name)).findFirst().orElse(null);
        ArrayList<BoardType> types = GetAll();
        for (BoardType bt: types){
            if (selectedHotel.getId()==bt.getHotel_id())
            cb.addItem(bt.getName());
        }
    }
    public String getIdByName(String name) {
        String query = "SELECT id FROM BoardType WHERE name = ?";
        StringBuilder fetchedBoardTypes = new StringBuilder();

        try (Connection connection = DBConnector.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String boardTypeName = resultSet.getString("name");
                fetchedBoardTypes.append(boardTypeName).append(", ");
            }
            if (fetchedBoardTypes.length() > 0) {
                fetchedBoardTypes.delete(fetchedBoardTypes.length() - 2, fetchedBoardTypes.length());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return fetchedBoardTypes.toString();
    }


}

