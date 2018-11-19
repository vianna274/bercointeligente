package br.ufrgs.inf.handler;

import br.ufrgs.inf.data.SomEvent;
import br.ufrgs.inf.equipment.Som;
import br.ufrgs.inf.event.EventListener;
import br.ufrgs.inf.event.Queue;

public class SomHandler implements EventListener<SomEvent> {

    private Som som;
    private Queue queue;

    public SomHandler(Som som, Queue queue) {
        this.som = som;
        this.queue = queue;
    }

    @Override
    public void onEvent(SomEvent event) {
        this.handleStartEvent(event);
    }

    public void handleEndEvent(SomEvent event) {
        this.som.toggle();
    }

    public void handleStartEvent(SomEvent event) {
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