package br.ufrgs.inf.handler;

import br.ufrgs.inf.data.builders.*;
import br.ufrgs.inf.data.domain.*;
import br.ufrgs.inf.data.events.*;
import br.ufrgs.inf.equipment.Camera;
import br.ufrgs.inf.event.EventListener;
import br.ufrgs.inf.event.Queue;

import java.time.LocalDateTime;

public class CameraHandler implements EventListener<CameraEvent> {

    private Camera camera;
    private Queue queue;
    private Queue uiQueue;
    private Scheduler<CameraEvent> scheduler;

    public CameraHandler(Camera camera, Queue queue, Queue uiQueue) {
        this.camera = camera;
        this.queue = queue;
        this.uiQueue = uiQueue;
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
        this.camera.turnOff();

        sendUiQueue(new CameraEventBuilder()
                .operation(Operation.STATUS_CHANGED)
                .equipmentStatus(camera.getEquipmentStatus())
                .id(event.getId())
                .build());
        return 0;
    }

    public Integer handleResumeEvent(CameraEvent event) {
        System.out.println("[Camera Handler] : Resume");
        this.camera.turnOn();

        sendUiQueue(new CameraEventBuilder()
                .operation(Operation.STATUS_CHANGED)
                .equipmentStatus(camera.getEquipmentStatus())
                .id(event.getId())
                .build());
        return 0;
    }

    public void sendUiQueue(CameraEvent event) {
        this.uiQueue.enqueue(event);
    }

    private void handleBabyWokeUp() {
        LocalDateTime start = LocalDateTime.now();

        AquecedorEvent aquecedorEvent = new AquecedorEventBuilder()
                .operation(Operation.ACTION)
                .eventName(EventName.BABY_WAKE_UP)
                .start(start)
                .build();

        MobileEvent mobileEvent = new MobileEventBuilder()
                .operation(Operation.ACTION)
                .eventName(EventName.BABY_WAKE_UP)
                .start(start)
                .build();
        SomEvent somEvent = new SomEventBuilder()
                .operation(Operation.ACTION)
                .eventName(EventName.BABY_WAKE_UP)
                .start(start)
                .build();
        LuzEvent luzEvent = new LuzEventBuilder()
                .operation(Operation.ACTION)
                .eventName(EventName.BABY_WAKE_UP)
                .start(start)
                .build();

        queue.enqueue(aquecedorEvent);
        queue.enqueue(mobileEvent);
        queue.enqueue(somEvent);
        queue.enqueue(luzEvent);
    }

    private void handleBabySleeping() {
        LocalDateTime start = LocalDateTime.now();

        AquecedorEvent aquecedorEvent = new AquecedorEventBuilder()
                .operation(Operation.ACTION)
                .eventName(EventName.BABY_SLEPT)
                .start(start)
                .build();

        MobileEvent mobileEvent = new MobileEventBuilder()
                .operation(Operation.ACTION)
                .eventName(EventName.BABY_SLEPT)
                .start(start)
                .build();
        SomEvent somEvent = new SomEventBuilder()
                .operation(Operation.ACTION)
                .eventName(EventName.BABY_SLEPT)
                .start(start)
                .build();
        LuzEvent luzEvent = new LuzEventBuilder()
                .operation(Operation.ACTION)
                .eventName(EventName.BABY_SLEPT)
                .start(start)
                .build();

        queue.enqueue(aquecedorEvent);
        queue.enqueue(mobileEvent);
        queue.enqueue(somEvent);
        queue.enqueue(luzEvent);
    }

    public Integer handleStartEvent(CameraEvent event) {
        CameraEventBuilder eventBuilder = new CameraEventBuilder();

        if (event.getName() == EventName.BABY_WAKE_UP) {
            System.out.println("[Camera Handler] : BABY_WAKE_UP");
            this.camera.setBabyStatus(BabyStatus.AWAKING);
            this.handleBabyWokeUp();
            eventBuilder
                    .operation(Operation.STATUS_CHANGED)
                    .id(event.getId())
                    .babyStatus(BabyStatus.AWAKING);

        } else if (event.getName() == EventName.BABY_SLEPT) {
            System.out.println("[Camera Handler] : BABY_SLEPT");
            this.camera.setBabyStatus(BabyStatus.SLEEPING);
            this.handleBabySleeping();
            eventBuilder
                    .operation(Operation.STATUS_CHANGED)
                    .id(event.getId())
                    .babyStatus(BabyStatus.SLEEPING);
        } else if (event.getName() != null) return 0; // Descartar eventos com nome que não foram tratados

        if (event.getEquipmentStatus() != null && event.getEquipmentStatus() != camera.getEquipmentStatus()) {
            camera.toggle();
            eventBuilder
                    .operation(Operation.STATUS_CHANGED)
                    .id(event.getId())
                    .equipmentStatus(event.getEquipmentStatus());
        }
        if (event.getRecording() != null) {
            camera.recordingControl(event.getRecording());
            eventBuilder
                    .operation(Operation.STATUS_CHANGED)
                    .id(event.getId())
                    .recording(event.getRecording());
        }

        this.sendUiQueue(eventBuilder.build());

        return 0;
    }

    public Integer handleEndEvent(CameraEvent event) {
        CameraEventBuilder eventBuilder = new CameraEventBuilder();
        if (event.getBabyStatus() != null) {
            switch (event.getBabyStatus()) {
                case AWAKE:
                    System.out.println("Indo dormir");

                    this.camera.setBabyStatus(BabyStatus.SLEEPING);
                    this.handleBabySleeping();
                    eventBuilder
                            .operation(Operation.STATUS_CHANGED)
                            .id(event.getId())
                            .babyStatus(BabyStatus.SLEEPING);
                    break;

                case SLEEPING:
                    System.out.println("Acordando");

                    this.camera.setBabyStatus(BabyStatus.AWAKE);
                    this.handleBabyWokeUp();
                    eventBuilder
                            .operation(Operation.STATUS_CHANGED)
                            .id(event.getId())
                            .babyStatus(BabyStatus.AWAKE);
                    break;
            }

        } else {
            this.camera.toggle();
            eventBuilder
                    .operation(Operation.STATUS_CHANGED)
                    .id(event.getId())
                    .equipmentStatus(camera.getEquipmentStatus());
        }

        this.sendUiQueue(eventBuilder.build());

        return 0;
    }
}
