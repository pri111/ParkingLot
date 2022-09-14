package ParkingLot;

import java.util.*;

public class Floor {
    public int totalSlots;
    public int totalFreeSlots;
    public HashMap<VehicleType, Integer> freeSlotCount;
    private Slot[] slots;

    public Floor(int slotCount) {
        this.totalSlots = slotCount;
        this.totalFreeSlots = slotCount;
        freeSlotCount = new HashMap<VehicleType, Integer>();
        initSlots();
    }

    private void initSlots() {
        this.slots = new Slot[this.totalSlots];

        // initialize for trucks
        for(int i=0; i<1; i++){
            this.slots[i] = new Slot(i+1, VehicleType.TRUCK);
        }
        freeSlotCount.put(VehicleType.TRUCK, 1);

        // initialize for bikes
        for(int i=1; i<3; i++){
            this.slots[i] = new Slot(i+1, VehicleType.BIKE);
        }
        freeSlotCount.put(VehicleType.BIKE, 2);

        // initialize for cars
        for(int i=3; i<this.totalSlots; i++){
            this.slots[i] = new Slot(i+1, VehicleType.CAR);
        }
        freeSlotCount.put(VehicleType.CAR, totalSlots-3);
    }

    public boolean hasSlot(VehicleType type) {
        if(this.freeSlotCount.get(type) > 0) {
            return true;
        }
        return false;
    }

    public int parkVehicle(VehicleType type, Vehicle vehicle) {
        for(int i=0; i<this.totalSlots; i++) {
            if(this.slots[i].vehicleType == type && this.slots[i].isFree){
                this.slots[i].isFree = false;
                this.slots[i].vehicle = vehicle;
                int slotCount = this.freeSlotCount.get(type);
                slotCount--;
                this.freeSlotCount.put(type, slotCount);
                totalFreeSlots--;
                return this.slots[i].id;
            }
        }
        return -1;
    }

    public Vehicle removeVehicle(int slotId) {
        if(this.slots[slotId-1].isFree) {
            return null;
        }
        Vehicle parkedVehicle = this.slots[slotId-1].vehicle;
        this.slots[slotId-1].isFree = true;
        this.slots[slotId-1].vehicle  = null;
        int slotCount = this.freeSlotCount.get(this.slots[slotId-1].vehicleType);
        slotCount++;
        this.freeSlotCount.put(this.slots[slotId-1].vehicleType, slotCount);
        totalFreeSlots++;

        return parkedVehicle;
    }

    public ArrayList<Integer> getFreeSlots(VehicleType type) {
        ArrayList<Integer> freeSlots = new ArrayList<Integer>();
        for(int i=0; i<this.totalSlots; i++) {
            if(this.slots[i].vehicleType == type && this.slots[i].isFree)
                freeSlots.add(this.slots[i].id);
        }
        return freeSlots;
    }

    public ArrayList<Integer> getOccSlots(VehicleType type) {
        ArrayList<Integer> occSlots = new ArrayList<Integer>();
        for(int i=0; i<this.totalSlots; i++) {
            if(this.slots[i].vehicleType == type && !this.slots[i].isFree)
                occSlots.add(this.slots[i].id);
        }
        return occSlots;
    }
}