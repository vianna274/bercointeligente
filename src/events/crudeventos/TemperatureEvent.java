package events.crudeventos;

import java.util.EventObject;

public class TemperatureEvent extends EventObject {
    private Action action;
    private double temperature;

    public TemperatureEvent(Object source, Action action, double temperature) {
        super(source);
        this.action = action;
        this.temperature = temperature;
    }

    public Action getAction() { return action; }
    public double getTemperature() { return temperature; }
}
