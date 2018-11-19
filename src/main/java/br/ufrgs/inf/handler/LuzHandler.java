package br.ufrgs.inf.handler;

import br.ufrgs.inf.data.LuzEvent;
import br.ufrgs.inf.equipment.Luz;
import br.ufrgs.inf.event.EventListener;
import br.ufrgs.inf.event.Queue;

public class LuzHandler implements EventListener<LuzEvent> {

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
