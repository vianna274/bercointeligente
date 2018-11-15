package org.ufrgs.engsw.berco.controller;

import org.ufrgs.engsw.berco.data.*;
import org.ufrgs.engsw.berco.data.domain.*;
import org.ufrgs.engsw.berco.event.Queue;
import org.ufrgs.engsw.berco.handler.Scheduler;

import java.time.LocalDateTime;

public class EventCRUD {

    private Queue queue;
    private Scheduler scheduler;

    public EventCRUD(Queue queue, Scheduler scheduler) {
        this.queue = queue;
        this.scheduler = scheduler;
    }

    public String createAquecedorEvent(LocalDateTime begin, LocalDateTime end,
                                       EquipmentStatus status) throws Exception {
        AquecedorEvent event = new AquecedorEvent(begin, end, status);

        this.queue.enqueue(event);

        return event.getId();
    }

    public String createAquecedorEvent(LocalDateTime begin, LocalDateTime end,
                                       Temperature temperature, EquipmentStatus status) {
        AquecedorEvent event = new AquecedorEvent(begin, end, temperature, status);

        this.queue.enqueue(event);

        return event.getId();
    }

    public String createCameraEvent(LocalDateTime begin, LocalDateTime end,
                                Recording recording, EquipmentStatus status) {
        CameraEvent event = new CameraEvent(begin, end, recording, status);

        this.queue.enqueue(event);

        return event.getId();
    }

    public String createCameraEvent(LocalDateTime begin, LocalDateTime end, BabyStatus babyStatus) {
        CameraEvent event = new CameraEvent(begin, end, babyStatus);

        this.queue.enqueue(event);

        return event.getId();
    }

    public String createSomEvent(LocalDateTime begin, LocalDateTime end,
                                 MusicVolume musicVolume, Song song, EquipmentStatus status) {
        SomEvent event = new SomEvent(begin, end, musicVolume, song, status);

        this.queue.enqueue(event);

        return event.getId();
    }

    public String createMobileEvent(LocalDateTime begin, LocalDateTime end,
                                    MobileSpeed mobileSpeed, EquipmentStatus status) {
        MobileEvent event = new MobileEvent(begin, end, mobileSpeed, status);

        this.queue.enqueue(event);

        return event.getId();
    }

    public String createLuzEvent(LocalDateTime begin, LocalDateTime end,
                                 EquipmentStatus status) {
        LuzEvent event = new LuzEvent(begin, end, status);

        this.queue.enqueue(event);

        return event.getId();
    }

    public String scheduleEvent(Event event) {
        this.scheduler.scheduleEvent(event);
        return event.id();
    }

    public String deleteScheduledEvent(String id) {
        this.scheduler.removeEvent(id);
        return  id;
    }

    public AquecedorEvent getAquecedorEvent(String id) {
        return (AquecedorEvent)queue.getEvent(id);
    }

    public CameraEvent getCameraEvent(String id) {
        return (CameraEvent)queue.getEvent(id);
    }
}