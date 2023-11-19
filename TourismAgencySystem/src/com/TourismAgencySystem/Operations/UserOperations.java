package com.TourismAgencySystem.Operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.TourismAgencySystem.Helper.DBConnector;
import com.TourismAgencySystem.Model.User;


public class UserOperations implements IOperations<User>{

    @Override
    public void Create(User model) {
        String query = "INSERT INTO User (uname, pass, name_lastname, role) VALUES (?, ?, ?, ?)";

            try (Connection connection = DBConnector.getInstance();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, model.getUname());
                preparedStatement.setString(2, model.getPass());
                preparedStatement.setString(3, model.getName_lastname());
                preparedStatement.setString(4, model.getRole());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public User GetFetchById(int id) {
        String query = "SELECT * FROM User WHERE id = ?";
        User user = null;

        try (Connection connection = DBConnector.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User(0, "","","","");
                user.setId(resultSet.getInt("id"));
                user.setUname(resultSet.getString("uname"));
                user.setPass(resultSet.getString("pass"));
                user.setName_lastname(resultSet.getString("name_lastname"));
                user.setRole(resultSet.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public ArrayList<User> GetAll() {
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT * FROM User";

        try (Connection connection = DBConnector.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                User user = new User(0,"",",","","");
                user.setId(resultSet.getInt("id"));
                user.setUname(resultSet.getString("uname"));
                user.setPass(resultSet.getString("pass"));
                user.setName_lastname(resultSet.getString("name_lastname"));
                user.setRole(resultSet.getString("role"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;}

    @Override
    public void Update(User model) {
        String query = "UPDATE User SET uname = ?, pass = ?, name_lastname = ?, role = ? WHERE id = ?";

        try (Connection connection = DBConnector.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, model.getUname());
            preparedStatement.setString(2, model.getPass());
            preparedStatement.setString(3, model.getName_lastname());
            preparedStatement.setString(4, model.getRole());
            preparedStatement.setInt(5, model.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Delete(User model) {
        String query = "DELETE FROM User WHERE id = ?";

        try (Connection connection = DBConnector.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, model.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
