package server;

import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import TravelAgencyModule.*;

/**
 * Implementation of the TravelAgency interface.
 */
public class TravelAgencyImpl extends TravelAgencyPOA {
    private DatabaseConnection db;

    /**
     * Constructs a new TravelAgencyImpl instance and initializes the database connection.
     * 
     * @throws SQLException if a database access error occurs
     */
    protected TravelAgencyImpl() throws SQLException {
        super();
        this.db = new DatabaseConnection();
    }

    /**
     * Retrieves the list of available trips from the database.
     * 
     * @return an array of available trips
     */
    @Override
    public Trip[] getAvailableTrips() {
        List<Trip> trips = new ArrayList<>();
        try {
            ResultSet rs = db.getAvailableTrips();
            while (rs.next()) {
                Trip trip = new Trip(
                        rs.getInt("id"),
                        rs.getString("destination"),
                        rs.getDouble("price"),
                        rs.getInt("available_seats"));
                trips.add(trip);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trips.toArray(new Trip[0]);
    }
    
    /**
     * Books a trip for a client.
     * 
     * @param tripId the ID of the trip to book
     * @param clientName the name of the client booking the trip
     * @return true if the booking was successful, false otherwise
     */
    @Override
    public boolean bookTrip(int tripId, String clientName) {
        try {
            return db.addReservation(clientName, tripId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Closes the database connection when the object is garbage collected.
     * 
     * @throws Throwable if an error occurs during finalization
     */
    @Override
    protected void finalize() throws Throwable {
        db.closeConnection();
    }
}
