package org.ufrgs.engsw.berco.handler;

import org.ufrgs.engsw.berco.data.LuzEvent;
import org.ufrgs.engsw.berco.equipment.Luz;
import org.ufrgs.engsw.berco.event.EventListener;
import org.ufrgs.engsw.berco.event.Queue;

public class LuzHandler implements EventListener<LuzEvent>{

    private Luz luz;
    private Queue queue;

    public LuzHandler(Luz luz, Queue queue) {
        this.luz = luz;
        this.queue = queue;
    }

    @Override
    public void onEvent(LuzEvent event) {
        this.handleStartEvent(event);
    }

    public void handleEndEvent(LuzEvent event) {
        this.luz.toggle();
    }

    public void handleStartEvent(LuzEvent event) {
        if(event.getEquipmentStatus() != luz.getEquipmentStatus()){
            luz.toggle();
        }
    }
}
