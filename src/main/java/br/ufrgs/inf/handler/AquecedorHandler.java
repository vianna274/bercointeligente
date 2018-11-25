package br.ufrgs.inf.handler;

import br.ufrgs.inf.data.builders.AquecedorEventBuilder;
import br.ufrgs.inf.data.domain.EquipmentStatus;
import br.ufrgs.inf.data.domain.Operation;
import br.ufrgs.inf.data.events.AquecedorEvent;
import br.ufrgs.inf.data.domain.EventName;
import br.ufrgs.inf.data.domain.Temperature;
import br.ufrgs.inf.equipment.Aquecedor;
import br.ufrgs.inf.event.EventListener;
import br.ufrgs.inf.event.Queue;

public class AquecedorHandler implements EventListener<AquecedorEvent> {

    private Aquecedor aquecedor;
    private Queue queue;
    private Queue uiQueue;
    private Scheduler<AquecedorEvent> scheduler;

    public AquecedorHandler(Aquecedor aquecedor, Queue queue, Queue uiQueue) {
        this.aquecedor = aquecedor;
        this.queue = queue;
        this.uiQueue = uiQueue;
        this.scheduler = new Scheduler<>();
        this.scheduler.setStartEventCallback(this::handleStartEvent);
        this.scheduler.setEndEventCallback(this::handleEndEvent);
        this.scheduler.setResumeEventCallback(this::handleResumeEvent);
        this.scheduler.setPauseEventCallback(this::handlePauseEvent);
    }

    @Override
    public void onEvent(AquecedorEvent event) {
        this.scheduler.scheduleEvent(event);
    }

    public void sendUiQueue(AquecedorEvent event) {
        this.uiQueue.enqueue(event);
    }

    public Integer handlePauseEvent(AquecedorEvent event) {
        System.out.println("[Aquecedor Handler] : Pause");
        this.handleEndEvent(event);
        return 0;
    }

    public Integer handleResumeEvent(AquecedorEvent event) {
        System.out.println("[Aquecedor Handler] : Resume");
        this.handleStartEvent(event);
        return 0;
    }

    public Integer handleEndEvent(AquecedorEvent event) {
        this.aquecedor.toggle();
        this.aquecedor.changeTemperature(Temperature.COLD);

        sendUiQueue(new AquecedorEventBuilder()
                .operation(Operation.STATUS_CHANGED)
                .equipmentStatus(aquecedor.getEquipmentStatus())
                .temperature(event.getTemperature())
                .id(event.getId())
                .build());

        return 0;
    }

    public Integer handleStartEvent(AquecedorEvent event) {
        AquecedorEventBuilder eventBuilder = new AquecedorEventBuilder();

        if (event.getName() == EventName.BABY_SLEPT) {
            System.out.println("[Aquecedor Handler] : BABY_SLEPT");
            aquecedor.turnOff();
            aquecedor.changeTemperature(Temperature.COLD);
            eventBuilder
                    .operation(Operation.STATUS_CHANGED)
                    .equipmentStatus(EquipmentStatus.OFF)
                    .id(event.getId())
                    .temperature(Temperature.COLD);
        } else if (event.getName() == EventName.BABY_WAKE_UP) {
            System.out.println("[Aquecedor Handler] : BABY_WAKE_UP");
            aquecedor.turnOn();
            aquecedor.changeTemperature(Temperature.AMBIENT);
            eventBuilder
                    .operation(Operation.STATUS_CHANGED)
                    .equipmentStatus(EquipmentStatus.ON)
                    .id(event.getId())
                    .temperature(Temperature.AMBIENT);
        } else if (event.getName() != null) return 0; // Descartar eventos com nome que não foram tratados

        if (event.getTemperature() != null && event.getTemperature() != aquecedor.getTemperature()) {
            aquecedor.changeTemperature(event.getTemperature());
            eventBuilder
                    .temperature(event.getTemperature())
                    .id(event.getId())
                    .operation(Operation.STATUS_CHANGED);
        }
        if (event.getEquipmentStatus() != null && event.getEquipmentStatus() != aquecedor.getEquipmentStatus()) {
            aquecedor.toggle();
            eventBuilder
                    .operation(Operation.STATUS_CHANGED)
                    .id(event.getId())
                    .equipmentStatus(event.getEquipmentStatus());
        }

        this.sendUiQueue(eventBuilder.build());

        return 0;
    }
}
