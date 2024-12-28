package client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;

import TravelAgencyModule.*;

/**
 * The TravelAgencyClient class is responsible for initializing the ORB (Object Request Broker)
 * and reading the IOR (Interoperable Object Reference) from a file to connect to the Travel Agency server.
 */
public class TravelAgencyClient {

    /**
     * The main method initializes the ORB and reads the IOR from a file to connect to the Travel Agency server.
     *
     * @param args Command line arguments passed to the program.
     */
    public static void main(String[] args) {

        // Initialize the ORB
        ORB orb = ORB.init(args, null);

        String ior;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("TravelAgencyIOR.txt"))) {
            // Read the IOR from the file
            ior = bufferedReader.readLine();
        } catch (IOException e) {
            // Print the stack trace if an IOException occurs
            e.printStackTrace();
            return;
        }

        // Convert the IOR string to a CORBA object reference
        Object objRef = orb.string_to_object(ior);

        // Narrow the object reference to a TravelAgency object
        TravelAgency agency = TravelAgencyHelper.narrow(objRef);

        // Get the list of available trips from the Travel Agency server
        Trip[] trips = agency.getAvailableTrips();

        for (Trip trip : trips) {
            System.out.println(trip);
        }

        boolean reserve = agency.bookTrip(1, "Corba");
        System.out.println(reserve ? "Reservation successful" : "Failed to book trip");
    }
}
