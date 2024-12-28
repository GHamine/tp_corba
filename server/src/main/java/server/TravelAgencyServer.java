package server;

import java.io.FileWriter;
import java.io.IOException;

import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class TravelAgencyServer {

    public static void main(String[] args) {
        try {
            // Initialize the ORB
            ORB orb = ORB.init(args, null);

            // Get reference to the root POA and activate the POA manager
            POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPOA.the_POAManager().activate();

            // Create the TravelAgencyImpl object
            TravelAgencyImpl travelAgencyImpl = new TravelAgencyImpl();

            // Register the object with the ORB
            Object ref = rootPOA.servant_to_reference(travelAgencyImpl);
            String ior = orb.object_to_string(ref);

            // Print the IOR to the console
            System.out.println("Travel Agency Server are running...");
            System.out.println(ior);

            // Save the IOR to a file
            try (FileWriter fileWriter = new FileWriter("TravelAgencyIOR.txt")) {
                fileWriter.write(ior);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Wait for invocations from clients
            orb.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}