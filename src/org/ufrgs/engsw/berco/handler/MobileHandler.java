package org.ufrgs.engsw.berco.handler;

import org.ufrgs.engsw.berco.data.MobileEvent;
import org.ufrgs.engsw.berco.equipment.Mobile;
import org.ufrgs.engsw.berco.event.EventListener;

public class MobileHandler implements EventListener<MobileEvent>{

    private Mobile mobile;

    public MobileHandler(Mobile mobile) {
        this.mobile = mobile;
    }

    @Override
    public void onEvent(MobileEvent event) {
        if(event.getEquipmentStatus() != mobile.getEquipmentStatus()){
            mobile.toggle();
        }
        if(event.getSpeed() !=  mobile.getSpeed()) {
            mobile.setSpeed(event.getSpeed());
        }
    }
}
