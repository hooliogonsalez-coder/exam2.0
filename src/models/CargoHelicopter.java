package models;

public class CargoHelicopter extends Transport{
    private double FlightRange;

    public CargoHelicopter(String id, String model, double baterryLevel, double flightRange) {
        super(id, model, baterryLevel);
        FlightRange = flightRange;
    }

    public double getFlightRange() {return FlightRange;}
    public void setFlightRange(double flightRange) {FlightRange = flightRange;}

    @Override
    public String getDetails(){
        return String.format("Дрон [%s | %s | %s | заряд %.1f%% | %.1f км.]",
                getId(),
                getModel(),
                isInTransit() ? "в пути" : "на базе",
                getBatteryLevel(),
                getFlightRange()
        );
    }
}
