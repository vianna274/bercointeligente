package br.ufrgs.inf.handler;

import br.ufrgs.inf.data.MobileEvent;
import br.ufrgs.inf.data.domain.EquipmentStatus;
import br.ufrgs.inf.data.domain.EventName;
import br.ufrgs.inf.data.domain.MobileSpeed;
import br.ufrgs.inf.equipment.Mobile;
import br.ufrgs.inf.event.EventListener;
import br.ufrgs.inf.event.Queue;

public class MobileHandler implements EventListener<MobileEvent> {

    private Mobile mobile;
    private Queue queue;
    private Scheduler<MobileEvent> scheduler;

    public MobileHandler(Mobile mobile, Queue queue) {
        this.mobile = mobile;
        this.queue = queue;
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
        return 0;
    }

    public Integer handleStartEvent(MobileEvent event) {
        if (event.getName() == EventName.BABY_TIRED) {
            System.out.println("[Mobile Handler] : BABY_TIRED");
            mobile.turnOn();
            mobile.setSpeed(MobileSpeed.SLOW);
            return 0;
        }
        if (event.getName() == EventName.BABY_SLEPT) {
            System.out.println("[Mobile Handler] : BABY_SLEPT");
            mobile.turnOff();
            return 0;
        }
        if (event.getName() == EventName.BABY_MOVING) {
            System.out.println("[Mobile Handler] : BABY_MOVING");
            mobile.turnOn();
            mobile.setSpeed(MobileSpeed.FAST);
        }

        if(event.getName() != null) return 0; // Descartar eventos com nome que não foram tratados

        if(event.getEquipmentStatus() != mobile.getEquipmentStatus()){
            mobile.toggle();
        }
        if(event.getSpeed() !=  mobile.getSpeed()) {
            mobile.setSpeed(event.getSpeed());
        }
        return 0;
    }
}
