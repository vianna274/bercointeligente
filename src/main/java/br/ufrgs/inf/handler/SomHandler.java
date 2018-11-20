package br.ufrgs.inf.handler;

import br.ufrgs.inf.data.SomEvent;
import br.ufrgs.inf.data.domain.EquipmentStatus;
import br.ufrgs.inf.data.domain.EventName;
import br.ufrgs.inf.data.domain.MusicVolume;
import br.ufrgs.inf.data.domain.Song;
import br.ufrgs.inf.equipment.Som;
import br.ufrgs.inf.event.EventListener;
import br.ufrgs.inf.event.Queue;

public class SomHandler implements EventListener<SomEvent> {

    private Som som;
    private Queue queue;
    private Scheduler<SomEvent> scheduler;
    public SomHandler(Som som, Queue queue) {
        this.som = som;
        this.queue = queue;
        this.scheduler = new Scheduler<>();
        this.scheduler.setStartEventCallback(this::handleStartEvent);
        this.scheduler.setEndEventCallback(this::handleEndEvent);
        this.scheduler.setResumeEventCallback(this::handleResumeEvent);
        this.scheduler.setPauseEventCallback(this::handlePauseEvent);
    }

    @Override
    public void onEvent(SomEvent event) {
        this.scheduler.scheduleEvent(event);
    }

    public Integer handlePauseEvent(SomEvent event) {
        System.out.println("[Som Handler] : Pause");
        this.handleEndEvent(event);
        return 0;
    }

    public Integer handleResumeEvent(SomEvent event) {
        System.out.println("[Som Handler] : Resume");
        this.handleStartEvent(event);
        return 0;
    }

    public Integer handleEndEvent(SomEvent event) {
        this.som.toggle();
        return 0;
    }

    public Integer handleStartEvent(SomEvent event) {
        if (event.getName() == EventName.BABY_TIRED) {
            System.out.println("[Som Handler] : BABY_TIRED");
            som.turnOn();
            som.setMusicVolume(MusicVolume.LOW);
            som.setCurrentSong(Song.FIRST);
            return 0;
        }
        if (event.getName() == EventName.BABY_SLEPT) {
            System.out.println("[Som Handler] : BABY_SLEPT");
            som.turnOff();
            return 0;
        }
        if (event.getName() == EventName.BABY_MOVING) {
            System.out.println("[Som Handler] : BABY_MOVING");
            som.turnOn();
            som.setMusicVolume(MusicVolume.MEDIUM);
            som.setCurrentSong(Song.SECOND);
            return 0;
        }

        if(event.getName() != null) return 0; // Descartar eventos com nome que não foram tratados


        if(event.getEquipmentStatus() != som.getEquipmentStatus()){
            som.toggle();
        }

        if(event.getCurrentSong() !=  som.getCurrentSong()) {
            som.setCurrentSong(event.getCurrentSong());
        }

        if(event.getMusicVolume() != som.getMusicVolume()) {
            som.setMusicVolume(event.getMusicVolume());
        }
        return 0;
    }
}
