package models;

import interfaces.Dispatchable;

import java.io.Serializable;

public abstract class Transport implements Dispatchable, Serializable {
    protected String Id;
    protected String Model;
    protected boolean Status;
    protected double BatteryLevel;

    public Transport(String id,String model,double baterryLevel){
        this.Id = id;
        this.Model = model;
        this.Status = false;
        this.BatteryLevel = baterryLevel;
    }

    @Override
    public void dispatch(){
        Status = true;
    }

    @Override
    public void recall(){
        Status = false;
    }

    @Override
    public boolean isInTransit(){
        return Status;
    }

    public abstract String getDetails();
    public String getId() {return Id;}
    public void setId(String id) {Id = id;}
    public String getModel() {return Model;}
    public void setModel(String model) {Model = model;}
    public boolean isStatus() {return Status;}
    public void setStatus(boolean status) {Status = status;}
    public double getBatteryLevel() {return BatteryLevel;}
    public void setBatteryLevel(double batteryLevel) {BatteryLevel = batteryLevel;}
}
