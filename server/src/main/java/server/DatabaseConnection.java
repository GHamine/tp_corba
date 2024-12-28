package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {
    private Connection connection;

    String url = "jdbc:mysql://localhost:3306/tp2";
    String username = "root";
    String password = "0000";


    public DatabaseConnection() throws SQLException {
        this.connection = DriverManager.getConnection(url, username, password);
    }

    
    public ResultSet getAvailableTrips() throws SQLException {
        String query = "SELECT * FROM trips WHERE available_seats > 0";
        PreparedStatement stmt = connection.prepareStatement(query);
        return stmt.executeQuery();
    }

    
    public boolean addReservation(String clientName, int tripId) throws SQLException {

        try {

            String checkSeatsQuery = "SELECT available_seats FROM trips WHERE id = ?";
            PreparedStatement checkSeatsStmt = connection.prepareStatement(checkSeatsQuery);
            checkSeatsStmt.setInt(1, tripId);
            ResultSet rs = checkSeatsStmt.executeQuery();

            if (rs.next()) {
                int availableSeats = rs.getInt("available_seats");

                if (availableSeats > 0) {

                    String reservationQuery = "INSERT INTO reservations (client_name, trip_id, reservation_date) VALUES (?, ?, CURDATE())";
                    PreparedStatement reservationStmt = connection.prepareStatement(reservationQuery);
                    reservationStmt.setString(1, clientName);
                    reservationStmt.setInt(2, tripId);
                    reservationStmt.executeUpdate();

                    String updateSeatsQuery = "UPDATE trips SET available_seats = available_seats - 1 WHERE id = ?";
                    PreparedStatement updateSeatsStmt = connection.prepareStatement(updateSeatsQuery);
                    updateSeatsStmt.setInt(1, tripId);
                    updateSeatsStmt.executeUpdate();

                    return true;
                } else {
                    System.out.println("No available seats for this trip.");
                }
            } else {
                System.out.println("Trip ID not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

}
