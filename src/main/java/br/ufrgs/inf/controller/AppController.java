package br.ufrgs.inf.controller;

import br.ufrgs.inf.data.*;
import br.ufrgs.inf.data.domain.*;
import br.ufrgs.inf.event.Queue;
import br.ufrgs.inf.handler.Scheduler;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


public class AppController {

    private final Queue queue;
    private final Scheduler scheduler;
    private final EquipmentService equipmentService;

    public AppController(final Queue queue,
                         final Scheduler scheduler,
                         final EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
        this.queue = queue;
        this.scheduler = scheduler;
    }

    public void wakeUpBaby() {
        final LocalDateTime now = LocalDateTime.now();

        final LocalDateTime end = now.plus(10, ChronoUnit.SECONDS);

        this.createCameraEvent("Wake up test", now, end, Recording.OFF, EquipmentStatus.ON);
        this.createCameraEvent("Wake up test", now, end, BabyStatus.AWAKE);
    }

    private String getBabyStatus() {
        return equipmentService.getBabyStatus();
    }

    public String createAquecedorEvent(String name, LocalDateTime begin, LocalDateTime end, EquipmentStatus status) throws Exception {
        AquecedorEvent event = new AquecedorEvent(name, begin, end, status);

        this.queue.enqueue(event);

        return event.getId();
    }

    public String createAquecedorEvent(String name, LocalDateTime begin, LocalDateTime end, Temperature temperature, EquipmentStatus status) {
        AquecedorEvent event = new AquecedorEvent(name, begin, end, temperature, status);

        this.queue.enqueue(event);

        return event.getId();
    }

    public String createCameraEvent(String name, LocalDateTime begin, LocalDateTime end, Recording recording, EquipmentStatus status) {
        CameraEvent event = new CameraEvent(name, begin, end, recording, status);

        this.queue.enqueue(event);

        return event.getId();
    }

    public String createCameraEvent(String name, LocalDateTime begin, LocalDateTime end, BabyStatus babyStatus) {
        CameraEvent event = new CameraEvent(name, begin, end, babyStatus);

        this.queue.enqueue(event);

        return event.getId();
    }

    public String createSomEvent(String name, LocalDateTime begin, LocalDateTime end, MusicVolume musicVolume, Song song, EquipmentStatus status) {
        SomEvent event = new SomEvent(name, begin, end, musicVolume, song, status);

        this.queue.enqueue(event);

        return event.getId();
    }

    public String createMobileEvent(String name, LocalDateTime begin, LocalDateTime end, MobileSpeed mobileSpeed, EquipmentStatus status) {
        MobileEvent event = new MobileEvent(name, begin, end, mobileSpeed, status);

        this.queue.enqueue(event);

        return event.getId();
    }

    public String createLuzEvent(String name, LocalDateTime begin, LocalDateTime end, EquipmentStatus status) {
        LuzEvent event = new LuzEvent(name, begin, end, status);

        this.queue.enqueue(event);

        return event.getId();
    }

    public String scheduleEvent(Event event) {
        this.scheduler.scheduleEvent(event);
        return event.getId();
    }

    public String replaceScheduled(Event event) {
        this.scheduler.replaceScheduled(event);
        return event.getId();
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
