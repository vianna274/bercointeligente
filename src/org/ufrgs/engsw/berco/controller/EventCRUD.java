package org.ufrgs.engsw.berco.controller;

import org.ufrgs.engsw.berco.data.*;
import org.ufrgs.engsw.berco.data.domain.*;
import org.ufrgs.engsw.berco.event.Queue;

import java.time.LocalDateTime;

public class EventCRUD {
    private Queue queue;

    public EventCRUD(Queue queue) {
        this.queue = queue;
    }

    public String createAquecedorEvent(LocalDateTime begin, LocalDateTime end,
                                   EquipmentStatus status) throws Exception {
        AquecedorEvent event = new AquecedorEvent(end, status);
        this.queue.enqueue(event);
        return event.getId();
    }

    public String createAquecedorEvent(LocalDateTime begin, LocalDateTime end,
                                       Temperature temperature, EquipmentStatus status) throws Exception {
        AquecedorEvent event = new AquecedorEvent(end, temperature, status);
        this.queue.enqueue(event);
        return event.getId();
    }

    public String createCameraEvent(LocalDateTime begin, LocalDateTime end,
                                Recording recording, EquipmentStatus status) {
        CameraEvent event = new CameraEvent(end, recording, status);
        this.queue.enqueue(event);
        return event.getId();
    }

    public String createSomEvent(LocalDateTime begin, LocalDateTime end,
                                 MusicVolume musicVolume, Song song, EquipmentStatus status) {
        SomEvent event = new SomEvent(end, musicVolume, song, status);
        this.queue.enqueue(event);
        return event.getId();
    }

    public String createMobileEvent(LocalDateTime begin, LocalDateTime end,
                                    MobileSpeed mobileSpeed, EquipmentStatus status) {
        MobileEvent event = new MobileEvent(end, mobileSpeed, status);
        this.queue.enqueue(event);
        return event.getId();
    }

    public String createLuzEvent(LocalDateTime begin, LocalDateTime end,
                                 EquipmentStatus status) {
        LuzEvent event = new LuzEvent(end, status);
        this.queue.enqueue(event);
        return event.getId();
    }

    public AquecedorEvent getAquecedorEvent(String id) {
        return (AquecedorEvent)queue.getEvent(id);
    }

    public CameraEvent getCameraEvent(String id) {
        return (CameraEvent)queue.getEvent(id);
    }
}
