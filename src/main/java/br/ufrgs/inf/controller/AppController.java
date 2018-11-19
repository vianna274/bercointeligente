package br.ufrgs.inf.controller;

import br.ufrgs.inf.data.*;
import br.ufrgs.inf.data.domain.*;
import br.ufrgs.inf.equipment.BabyBottle;
import br.ufrgs.inf.event.Queue;
import br.ufrgs.inf.handler.Scheduler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


public class AppController {

    private final Queue queue;
    private final Scheduler scheduler;
    private final EquipmentService equipmentService;
    private BabyBottle babyBottle;
    private BabyStatus babyStatus;
    private EquipmentStatus ligthStatus;

    private List<Consumer<BabyBottle>> babyBottleListeners;

    public AppController(final Queue queue,
                         final Scheduler scheduler,
                         final EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
        this.queue = queue;
        this.scheduler = scheduler;
        this.babyStatus = BabyStatus.SLEEPING;
        this.babyBottle = new BabyBottle();
        this.babyBottleListeners = new ArrayList<>();
        this.ligthStatus = EquipmentStatus.OFF;
    }


    public void setBabyStatus(BabyStatus babyStatus) {
        this.babyStatus = babyStatus;
    }

    public BabyStatus getBabyStatus() {
        return babyStatus;
    }

    public void addBabyBottleListener(final Consumer<BabyBottle> consumer) {
        this.babyBottleListeners.add(consumer);
    }

    public void notifyBabyBottleListeners(final BabyBottle babyBottle) {
        this.babyBottleListeners.forEach(s -> s.accept(this.babyBottle));
    }

    public void toggleBabyStatus() {
        if(babyStatus.equals(BabyStatus.AWAKE)){
            this.babyStatus = BabyStatus.SLEEPING;
        }
        else{
            this.babyStatus =  BabyStatus.AWAKE;
        }

        this.babyBottle.toggleBabyBottleStatus();
       // this.ligthStatus
        this.notifyBabyBottleListeners(this.babyBottle);
    }

    public BabyBottle getBabyBottle() {
        return babyBottle;
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

    public EquipmentStatus getLigthStatus() {
        return ligthStatus;
    }

    public void setLigthStatus(EquipmentStatus ligthStatus) {
        this.ligthStatus = ligthStatus;
    }
}
