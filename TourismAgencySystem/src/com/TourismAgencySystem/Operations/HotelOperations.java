package com.TourismAgencySystem.Operations;

import com.TourismAgencySystem.Helper.Config;
import com.TourismAgencySystem.Helper.DBConnector;
import com.TourismAgencySystem.Model.Hotel;
import com.TourismAgencySystem.Model.User;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class HotelOperations implements IOperations<Hotel> {
    @Override
    public void Create(Hotel model) {
        String[] columnNames = {"ID", "Name", "City", "District", "Address", "Email", "Phone", "Star",
                "Board Types", "Parking", "Wifi", "Pool", "Hotel concierge", "SPA"};
        DefaultTableModel mdl_htl_list = new DefaultTableModel(new Object[][]{}, columnNames);
        //Biliyorum çok hoş bir sorgu olmadı, ancak böyle tasarladım ve okunurluk açısından yorucu gibi gözükse de,
        //geliştirilebilirlik açısından daha kaliteli bir kod olduğu kanaatindeyim!
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
            preparedStatement.setString(8, getClass().getName()); //boardType bir foreign_key! burada String olarak name prop.u neye denk geliyorsa onu aldım kullanmak adına!
            //geri kalan VALUE değerlerimin her biri BOOLEAN! true olması halinde ismi yazdırılacak, false olması halinde yazılmayacak! yazdılırken de muhtemelen String.trim() metodunu kullanırım diye düşünüyorum,
            //burayı silmediysem muhtemelen unutmuşum demektir:)

            preparedStatement.setBoolean(9, model.isHasFreeParking());
            preparedStatement.setBoolean(10, model.isHasWifi());
            preparedStatement.setBoolean(11, model.isHasSwimmingPool());
            preparedStatement.setBoolean(12, model.isHasGym());
            preparedStatement.setBoolean(13, model.isHasHotelConcierge());
            preparedStatement.setBoolean(14, model.isHasRoomService());
            preparedStatement.setBoolean(15, model.isHasSpa());

            //15 index'li yazınca cıvata sandınız deeemi ama birazdan görürsünüz kimin algoritması daha böyüğümüş :)
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
    //bu metot database'e bir query atıp dönen resultsetleri toplayacak ve String olarak yazacak!
    public String hotelBoardTypeCollector(int hotelId) {
        String query = "SELECT name FROM BoardType WHERE hotel_id = ?";
        String boardTypes = "";

        try (Connection connection = DBConnector.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, hotelId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String boardType = resultSet.getString("name");
                boardTypes += boardType + ", ";
            }

            // Son virgülü ve boşluğu kaldırın
            if (!boardTypes.isEmpty()) {
                boardTypes = boardTypes.substring(0, boardTypes.length() - 2);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return boardTypes;
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




    // Search çalışması için fikir olarak kalsın
    public ArrayList<Hotel> GetFiltered(boolean hasSpa, Date checkInDate, Date checkOutDate) {
        ArrayList<Hotel> hotels = new ArrayList<>();

        // boolean tip veritabanında tinyInt olarak kaydedildiği için parametrede verilen boolean değerine göre bir int
        // oluşturmamız gerekli. Bunun için "hasSpaIntValue" değişkeni tanımlandı. Bu int değişken SQL cümleciğine verilecek.
        int hasSpaIntValue;

        if(hasSpa)
            hasSpaIntValue = 1;

        else
            hasSpaIntValue = 0;

        String query = "SELECT * FROM Hotel WHERE hasSpa =  ?";

        try (Connection connection = DBConnector.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1,hasSpaIntValue);
            ResultSet resultSet = preparedStatement.executeQuery(); //WRITE IŞLEMLERINDE executeQuery() !!!!!!!!

            while (resultSet.next()) {
                Hotel hotel = new Hotel();
                hotel.setId(resultSet.getInt("id"));
                hotel.setName(resultSet.getString("name"));
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
}
