package ParkingLot;

import java.io.*;

public class ParkingLotUtil {
    public static void main(String[] args) {
        try {
            ParkingLot parkingLot = null;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String str = br.readLine();
            while(!str.equals("exit")){
                String[] input = str.split(" ");
                switch(input[0]){
                    case "create_parking_lot":
                        parkingLot = createParkingLot(parkingLot, input);
                        break;
                    case "park_vehicle":
                        parkVehicle(parkingLot, input);
                        break;
                    case "unpark_vehicle":
                        unparkVehicle(parkingLot, input);
                        break;
                    case "display":
                        display(parkingLot, input);
                        break;
                    case "exit":
                        return;
                }

                str = br.readLine();
            }

        }
        catch (IOException ioEx) {
            System.out.println(ioEx);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private static ParkingLot createParkingLot(ParkingLot parkingLot, String[] input) {
        int floorCount = Integer.parseInt(input[2]);
        int slotCount = Integer.parseInt(input[3]);
        parkingLot = new ParkingLot(input[1], floorCount, slotCount);
        System.out.println("Created parking lot with "+ floorCount + " floors and " + slotCount + " slots per floor");
        return parkingLot;
    }

    private static void parkVehicle(ParkingLot parkingLot, String[] input) {
        if(parkingLot != null) {
            VehicleType type = VehicleType.valueOf(input[1]);
            String regNumber = input[2];
            String color = input[3];
            Vehicle vh = new Vehicle(type, regNumber, color);
            System.out.println(parkingLot.parkVehicle(vh));
            return;
        }
        System.out.println("Invalid parking lot");
    }

    private static void unparkVehicle(ParkingLot parkingLot, String[] input) {
        if(parkingLot != null) {
            String ticket = input[1];
            System.out.println(parkingLot.unparkVehicle(ticket));
            return;
        }
        System.out.println("Invalid parking lot");
    }

    private static void display(ParkingLot parkingLot, String[] input) {
        if(parkingLot != null) {
            DisplayType dt = DisplayType.valueOf(input[1]);
            VehicleType vt = VehicleType.valueOf(input[2]);
            parkingLot.display(dt, vt);
            return;
        }
        System.out.println("Invalid parking lot");
    }
}