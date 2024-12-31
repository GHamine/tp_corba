package server;

import java.util.Properties;

import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NameComponent;

public class TravelAgencyServer {

    public static void main(String[] args) {


        // Starting Naming Service

        //************************************//
        //                                    //
        //   tnameserv -ORBInitialPort 1050   //
        //                                    //
        //************************************//




        Properties props = new Properties();
        props.put("org.omg.CORBA.ORBInitialHost", "127.0.0.1");
        props.put("org.omg.CORBA.ORBInitialPort", "1050");

        // Initialize the ORB
        ORB orb = ORB.init(args, props);


        try {


            // Get reference to the root POA and activate the POA manager
            POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPOA.the_POAManager().activate();

            // Create the TravelAgencyImpl object
            TravelAgencyImpl travelAgencyImpl = new TravelAgencyImpl();

            // Register the object with the ORB
            Object ref = rootPOA.servant_to_reference(travelAgencyImpl);
            // String ior = orb.object_to_string(ref);

            // Get the root naming context
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // Bind the object reference in the naming service
            String name = "TravelAgency";
            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path, ref);

            // Print the IOR to the console
            System.out.println("Travel Agency Server is running...");
            // System.out.println(ior);

            // Wait for invocations from clients
            orb.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}