package ParkingLot;

public class Vehicle {
    public VehicleType type;
    public String regNumber;
    public String color;

    public Vehicle (VehicleType type, String regNumber, String color) {
        this.type = type;
        this.regNumber = regNumber;
        this.color = color;
    }
}