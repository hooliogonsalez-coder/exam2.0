package models;

public class DeliveryDrone extends Transport{
    private double MaxPayload;

    public DeliveryDrone(String id, String model, double baterryLevel, double maxPayload) {
        super(id, model, baterryLevel);
        MaxPayload = maxPayload;
    }

    public double getMaxPayload() {return MaxPayload;}
    public void setMaxPayload(double maxPayload) {MaxPayload = maxPayload;}

    @Override
    public String getDetails(){
        return String.format("Дрон [%s | %s | %s | заряд %.1f%% | %.1f кг.]",
                getId(),
                getModel(),
                isInTransit() ? "в пути" : "на базе",
                getBatteryLevel(),
                getMaxPayload()
                );
    }
}
