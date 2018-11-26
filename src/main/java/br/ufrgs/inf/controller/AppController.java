package br.ufrgs.inf.controller;

import br.ufrgs.inf.data.builders.*;
import br.ufrgs.inf.data.domain.*;
import br.ufrgs.inf.data.events.*;
import br.ufrgs.inf.equipment.BabyBottle;
import br.ufrgs.inf.event.Queue;

import java.time.LocalDateTime;


public class AppController {

    private final Queue queue;
    private final EquipmentService equipmentService;

    public AppController(final Queue queue,
                         final EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
        this.queue = queue;
    }

    public boolean isBabyAwake() {
        return this.equipmentService.getBabyStatus() != BabyStatus.SLEEPING;
    }

    /* POST */
    public void wakeUpBaby() {
        CameraEvent event = new CameraEventBuilder()
                .operation(Operation.ACTION)
                .eventName(EventName.BABY_WAKE_UP)
                .start(LocalDateTime.now())
                .build();

        this.queue.enqueue(event);
    }

    public void babySleep() {
        CameraEvent event = new CameraEventBuilder()
                .operation(Operation.ACTION)
                .eventName(EventName.BABY_SLEPT)
                .start(LocalDateTime.now())
                .build();

        this.queue.enqueue(event);
    }

    public String createAquecedorEvent(LocalDateTime begin, LocalDateTime end, EquipmentStatus status) {
        AquecedorEvent event = new AquecedorEventBuilder()
                .operation(Operation.POST)
                .start(begin)
                .end(end)
                .equipmentStatus(status)
                .build();

        this.queue.enqueue(event);

        return event.getId();
    }

    public String createAquecedorEvent(LocalDateTime begin, LocalDateTime end, Temperature temperature, EquipmentStatus status) {
        AquecedorEvent event = new AquecedorEventBuilder()
                .operation(Operation.POST)
                .start(begin)
                .end(end)
                .temperature(temperature)
                .equipmentStatus(status)
                .build();

        this.queue.enqueue(event);

        return event.getId();
    }

    public String createCameraEvent(LocalDateTime begin, LocalDateTime end, Recording recording, EquipmentStatus status) {
        CameraEvent event = new CameraEventBuilder()
                .operation(Operation.POST)
                .start(begin)
                .end(end)
                .recording(recording)
                .equipmentStatus(status)
                .build();

        this.queue.enqueue(event);

        return event.getId();
    }

    public String createCameraEvent(LocalDateTime begin, LocalDateTime end, BabyStatus babyStatus) {
        CameraEvent event = new CameraEventBuilder()
                .operation(Operation.POST)
                .start(begin)
                .end(end)
                .babyStatus(babyStatus)
                .build();

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

    /* PUT */

    public String createAquecedorEvent(String id, LocalDateTime begin, LocalDateTime end, EquipmentStatus status) {
        AquecedorEvent event = new AquecedorEventBuilder()
                .operation(Operation.PUT)
                .start(begin)
                .end(end)
                .equipmentStatus(status)
                .id(id)
                .build();

        this.queue.enqueue(event);

        return event.getId();
    }

    public String createAquecedorEvent(String id, LocalDateTime begin, LocalDateTime end, Temperature temperature, EquipmentStatus status) {
        AquecedorEvent event = new AquecedorEventBuilder()
                .operation(Operation.PUT)
                .start(begin)
                .end(end)
                .temperature(temperature)
                .equipmentStatus(status)
                .id(id)
                .build();

        this.queue.enqueue(event);

        return event.getId();
    }

    public String createCameraEvent(String id, LocalDateTime begin, LocalDateTime end, Recording recording, EquipmentStatus status) {
        CameraEvent event = new CameraEventBuilder()
                .operation(Operation.PUT)
                .start(begin)
                .end(end)
                .recording(recording)
                .equipmentStatus(status)
                .id(id)
                .build();

        this.queue.enqueue(event);

        return event.getId();
    }

    public String createCameraEvent(String id, LocalDateTime begin, LocalDateTime end, BabyStatus babyStatus) {
        CameraEvent event = new CameraEventBuilder()
                .operation(Operation.PUT)
                .start(begin)
                .end(end)
                .babyStatus(babyStatus)
                .id(id)
                .build();

        this.queue.enqueue(event);

        return event.getId();
    }

    public String createSomEvent(String id, LocalDateTime begin, LocalDateTime end, MusicVolume musicVolume, Song song, EquipmentStatus status) {
        SomEvent event = new SomEventBuilder()
                .operation(Operation.PUT)
                .start(begin)
                .end(end)
                .musicVolume(musicVolume)
                .song(song)
                .equipmentStatus(status)
                .id(id)
                .build();

        this.queue.enqueue(event);

        return event.getId();
    }

    public String createMobileEvent(String id, LocalDateTime begin, LocalDateTime end, MobileSpeed mobileSpeed, EquipmentStatus status) {
        MobileEvent event = new MobileEventBuilder()
                .operation(Operation.PUT)
                .start(begin)
                .end(end)
                .mobileSpeed(mobileSpeed)
                .equipmentStatus(status)
                .id(id)
                .build();

        this.queue.enqueue(event);

        return event.getId();
    }

    public String createLuzEvent(String id, LocalDateTime begin, LocalDateTime end, EquipmentStatus status) {
        LuzEvent event = new LuzEventBuilder()
                .operation(Operation.PUT)
                .start(begin)
                .end(end)
                .equipmentStatus(status)
                .id(id)
                .build();

        this.queue.enqueue(event);

        return event.getId();
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
}
