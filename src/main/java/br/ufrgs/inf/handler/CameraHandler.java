package br.ufrgs.inf.handler;

import br.ufrgs.inf.data.*;
import br.ufrgs.inf.data.domain.*;
import br.ufrgs.inf.equipment.Camera;
import br.ufrgs.inf.event.EventListener;
import br.ufrgs.inf.event.Queue;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CameraHandler implements EventListener<CameraEvent> {

    private Camera camera;
    private Queue queue;

    public CameraHandler(Camera camera, Queue queue) {
        this.camera = camera;
        this.queue = queue;
    }

    @Override
    public void onEvent(CameraEvent event) {
        this.handleStartEvent(event);
    }

    private void handleBabyWokeUp() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plus(15, ChronoUnit.MINUTES);

        AquecedorEvent aquecedorEvent = new AquecedorEvent("", start, end, Temperature.AMBIENT, EquipmentStatus.ON);
        MobileEvent mobileEvent = new MobileEvent("", start, end, MobileSpeed.MEDIUM, EquipmentStatus.ON);
        SomEvent somEvent = new SomEvent("", start, end, MusicVolume.MEDIUM, Song.SECOND, EquipmentStatus.ON);
        LuzEvent luzEvent = new LuzEvent("", start, end, EquipmentStatus.ON);

        queue.enqueue(aquecedorEvent);
        queue.enqueue(mobileEvent);
        queue.enqueue(somEvent);
        queue.enqueue(luzEvent);
    }

    private void handleBabySleeping() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plus(15, ChronoUnit.MINUTES);

        AquecedorEvent aquecedorEvent = new AquecedorEvent("", start, end, Temperature.COLD, EquipmentStatus.OFF);
        MobileEvent mobileEvent = new MobileEvent("", start, end, MobileSpeed.SLOW, EquipmentStatus.ON);
        SomEvent somEvent = new SomEvent("", start, end, MusicVolume.LOW, Song.FIRST, EquipmentStatus.ON);
        LuzEvent luzEvent = new LuzEvent("", start, end, EquipmentStatus.OFF);

        queue.enqueue(aquecedorEvent);
        queue.enqueue(mobileEvent);
        queue.enqueue(somEvent);
        queue.enqueue(luzEvent);
    }

    public void handleStartEvent(CameraEvent event) {
        if (event.getBabyStatus() != null) {
            this.camera.setBabyStatus(event.getBabyStatus());
            this.handleBabyWokeUp();

            return;
        }

        if (event.getEquipmentStatus() != camera.getEquipmentStatus()) {
            camera.toggle();
        }

        if(event.getRecording() != null) {
            camera.recordingControl(event.getRecording());
        }

        if(event.getBabyStatus() == BabyStatus.AWAKE) {
            event.setRecording(Recording.ON);
            event.setEquipmentStatus(EquipmentStatus.ON);
            camera.recordingControl(event.getRecording());

        } else {
            event.setRecording(Recording.OFF);
            event.setEquipmentStatus(EquipmentStatus.OFF);
            camera.recordingControl(event.getRecording());
        }
    }

    public void handleEndEvent(CameraEvent event) {
        if (event.getBabyStatus() != null) {
            switch(event.getBabyStatus()) {
                case AWAKE:
                    System.out.println("Indo dormir");

                    this.camera.setBabyStatus(BabyStatus.SLEEPING);
                    this.handleBabySleeping();

                    break;

                case SLEEPING:
                    System.out.println("Acordando");

                    this.camera.setBabyStatus(BabyStatus.AWAKE);
                    this.handleBabyWokeUp();

                    break;
            }

        } else {
            this.camera.toggle();
        }
    }
}
