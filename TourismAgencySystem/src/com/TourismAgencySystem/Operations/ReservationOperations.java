package com.TourismAgencySystem.Operations;

import com.TourismAgencySystem.Helper.DBConnector;
import com.TourismAgencySystem.Model.BoardType;
import com.TourismAgencySystem.Model.Reservation;

import java.sql.*;
import java.util.ArrayList;

import com.TourismAgencySystem.Model.Room;
import com.TourismAgencySystem.Model.Season;

public class ReservationOperations implements IOperations<Reservation>{
    private RoomOperations roomOps;
    private BoardTypeOperations boardOps;
    private SeasonOperations seasonOps;
    public ReservationOperations() {
        seasonOps = new SeasonOperations();
        roomOps = new RoomOperations();
        boardOps = new BoardTypeOperations();
    }
//preparedStatement.setDouble(5, );
    @Override
    public void Create(Reservation model) {
        // Odanın stoğunu kontrol ettim
        if (RoomStockCheck(model.getRoom_id())){
            try (Connection connection = DBConnector.getInstance()) {
                String query = "INSERT INTO Reservation (hotel_id, boardType_id, room_id, season_id, " +
                        "totalPrice, childCount, adultCount, checkInDate, checkOutDate) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    double total = CalculateReservationTotal(model.getBoardType_id(),model.getSeason_id(),model.getAdultCount(),model.getChildCount(),model.getCommissionRate(),model.getCheckInDate(),model.getCheckOutDate());

                    preparedStatement.setInt(1, model.getHotel_id());
                    preparedStatement.setInt(2, model.getBoardType_id());
                    preparedStatement.setInt(3, model.getRoom_id());
                    preparedStatement.setInt(4, model.getSeason_id());
                    preparedStatement.setDouble(5, total);
                    preparedStatement.setInt(6, model.getChildCount());
                    preparedStatement.setInt(7, model.getAdultCount());
                    preparedStatement.setDate(8, model.getCheckInDate());
                    preparedStatement.setDate(9, model.getCheckOutDate());

                    preparedStatement.executeUpdate();
                    ReduceRoomStock(model.getRoom_id());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Reservation GetFetchById(int id) {
        String query = "SELECT * FROM Reservation WHERE id = ?";

        try (Connection connection = DBConnector.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Reservation reservation = new Reservation();
                reservation.setId(resultSet.getInt("id"));
                reservation.setHotel_id(resultSet.getInt("hotel_id"));
                reservation.setBoardType_id(resultSet.getInt("boardType_id"));
                reservation.setRoom_id(resultSet.getInt("room_id"));
                reservation.setSeason_id(resultSet.getInt("season_id"));
                reservation.setTotalPrice(resultSet.getDouble("totalPrice"));
                reservation.setChildCount(resultSet.getInt("childCount"));
                reservation.setAdultCount(resultSet.getInt("adultCount"));

                return reservation;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArrayList<Reservation> GetAll() {
        ArrayList<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM Reservation";

        try (Connection connection = DBConnector.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Reservation reservation = new Reservation();
                reservation.setId(resultSet.getInt("id"));
                reservation.setHotel_id(resultSet.getInt("hotel_id"));
                reservation.setBoardType_id(resultSet.getInt("boardType_id"));
                reservation.setRoom_id(resultSet.getInt("room_id"));
                reservation.setSeason_id(resultSet.getInt("season_id"));
                reservation.setTotalPrice(resultSet.getDouble("totalPrice"));
                reservation.setChildCount(resultSet.getInt("childCount"));
                reservation.setAdultCount(resultSet.getInt("adultCount"));

                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }

    @Override
    public void Update(Reservation model) {
        String query = "UPDATE Reservation SET hotel_id = ?, boardType_id = ?, room_id = ?, season_id = ?, " +
                "totalPrice = ?, childCount = ?, adultCount = ? WHERE id = ?";

        try (Connection connection = DBConnector.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, model.getHotel_id());
            preparedStatement.setInt(2, model.getBoardType_id());
            preparedStatement.setInt(3, model.getRoom_id());
            preparedStatement.setInt(4, model.getSeason_id());
            preparedStatement.setDouble(5, model.getTotalPrice());
            preparedStatement.setInt(6, model.getChildCount());
            preparedStatement.setInt(7, model.getAdultCount());
            preparedStatement.setInt(8, model.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Delete(Reservation model) {
        String query = "DELETE FROM Reservation WHERE id = ?";

        try (Connection connection = DBConnector.getInstance();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, model.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean  RoomStockCheck(int roomId){
        Room room = roomOps.GetFetchById(roomId);
        if (room.getStock() > 0)
            return true;

        else
            return false;
    }

    private void ReduceRoomStock(int roomId){
        Room room = roomOps.GetFetchById(roomId);
        int currentStock = room.getStock();
        room.setStock(--currentStock);
        roomOps.Update(room);
    }

    // Değerlendirme formu 10-12-15
    private double CalculateReservationTotal (int boardTypeId, int seasonId, int adultCount, int childCount, double commissionRate, Date checkInDate, Date checkOutDate){
        int nightCount = getDayDifference(checkInDate,checkOutDate);

        BoardType boardType = boardOps.GetFetchById(boardTypeId);
        Season season = seasonOps.GetFetchById(seasonId);

        double boardTypePriceRate = boardType.getPriceRate();
        double adultPrice = season.getAdultPrice();
        double childPrice = season.getChildPrice();

        double childAndAdultTotal = (childCount * childPrice) + (adultCount * adultPrice);
        double boardTypePriceRateTotal = boardTypePriceRate * childAndAdultTotal;
        double commission = boardTypePriceRateTotal * commissionRate;
        double commissionTotal = boardTypePriceRateTotal + commission;

        double total =  commissionTotal * nightCount;

        return total;
    }

    public static int getDayDifference(Date startDate, Date endDate) {
        long millisDifference = endDate.getTime() - startDate.getTime();
        return (int) (millisDifference / (24 * 60 * 60 * 1000));
    }
}
