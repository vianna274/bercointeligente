package br.ufrgs.inf.handler;

import br.ufrgs.inf.data.AquecedorEvent;
import br.ufrgs.inf.data.domain.EquipmentStatus;
import br.ufrgs.inf.data.domain.EventName;
import br.ufrgs.inf.data.domain.Temperature;
import br.ufrgs.inf.equipment.Aquecedor;
import br.ufrgs.inf.event.EventListener;
import br.ufrgs.inf.event.Queue;

public class AquecedorHandler implements EventListener<AquecedorEvent> {

    private Aquecedor aquecedor;
    private Queue queue;
    private Scheduler<AquecedorEvent> scheduler;

    public AquecedorHandler(Aquecedor aquecedor, Queue queue) {
        super();
        this.aquecedor = aquecedor;
        this.queue = queue;
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
        return 0;
    }

    public Integer handleStartEvent(AquecedorEvent event) {
        if (event.getName() == EventName.BABY_SLEPT) {
            System.out.println("[Aquecedor Handler] : BABY_SLEPT");
            aquecedor.turnOff();
            aquecedor.changeTemperature(Temperature.COLD);
            return 0;
        }
        if (event.getName() == EventName.BABY_WAKE_UP) {
            System.out.println("[Aquecedor Handler] : BABY_WAKE_UP");
            aquecedor.turnOn();
            aquecedor.changeTemperature(Temperature.AMBIENT);
            return 0;
        }

        if(event.getName() != null) return 0; // Descartar eventos com nome que não foram tratados

        if(event.getTemperature() != aquecedor.getTemperature()) {
            aquecedor.changeTemperature(event.getTemperature());
        }
        if(event.getEquipmentStatus() != aquecedor.getEquipmentStatus()){
            aquecedor.toggle();
        }
        return 0;
    }
}
