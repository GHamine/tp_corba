package client;

import java.util.Properties;

import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

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
        Properties props = new Properties();
        props.put("org.omg.CORBA.ORBInitialHost", "127.0.0.1");
        props.put("org.omg.CORBA.ORBInitialPort", "1050");

        ORB orb = ORB.init(args, props);


    try {

        // Get the root naming context
        Object objRef = orb.resolve_initial_references("NameService");
        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

        // Resolve the object reference in the naming service
        String name = "TravelAgency";
        TravelAgency agency = TravelAgencyHelper.narrow(ncRef.resolve_str(name));

        // Get the list of available trips from the Travel Agency server
        Trip[] trips = agency.getAvailableTrips();

        // Print the list of available trips
        for (Trip trip : trips) {
            System.out.println(trip);
        }        
        
        System.out.println("Connected server successfully");
        // boolean reserve = agency.bookTrip(1, "Corba");
        // System.out.println(reserve ? "Reservation successful" : "Failed to book trip");
 
    } catch (Exception e) {
        e.printStackTrace();
    }


   }
}
