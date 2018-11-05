package logica.crudeventos;

import events.aquecedor.Action;
import events.aquecedor.AquecedorEvent;
import interadoresfisicos.aquecedor.Aquecedor;

import java.util.Calendar;
import java.util.Date;

public class AdminEventos {
    private Aquecedor aquecedor;

    public AdminEventos(Aquecedor aquecedor) {
        this.aquecedor = aquecedor;
    }

    public void emitAquecedorTurnOn() {
        AquecedorEvent event = new AquecedorEvent(this, Action.TURN_ON);
        this.aquecedor.sensorReceived(event);
    }

    public void emitDummyEvents() {
        // SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date begin = this.addSecondsToJavaUtilDate(new Date(), 5);
        Date end = this.addSecondsToJavaUtilDate(new Date(), 10);
        AquecedorEvent event = new AquecedorEvent(this, Action.TURN_ON, begin, end);

        Date begin1 = this.addSecondsToJavaUtilDate(new Date(), 15);
        Date end1 = this.addSecondsToJavaUtilDate(new Date(), 20);
        AquecedorEvent event1 = new AquecedorEvent(this, Action.TURN_ON, begin1, end1);

        this.aquecedor.sensorReceived(event);
        this.aquecedor.sensorReceived(event1);

    }

    public Date addSecondsToJavaUtilDate(Date date, int seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, seconds);
        return calendar.getTime();
    }
}
