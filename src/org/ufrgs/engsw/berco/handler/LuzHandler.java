package org.ufrgs.engsw.berco.handler;

import org.ufrgs.engsw.berco.data.LuzEvent;
import org.ufrgs.engsw.berco.equipment.Luz;
import org.ufrgs.engsw.berco.event.EventListener;

public class LuzHandler implements EventListener<LuzEvent>{

    private Luz luz;

    public LuzHandler(Luz luz) {
        this.luz = luz;
    }

    @Override
    public void onEvent(LuzEvent event) {
        if(event.getEquipmentStatus() != luz.getEquipmentStatus()){
            luz.toggle();
        }
    }
}
