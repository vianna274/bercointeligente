package org.ufrgs.engsw.berco.handler;

import org.ufrgs.engsw.berco.data.MobileEvent;
import org.ufrgs.engsw.berco.equipment.Mobile;
import org.ufrgs.engsw.berco.event.EventListener;
import org.ufrgs.engsw.berco.event.Queue;

public class MobileHandler implements EventListener<MobileEvent>{

    private Mobile mobile;
    private Queue queue;

    public MobileHandler(Mobile mobile, Queue queue) {
        this.mobile = mobile;
        this.queue = queue;
    }

    @Override
    public void onEvent(MobileEvent event) {
        this.handleStartEvent(event);
    }

    public void handleEndEvent(MobileEvent event) {
        this.mobile.toggle();
    }

    public void handleStartEvent(MobileEvent event) {
        System.out.println(event.toString());
        if(event.getEquipmentStatus() != mobile.getEquipmentStatus()){
            System.out.println("Colocando como on");
            mobile.toggle();
        }
        if(event.getSpeed() !=  mobile.getSpeed()) {
            mobile.setSpeed(event.getSpeed());
        }
    }
}