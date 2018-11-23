package br.ufrgs.inf.handler;

import br.ufrgs.inf.data.builders.LuzEventBuilder;
import br.ufrgs.inf.data.domain.EquipmentStatus;
import br.ufrgs.inf.data.domain.Operation;
import br.ufrgs.inf.data.events.LuzEvent;
import br.ufrgs.inf.data.domain.EventName;
import br.ufrgs.inf.equipment.Luz;
import br.ufrgs.inf.event.EventListener;
import br.ufrgs.inf.event.Queue;

public class LuzHandler implements EventListener<LuzEvent> {

    private Luz luz;
    private Queue queue;
    private Queue uiQueue;
    private Scheduler<LuzEvent> scheduler;

    public LuzHandler(Luz luz, Queue queue, Queue uiQueue) {
        this.luz = luz;
        this.queue = queue;
        this.uiQueue = uiQueue;
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
        this.sendUiQueue(new LuzEventBuilder()
                .operation(Operation.STATUS_CHANGED)
                .equipmentStatus(luz.getEquipmentStatus())
                .build());
        return 0;
    }

    public void sendUiQueue(LuzEvent event) {
        this.uiQueue.enqueue(event);
    }

    public Integer handleStartEvent(LuzEvent event) {
        LuzEventBuilder eventBuilder = new LuzEventBuilder();

        if (event.getName() == EventName.BABY_TIRED) {
            System.out.println("[Luz Handler] : BABY_TIRED");
            // TODO luz ficar mais fraca
        } else if (event.getName() == EventName.BABY_MOVING) {
            System.out.println("[Luz Handler] : BABY_MOVING");
            luz.turnOn();
            eventBuilder
                    .operation(Operation.STATUS_CHANGED)
                    .equipmentStatus(luz.getEquipmentStatus());
        } else if (event.getName() == EventName.BABY_SLEPT) {
            System.out.println("[Luz Handler] : BABY_SLEPT");
            luz.turnOff();
            eventBuilder
                    .operation(Operation.STATUS_CHANGED)
                    .equipmentStatus(luz.getEquipmentStatus());
        } else if (event.getName() != null) return 0; // Descartar eventos com nome que não foram tratados

        if (event.getEquipmentStatus() != luz.getEquipmentStatus()) {
            luz.toggle();
            eventBuilder
                    .operation(Operation.STATUS_CHANGED)
                    .equipmentStatus(luz.getEquipmentStatus());
        }

        this.sendUiQueue(eventBuilder.build());

        return 0;
    }
}
