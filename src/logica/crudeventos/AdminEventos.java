package logica.crudeventos;

import events.aquecedor.Action;
import events.aquecedor.AquecedorEvent;
import events.crudeventos.TemperatureEvent;
import interadoresfisicos.aquecedor.Aquecedor;

public class AdminEventos {
    private Aquecedor aquecedor;

    public AdminEventos() {
        this.aquecedor = new Aquecedor();
        this.emitAquecedorGetTemperature();
        this.turnOn();
        this.emitAquecedorGetTemperature();
    }

    public void turnOn() {
        this.emitAquecedorTurnOn();
    }


    public void emitAquecedorTurnOn() {
        AquecedorEvent event = new AquecedorEvent(this, Action.TURN_ON);
        this.aquecedor.sensorReceived(event);
    }

    public void emitAquecedorGetTemperature() {
        AquecedorEvent event = new AquecedorEvent(this, Action.GET_STATUS);
        this.aquecedor.sensorReceived(event);
    }

    public void temperatureReceived(TemperatureEvent event) {
        System.out.println("Receiving Temperature: " + event.getTemperature());
    }
}
