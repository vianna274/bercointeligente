package br.ufrgs.inf.handler;

import br.ufrgs.inf.data.builders.SomEventBuilder;
import br.ufrgs.inf.data.domain.*;
import br.ufrgs.inf.data.events.SomEvent;
import br.ufrgs.inf.equipment.Som;
import br.ufrgs.inf.event.EventListener;
import br.ufrgs.inf.event.Queue;

public class SomHandler implements EventListener<SomEvent> {

    private Som som;
    private Queue queue;
    private Queue uiQueue;
    private Scheduler<SomEvent> scheduler;

    public SomHandler(Som som, Queue queue, Queue uiQueue) {
        this.som = som;
        this.queue = queue;
        this.uiQueue = uiQueue;
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

    public void sendUiQueue(SomEvent event) {
        this.uiQueue.enqueue(event);
    }

    public Integer handlePauseEvent(SomEvent event) {
        System.out.println("[Som Handler] : Pause");
        this.som.turnOff();

        sendUiQueue(new SomEventBuilder()
                .operation(Operation.STATUS_CHANGED)
                .equipmentStatus(som.getEquipmentStatus())
                .id(event.getId())
                .build());
        return 0;
    }

    public Integer handleResumeEvent(SomEvent event) {
        System.out.println("[Som Handler] : Resume");
        this.som.turnOn();

        sendUiQueue(new SomEventBuilder()
                .operation(Operation.STATUS_CHANGED)
                .equipmentStatus(som.getEquipmentStatus())
                .id(event.getId())
                .build());
        return 0;
    }

    public Integer handleEndEvent(SomEvent event) {
        this.som.turnOff();

        sendUiQueue(new SomEventBuilder()
                .operation(Operation.STATUS_CHANGED)
                .equipmentStatus(som.getEquipmentStatus())
                .id(event.getId())
                .build());
        return 0;
    }

    public Integer handleStartEvent(SomEvent event) {
        SomEventBuilder somEventBuilder = new SomEventBuilder();

        if (event.getName() == EventName.BABY_TIRED) {
            System.out.println("[Som Handler] : BABY_TIRED");
            som.turnOn();
            som.setMusicVolume(MusicVolume.LOW);
            som.setCurrentSong(Song.FIRST);

            somEventBuilder
                    .operation(Operation.STATUS_CHANGED)
                    .musicVolume(MusicVolume.LOW)
                    .song(Song.FIRST)
                    .id(event.getId())
                    .equipmentStatus(EquipmentStatus.ON);
        } else if (event.getName() == EventName.BABY_SLEPT) {
            System.out.println("[Som Handler] : BABY_SLEPT");
            som.turnOff();

            somEventBuilder
                    .operation(Operation.STATUS_CHANGED)
                    .id(event.getId())
                    .equipmentStatus(EquipmentStatus.OFF);
        } else if (event.getName() == EventName.BABY_MOVING) {
            System.out.println("[Som Handler] : BABY_MOVING");
            som.turnOn();
            som.setMusicVolume(MusicVolume.MEDIUM);
            som.setCurrentSong(Song.SECOND);

            somEventBuilder
                    .operation(Operation.STATUS_CHANGED)
                    .musicVolume(MusicVolume.MEDIUM)
                    .song(Song.SECOND)
                    .id(event.getId())
                    .equipmentStatus(EquipmentStatus.ON);
        } else if (event.getName() != null) return 0; // Descartar eventos com nome que não foram tratados


        if (event.getEquipmentStatus() != som.getEquipmentStatus()) {
            som.toggle();
            somEventBuilder
                    .operation(Operation.STATUS_CHANGED)
                    .id(event.getId())
                    .equipmentStatus(som.getEquipmentStatus());
        }

        if (event.getCurrentSong() != som.getCurrentSong()) {
            som.setCurrentSong(event.getCurrentSong());
            somEventBuilder
                    .operation(Operation.STATUS_CHANGED)
                    .id(event.getId())
                    .song(som.getCurrentSong());
        }

        if (event.getMusicVolume() != som.getMusicVolume()) {
            som.setMusicVolume(event.getMusicVolume());
            somEventBuilder
                    .operation(Operation.STATUS_CHANGED)
                    .id(event.getId())
                    .musicVolume(som.getMusicVolume());
        }

        this.sendUiQueue(somEventBuilder.build());

        return 0;
    }
}
