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
    private Scheduler<CameraEvent> scheduler;

    public CameraHandler(Camera camera, Queue queue) {
        this.camera = camera;
        this.queue = queue;
        this.scheduler = new Scheduler<>();
        this.scheduler.setStartEventCallback(this::handleStartEvent);
        this.scheduler.setEndEventCallback(this::handleEndEvent);
        this.scheduler.setResumeEventCallback(this::handleResumeEvent);
        this.scheduler.setPauseEventCallback(this::handlePauseEvent);
    }

    @Override
    public void onEvent(CameraEvent event) {
        this.handleStartEvent(event);
    }

    public Integer handlePauseEvent(CameraEvent event) {
        System.out.println("[Camera Handler] : Pause");
        this.handleEndEvent(event);
        return 0;
    }

    public Integer handleResumeEvent(CameraEvent event) {
        System.out.println("[Camera Handler] : Resume");
        this.handleStartEvent(event);
        return 0;
    }

    private void handleBabyWokeUp() {
        LocalDateTime start = LocalDateTime.now();

        AquecedorEvent aquecedorEvent = new AquecedorEvent(Operation.ACTION, EventName.BABY_WAKE_UP, start);
        MobileEvent mobileEvent = new MobileEvent(Operation.ACTION, EventName.BABY_WAKE_UP, start);
        SomEvent somEvent = new SomEvent(Operation.ACTION, EventName.BABY_WAKE_UP, start);
        LuzEvent luzEvent = new LuzEvent(Operation.ACTION, EventName.BABY_WAKE_UP, start);

        queue.enqueue(aquecedorEvent);
        queue.enqueue(mobileEvent);
        queue.enqueue(somEvent);
        queue.enqueue(luzEvent);
    }

    private void handleBabySleeping() {
        LocalDateTime start = LocalDateTime.now();

        AquecedorEvent aquecedorEvent = new AquecedorEvent(Operation.ACTION, EventName.BABY_SLEPT, start);
        MobileEvent mobileEvent = new MobileEvent(Operation.ACTION, EventName.BABY_SLEPT, start);
        SomEvent somEvent = new SomEvent(Operation.ACTION, EventName.BABY_SLEPT, start);
        LuzEvent luzEvent = new LuzEvent(Operation.ACTION, EventName.BABY_SLEPT, start);

        queue.enqueue(aquecedorEvent);
        queue.enqueue(mobileEvent);
        queue.enqueue(somEvent);
        queue.enqueue(luzEvent);
    }

    public Integer handleStartEvent(CameraEvent event) {
        if (event.getName() == EventName.BABY_WAKE_UP) {
            System.out.println("[Camera Handler] : BABY_WAKE_UP");
            this.camera.setBabyStatus(event.getBabyStatus());
            this.handleBabyWokeUp();
            return 0;
        }

        if (event.getName() == EventName.BABY_SLEPT) {
            System.out.println("[Camera Handler] : BABY_SLEPT");
            this.camera.setBabyStatus(BabyStatus.SLEEPING);
            this.handleBabySleeping();
            return 0;
        }

        if(event.getName() != null) return 0; // Descartar eventos com nome que não foram tratados

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
        return 0;
    }

    public Integer handleEndEvent(CameraEvent event) {
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
        return 0;
    }
}
