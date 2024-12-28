# Deliverables

- The IDL file [`TravelAgency.idl`](./src/main/idl/TravelAgency.idl).

- Documentation on setting up CORBA tools and compiling the IDL file .

- Set up Maven: [`pom.xml`](./pom.xml).

- Compile the IDL File (the file is already precompiled):
  - Navigate to the directory containing `TravelAgency.idl`.
  - Run the following command to compile the IDL file:
    ```sh
    idlj -fall TravelAgency.idl
    ```
  - Copy `TravelAgencyModule` to server & client Java files.

- Source code for [`TravelAgencyImpl`](./server/src/main/java/server/TravelAgencyImpl.java) and [`TravelAgencyServer`](./server/src/main/java/server/TravelAgencyServer.java).

- Start the ORB and server:
  - Run [`TravelAgencyServer`](./server/src/main/java/server/TravelAgencyServer.java).

- Source code for [`TravelAgencyClient`](./client/src/main/java/client/TravelAgencyClient.java).

- Sample output showing successful client-server communication .
