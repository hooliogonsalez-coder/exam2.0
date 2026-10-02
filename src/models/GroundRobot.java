package models;

public class GroundRobot extends Transport{
    private int WheelConditions;

    public GroundRobot(String id, String model, double baterryLevel, int wheelConditions) {
        super(id, model, baterryLevel);
        WheelConditions = wheelConditions;
    }

    public int getWheelConditions() {return WheelConditions;}
    public void setWheelConditions(int wheelConditions) {WheelConditions = wheelConditions;}

    @Override
    public String getDetails(){
        return String.format("Дрон [%s | %s | %s | заряд %.1f%% | состояние колёс: %.1d%%]",
                getId(),
                getModel(),
                isInTransit() ? "в пути" : "на базе",
                getBatteryLevel(),
                getWheelConditions()
        );
    }
}
