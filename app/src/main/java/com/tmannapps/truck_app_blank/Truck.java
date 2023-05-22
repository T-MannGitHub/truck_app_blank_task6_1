package com.tmannapps.truck_app_blank;
//data model to bind data to recycler view
public class Truck {
    private String modelName, vehicleType, capacity; //parameters
    private int truckImage, id;

    //constructor
    public Truck (int id, String modelName, String vehicleType, String capacity, int truckImage){

        this.id = id;
        this.modelName = modelName; //Title
        this.vehicleType = vehicleType; //DetailType
        this.capacity = capacity; //i.e. small, medium, large Detail2
        this.truckImage = truckImage;
    }

    //getters and setters
    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public int getTruckImage() {
        return truckImage;
    }

    public void setTruckImage(int truckImage) {
        this.truckImage = truckImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
