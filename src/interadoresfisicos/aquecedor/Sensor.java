package interadoresfisicos.aquecedor;

public class Sensor {

    private double temperature;

    private final static double MAX_TEMPERATURE = 38.0;
    private final static double NORMAL_TEMPERATURE = 22.0;

    public Sensor() {
        this.temperature = NORMAL_TEMPERATURE;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        if (temperature <= MAX_TEMPERATURE)
            this.temperature = temperature;
    }

    public void increaseTemperature() {
        this.temperature = MAX_TEMPERATURE;
    }

    public void resetTemperature() {
        this.temperature = NORMAL_TEMPERATURE;
    }
}
