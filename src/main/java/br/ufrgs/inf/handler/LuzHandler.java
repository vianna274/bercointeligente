package br.ufrgs.inf.handler;

import br.ufrgs.inf.data.LuzEvent;
import br.ufrgs.inf.data.domain.EquipmentStatus;
import br.ufrgs.inf.data.domain.EventName;
import br.ufrgs.inf.equipment.Luz;
import br.ufrgs.inf.event.EventListener;
import br.ufrgs.inf.event.Queue;

public class LuzHandler implements EventListener<LuzEvent> {

    private Luz luz;
    private Queue queue;
    private Scheduler<LuzEvent> scheduler;

    public LuzHandler(Luz luz, Queue queue) {
        this.luz = luz;
        this.queue = queue;
        this.scheduler = new Scheduler<>();
        this.scheduler.setStartEventCallback(this::handleStartEvent);
        this.scheduler.setEndEventCallback(this::handleEndEvent);
        this.scheduler.setResumeEventCallback(this::handleResumeEvent);
        this.scheduler.setPauseEventCallback(this::handlePauseEvent);
    }

    @Override
    public void onEvent(LuzEvent event) {
        this.scheduler.scheduleEvent(event);
    }

    public Integer handlePauseEvent(LuzEvent event) {
        System.out.println("[Luz Handler] : Pause");
        this.handleEndEvent(event);
        return 0;
    }

    public Integer handleResumeEvent(LuzEvent event) {
        System.out.println("[Luz Handler] : Resume");
        this.handleStartEvent(event);
        return 0;
    }

    public Integer handleEndEvent(LuzEvent event) {
        this.luz.toggle();
        return 0;
    }

    public Integer handleStartEvent(LuzEvent event) {
        if (event.getName() == EventName.BABY_TIRED) {
            System.out.println("[Luz Handler] : BABY_TIRED");
            // TODO luz ficar mais fraca
            return 0;
        }
        if (event.getName() == EventName.BABY_MOVING ) {
            System.out.println("[Luz Handler] : BABY_MOVING");
            luz.turnOn();
            return 0;
        }
        if (event.getName() == EventName.BABY_SLEPT) {
            System.out.println("[Luz Handler] : BABY_SLEPT");
            luz.turnOff();
            return 0;
        }

        if(event.getName() != null) return 0; // Descartar eventos com nome que não foram tratados

        if(event.getEquipmentStatus() != luz.getEquipmentStatus()){
            luz.toggle();
        }
        return 0;
    }
}
