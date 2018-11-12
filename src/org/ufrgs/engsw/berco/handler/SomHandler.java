package org.ufrgs.engsw.berco.handler;

import org.ufrgs.engsw.berco.data.SomEvent;
import org.ufrgs.engsw.berco.equipment.Som;
import org.ufrgs.engsw.berco.event.EventListener;

public class SomHandler implements EventListener<SomEvent>{

    private Som som;

    public SomHandler(Som som) {
        this.som = som;
    }

    @Override
    public void onEvent(SomEvent event) {
        if(event.getEquipmentStatus() != som.getEquipmentStatus()){
            som.toggle();
        }
        if(event.getCurrentSong() !=  som.getCurrentSong()) {
            som.setCurrentSong(event.getCurrentSong());
        }
        if(event.getMusicVolume() != som.getMusicVolume()) {
            som.setMusicVolume(event.getMusicVolume());
        }
    }
}
