package br.ufrgs.inf.handler;

import br.ufrgs.inf.data.builders.MobileEventBuilder;
import br.ufrgs.inf.data.domain.Operation;
import br.ufrgs.inf.data.events.MobileEvent;
import br.ufrgs.inf.data.domain.EventName;
import br.ufrgs.inf.data.domain.MobileSpeed;
import br.ufrgs.inf.equipment.Mobile;
import br.ufrgs.inf.event.EventListener;
import br.ufrgs.inf.event.Queue;

public class MobileHandler implements EventListener<MobileEvent> {

    private Mobile mobile;
    private Queue queue;
    private Queue uiQueue;
    private Scheduler<MobileEvent> scheduler;

    public MobileHandler(Mobile mobile, Queue queue, Queue uiQueue) {
        this.mobile = mobile;
        this.queue = queue;
        this.uiQueue = uiQueue;
        this.scheduler = new Scheduler<>();
        this.scheduler.setStartEventCallback(this::handleStartEvent);
        this.scheduler.setEndEventCallback(this::handleEndEvent);
        this.scheduler.setResumeEventCallback(this::handleResumeEvent);
        this.scheduler.setPauseEventCallback(this::handlePauseEvent);
    }

    @Override
    public void onEvent(MobileEvent event) {
        this.scheduler.scheduleEvent(event);
    }

    public void sendUiQueue(MobileEvent event) {
        this.uiQueue.enqueue(event);
    }

    public Integer handlePauseEvent(MobileEvent event) {
        System.out.println("[Mobile Handler] : Pause");
        this.handleEndEvent(event);
        return 0;
    }

    public Integer handleResumeEvent(MobileEvent event) {
        System.out.println("[Mobile Handler] : Resume");
        this.handleStartEvent(event);
        return 0;
    }

    public Integer handleEndEvent(MobileEvent event) {
        this.mobile.toggle();
        this.sendUiQueue(new MobileEventBuilder()
                .operation(Operation.STATUS_CHANGED)
                .equipmentStatus(this.mobile.getEquipmentStatus())
                .build());
        return 0;
    }

    public Integer handleStartEvent(MobileEvent event) {
        MobileEventBuilder eventBuilder = new MobileEventBuilder();

        if (event.getName() == EventName.BABY_TIRED) {
            System.out.println("[Mobile Handler] : BABY_TIRED");
            mobile.turnOn();
            mobile.setSpeed(MobileSpeed.SLOW);
            eventBuilder
                    .operation(Operation.STATUS_CHANGED)
                    .equipmentStatus(mobile.getEquipmentStatus())
                    .mobileSpeed(mobile.getSpeed());
        } else if (event.getName() == EventName.BABY_SLEPT) {
            System.out.println("[Mobile Handler] : BABY_SLEPT");
            mobile.turnOff();
            eventBuilder
                    .operation(Operation.STATUS_CHANGED)
                    .equipmentStatus(mobile.getEquipmentStatus());
        } else if (event.getName() == EventName.BABY_MOVING) {
            System.out.println("[Mobile Handler] : BABY_MOVING");
            mobile.turnOn();
            mobile.setSpeed(MobileSpeed.FAST);
            eventBuilder
                    .operation(Operation.STATUS_CHANGED)
                    .equipmentStatus(mobile.getEquipmentStatus())
                    .mobileSpeed(mobile.getSpeed());
        } else if (event.getName() != null) return 0; // Descartar eventos com nome que não foram tratados

        if (event.getEquipmentStatus() != mobile.getEquipmentStatus()) {
            mobile.toggle();
            eventBuilder
                    .operation(Operation.STATUS_CHANGED)
                    .equipmentStatus(mobile.getEquipmentStatus());
        }
        if (event.getSpeed() != mobile.getSpeed()) {
            mobile.setSpeed(event.getSpeed());
            eventBuilder
                    .operation(Operation.STATUS_CHANGED)
                    .mobileSpeed(mobile.getSpeed());
        }

        sendUiQueue(eventBuilder.build());

        return 0;
    }
}
