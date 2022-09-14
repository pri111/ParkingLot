package ParkingLot;

import java.util.*;

public class ParkingLot {
    public String id;
    public Floor[] floors;
    public HashSet<String> tickets;

    public ParkingLot(String id, int floorCount, int slotCount){
        this.id = id;
        this.floors = new Floor[floorCount];
        for(int i=0; i<floorCount; i++){
            this.floors[i] = new Floor(slotCount);
        }
        tickets = new HashSet<>();
    }

    public String parkVehicle(Vehicle vh) {
        for(int i=0; i<this.floors.length; i++){
            if(this.floors[i].hasSlot(vh.type)){
                int slot = this.floors[i].parkVehicle(vh.type, vh);
                if(slot > 0){
                    String ticket = id+"_"+(i+1)+"_"+slot;
                    tickets.add(ticket);
                    return ("Parked vehicle. Ticket ID: "+ticket);
                }
            }
        }
        return "Parking Lot Full";
    }

    public String unparkVehicle(String ticket) {
        String[] ticketInfo = ticket.split("_");
        int floor = Integer.parseInt(ticketInfo[1]);
        int slot = Integer.parseInt(ticketInfo[2]);
        if(floor > this.floors.length || slot > this.floors[floor-1].totalSlots) {
            return "Invalid Ticket";
        }
        Vehicle vh = this.floors[floor-1].removeVehicle(slot);
        if(vh == null){
            return "Invalid Ticket";
        }
        tickets.remove(ticket);
        return ("Unparked vehicle with Registration Number: " + vh.regNumber + " and Color: " + vh.color);
    }

    public void display(DisplayType displayType, VehicleType vehicleType) {
        if(displayType == DisplayType.free_count) {
            displayFreeCount(vehicleType);
            return;
        }
        if(displayType == DisplayType.free_slots) {
            displayFreeSlots(vehicleType);
            return;
        }
        if(displayType == DisplayType.occupied_slots) {
            displayOccupiedSlots(vehicleType);
            return;
        }
    }

    private void displayFreeCount(VehicleType type) {
        for(int i=0; i< this.floors.length; i++) {
            System.out.println("No. of free slots for "+type.toString()+" on Floor "+(i+1)+": "+this.floors[i].freeSlotCount.get(type));
        }
    }

    private void displayFreeSlots(VehicleType type) {
        for(int i=0; i< this.floors.length; i++) {
            System.out.println("Free slots for "+type.toString()+" on Floor "+(i+1)+": "+this.floors[i].getFreeSlots(type).toString());
        }
    }

    private void displayOccupiedSlots(VehicleType type) {
        for(int i=0; i< this.floors.length; i++) {
            System.out.println("Occupied slots for "+type.toString()+" on Floor "+(i+1)+": "+this.floors[i].getOccSlots(type).toString());
        }
    }
}