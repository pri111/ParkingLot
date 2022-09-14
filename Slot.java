package ParkingLot;

public class Slot {
    public int id;
    public VehicleType vehicleType;
    public boolean isFree;
    public Vehicle vehicle;

    public Slot (int id, VehicleType vehicleType) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.isFree = true;
        this.vehicle = null;
    }
}