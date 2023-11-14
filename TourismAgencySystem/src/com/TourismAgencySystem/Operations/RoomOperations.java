package com.TourismAgencySystem.Operations;

import com.TourismAgencySystem.Helper.DBConnector;
import com.TourismAgencySystem.Model.Hotel;
import com.TourismAgencySystem.Model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomOperations implements IOperations<Room> {
    @Override
    public void Create(Room model) {
        String query = "INSERT INTO Room (name, stock, hotel_id, bedCount, hasTV, hasMinibar, hasSafeBox) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection connection = DBConnector.getInstance();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, model.getName());
            preparedStatement.setInt(2, model.getStock());
            preparedStatement.setInt(3, model.getHotel_Id());
            preparedStatement.setInt(4, model.getBedCount());
            preparedStatement.setBoolean(5, model.isHasTv());
            preparedStatement.setBoolean(6, model.isHasMinibar());
            preparedStatement.setBoolean(7, model.isHasSafeBox());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Room GetFetchById(int id) {//buna da bakcaz
        String query = "SELECT * FROM Room WHERE id = ?";

        try (Connection connection = DBConnector.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Room room = new Room(0, "", 0, 0, 0, false, false, false);
                room.setId(resultSet.getInt("id"));
                room.setName(resultSet.getString("name"));
                room.setStock(resultSet.getInt("stock"));
                room.setHotel_Id(resultSet.getInt("hotel_id"));
                room.setBedCount(resultSet.getInt("bedCount"));
                room.setHasTv(resultSet.getBoolean("hasTV"));
                room.setHasMinibar(resultSet.getBoolean("hasMinibar"));
                room.setHasSafeBox(resultSet.getBoolean("hasSafeBox"));

                return room;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public ArrayList<Room> GetAll() {
        ArrayList<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM Room";

        try (Connection connection = DBConnector.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Room room = new Room(0, "", 0, 0, 0, false, false, false);
                room.setId(resultSet.getInt("id"));
                room.setName(resultSet.getString("name"));
                room.setStock(resultSet.getInt("stock"));
                room.setHotel_Id(resultSet.getInt("hotel_id"));
                room.setBedCount(resultSet.getInt("bedCount"));
                room.setHasTv(resultSet.getBoolean("hasTV"));
                room.setHasMinibar(resultSet.getBoolean("hasMinibar"));
                room.setHasSafeBox(resultSet.getBoolean("hasSafeBox"));

                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }


    @Override
    public void Update(Room model) {
        String query = "UPDATE Room SET name = ?, stock = ?, hotel_id = ?, bedCount = ?, hasTV = ?, hasMinibar = ?, hasSafeBox = ? " +
                " WHERE id = ?";

        try (Connection connection = DBConnector.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, model.getName());
            preparedStatement.setInt(2, model.getStock());
            preparedStatement.setInt(3, model.getHotel_Id());
            preparedStatement.setInt(4, model.getBedCount());
            preparedStatement.setBoolean(5, model.isHasTv());
            preparedStatement.setBoolean(6, model.isHasMinibar());
            preparedStatement.setBoolean(7, model.isHasSafeBox());
            preparedStatement.setInt(8, model.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Delete(Room model) {
        String query = "DELETE FROM Room WHERE id = ?";

        try (Connection connection = DBConnector.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, model.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
