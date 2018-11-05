package interadoresfisicos.aquecedor;

import events.aquecedor.AquecedorEvent;
import events.aquecedor.AquecedorListener;
import events.aquecedor.TemperatureEvent;
import logica.displaystatus.DisplayStatus;

import java.util.ArrayList;
import java.util.Date;

public class Aquecedor implements AquecedorListener {

    private Sensor sensor;
    private ArrayList<AquecedorEvent> eventos;
    private AquecedorEvent currentEvento;

    public Aquecedor() {
        this.sensor = new Sensor();
        this.eventos = new ArrayList<>();
        this.currentEvento = null;
        new Thread(this::eventosManager).start();
    }

    private void isAnyEventoBegun() {
        Date currentDate = new Date();
        for(AquecedorEvent event : this.eventos) {
            if (event.getBegin().compareTo(currentDate) <= 0) {
                this.currentEvento = event;
                this.eventos.remove(event);
                this.handleAction(this.currentEvento);
                break;
            }
        }
    }

    private void isCurrentEventoEnded() {
        Date currentDate = new Date();
        if (this.currentEvento.getEnd().compareTo(currentDate) <= 0) {
            this.currentEvento = null;
            this.sensor.resetTemperature();
        }
    }

    private void eventosManager() {
        while(true) {
            if (this.currentEvento != null) {
                this.isCurrentEventoEnded();
            }
            isAnyEventoBegun();
            try {
                Thread.sleep(200);
            } catch (Exception e) {}
        }
    }

    private void schedule(AquecedorEvent event) {
        this.eventos.add(event);
    }

    private void handleAction(AquecedorEvent event) {
        switch(event.getAction()) {
            case TURN_ON:
                sensor.increaseTemperature();
                break;
            case TURN_OFF:
                sensor.resetTemperature();
                break;
            case GET_STATUS:
                this.emitTemperature(event.getDisplayStatus());
                break;
        }
    }

    @Override
    public void sensorReceived(AquecedorEvent event) {
        if (event.getBegin() != null && event.getEnd() != null)
            this.schedule(event);
        else
            this.handleAction(event);

    }

    private void emitTemperature(DisplayStatus destination) {
        TemperatureEvent event = new TemperatureEvent(this, sensor.getTemperature());
        destination.milkStatus(event);
    }
}
