package interadoresfisicos.aquecedor;

import events.aquecedor.AquecedorEvent;
import events.aquecedor.AquecedorListener;
import events.crudeventos.Action;
import events.crudeventos.TemperatureEvent;
import logica.crudeventos.AdminEventos;

public class Aquecedor implements AquecedorListener {

    private Sensor sensor;

    public Aquecedor() {
        this.sensor = new Sensor();
    }

    @Override
    public void sensorReceived(AquecedorEvent event) {
        switch(event.getAction()) {
            case TURN_ON:
                sensor.increaseTemperature();
                break;
            case TURN_OFF:
                sensor.resetTemperature();
                break;
            case GET_STATUS:
                this.emitTemperature(event.getSource());
                break;
        }
    }

    private void emitTemperature(AdminEventos destination) {
        TemperatureEvent event = new TemperatureEvent(this, Action.TEMPERATURE, sensor.getTemperature());
        destination.temperatureReceived(event);
    }
}
