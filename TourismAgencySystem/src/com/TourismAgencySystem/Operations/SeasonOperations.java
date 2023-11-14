package com.TourismAgencySystem.Operations;

import com.TourismAgencySystem.Helper.DBConnector;
import com.TourismAgencySystem.Model.Season;

import java.sql.*;
import java.util.ArrayList;

public class SeasonOperations implements IOperations<Season>{
    @Override
    public void Create(Season model) {
        String query = "INSERT INTO Season (hotel_id, name, seasonStartDate, seasonEndingDate, adultPrice, childPrice) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnector.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, model.getHotelId());
            preparedStatement.setString(2, model.getName());
            preparedStatement.setDate(3, model.getSeasonStartDate());
            preparedStatement.setDate(4, model.getSeasonEndingDate());
            preparedStatement.setInt(5, model.getAdultPrice());
            preparedStatement.setInt(6, model.getChildPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Season GetFetchById(int id) {
        String query = "SELECT * FROM Season WHERE id = ?";

        try (Connection connection = DBConnector.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Season season = new Season(0,0,"",new Date(0,0,0),new Date(0,0,0), 0, 0);
                season.setId(resultSet.getInt("id"));
                season.setHotelId(resultSet.getInt("hotel_id"));
                season.setName(resultSet.getString("name"));
                season.setSeasonStartDate(resultSet.getDate("seasonStartDate"));
                season.setSeasonEndingDate(resultSet.getDate("seasonEndingDate"));
                season.setAdultPrice(resultSet.getInt("adultPrice"));
                season.setChildPrice(resultSet.getInt("childPrice"));

                return season;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArrayList<Season> GetAll() {
        ArrayList<Season> seasons = new ArrayList<>();
        String query = "SELECT * FROM Season";

        try (Connection connection = DBConnector.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Season season = new Season(0,0,"",new Date(0,0,0),new Date(0,0,0), 0, 0);
                season.setId(resultSet.getInt("id"));
                season.setHotelId(resultSet.getInt("hotel_id"));
                season.setName(resultSet.getString("name"));
                season.setSeasonStartDate(resultSet.getDate("seasonStartDate"));
                season.setSeasonEndingDate(resultSet.getDate("seasonEndingDate"));
                season.setAdultPrice(resultSet.getInt("adultPrice"));
                season.setChildPrice(resultSet.getInt("childPrice"));

                seasons.add(season);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seasons;
    }

    @Override
    public void Update(Season model) {
        String query = "UPDATE Season SET hotel_id = ?, name = ?, seasonStartDate = ?, seasonEndingDate = ?, " +
                "adultPrice = ?, childPrice = ? WHERE id = ?";

        try (Connection connection = DBConnector.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, model.getHotelId());
            preparedStatement.setString(2, model.getName());
            preparedStatement.setDate(3, model.getSeasonStartDate());
            preparedStatement.setDate(4, model.getSeasonEndingDate());
            preparedStatement.setInt(5, model.getAdultPrice());
            preparedStatement.setInt(6, model.getChildPrice());
            preparedStatement.setInt(7, model.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Delete(Season model) {
        String query = "DELETE FROM Season WHERE id = ?";

        try (Connection connection = DBConnector.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, model.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
