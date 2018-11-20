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
    private final EquipmentService equipmentService;
    private BabyBottle babyBottle;
    private BabyStatus babyStatus;
    private EquipmentStatus ligthStatus;

    private List<Consumer<BabyBottle>> babyBottleListeners;

    public AppController(final Queue queue,
                         final EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
        this.queue = queue;
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

    /* POST */

    public void wakeUpBaby() {
        CameraEvent event = new CameraEvent(Operation.ACTION, EventName.BABY_WAKE_UP, LocalDateTime.now());

        this.queue.enqueue(event);
    }

    public void babySleep() {
        CameraEvent event = new CameraEvent(Operation.ACTION, EventName.BABY_SLEPT, LocalDateTime.now());

        this.queue.enqueue(event);
    }

    public String createAquecedorEvent(LocalDateTime begin, LocalDateTime end, EquipmentStatus status) throws Exception {
        AquecedorEvent event = new AquecedorEvent(Operation.POST, null, begin, end, status);

        this.queue.enqueue(event);

        return event.getId();
    }

    public String createAquecedorEvent(LocalDateTime begin, LocalDateTime end, Temperature temperature, EquipmentStatus status) {
        AquecedorEvent event = new AquecedorEvent(Operation.POST, null, begin, end, temperature, status);

        this.queue.enqueue(event);

        return event.getId();
    }

    public String createCameraEvent(LocalDateTime begin, LocalDateTime end, Recording recording, EquipmentStatus status) {
        CameraEvent event = new CameraEvent(Operation.POST, null, begin, end, recording, status);

        this.queue.enqueue(event);

        return event.getId();
    }

    public String createCameraEvent(LocalDateTime begin, LocalDateTime end, BabyStatus babyStatus) {
        CameraEvent event = new CameraEvent(Operation.POST, null, begin, end, babyStatus);

        this.queue.enqueue(event);

        return event.getId();
    }

    public String createSomEvent(LocalDateTime begin, LocalDateTime end, MusicVolume musicVolume, Song song, EquipmentStatus status) {
        SomEvent event = new SomEvent(Operation.POST, null, begin, end, musicVolume, song, status);

        this.queue.enqueue(event);

        return event.getId();
    }

    public String createMobileEvent(LocalDateTime begin, LocalDateTime end, MobileSpeed mobileSpeed, EquipmentStatus status) {
        MobileEvent event = new MobileEvent(Operation.POST, null, begin, end, mobileSpeed, status);

        this.queue.enqueue(event);

        return event.getId();
    }

    public String createLuzEvent(LocalDateTime begin, LocalDateTime end, EquipmentStatus status) {
        LuzEvent event = new LuzEvent(Operation.POST, null, begin, end, status);

        this.queue.enqueue(event);

        return event.getId();
    }

    /* DELETE */

    public void deleteLuzEvent(String id) {
        LuzEvent event = new LuzEvent(Operation.DELETE, id);
    }

    public void deleteAquecedorEvent(String id) {
        AquecedorEvent event = new AquecedorEvent(Operation.DELETE, id);
    }

    public void deleteMobileEvent(String id) {
        MobileEvent event = new MobileEvent(Operation.DELETE, id);
    }

    public void deleteSomEvent(String id) {
        SomEvent event = new SomEvent(Operation.DELETE, id);
    }

    public void deleteCameraEvent(String id) {
        CameraEvent event = new CameraEvent(Operation.DELETE, id);
    }

    /* PAUSE */

    public void pauseLuzEvent(String id) {
        LuzEvent event = new LuzEvent(Operation.PAUSE, id);
    }

    public void pauseAquecedorEvent(String id) {
        AquecedorEvent event = new AquecedorEvent(Operation.PAUSE, id);
    }

    public void pauseMobileEvent(String id) {
        MobileEvent event = new MobileEvent(Operation.PAUSE, id);
    }

    public void pauseSomEvent(String id) {
        SomEvent event = new SomEvent(Operation.PAUSE, id);
    }

    public void pauseCameraEvent(String id) {
        CameraEvent event = new CameraEvent(Operation.PAUSE, id);
    }

    /* RESUME */

    public void resumeLuzEvent(String id) {
        LuzEvent event = new LuzEvent(Operation.RESUME, id);
    }

    public void resumeAquecedorEvent(String id) {
        AquecedorEvent event = new AquecedorEvent(Operation.RESUME, id);
    }

    public void resumeMobileEvent(String id) {
        MobileEvent event = new MobileEvent(Operation.RESUME, id);
    }

    public void resumeSomEvent(String id) {
        SomEvent event = new SomEvent(Operation.RESUME, id);
    }

    public void resumeCameraEvent(String id) {
        CameraEvent event = new CameraEvent(Operation.RESUME, id);
    }

    public EquipmentStatus getLigthStatus() {
        return ligthStatus;
    }

    public void setLigthStatus(EquipmentStatus ligthStatus) {
        this.ligthStatus = ligthStatus;
    }
}
