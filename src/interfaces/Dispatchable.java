package interfaces;

public interface Dispatchable {
    void dispatch();
    void recall();
    boolean isInTransit();
}
