package org.ufrgs.engsw.berco.handler;

import org.ufrgs.engsw.berco.data.AquecedorEvent;
import org.ufrgs.engsw.berco.equipment.Aquecedor;
import org.ufrgs.engsw.berco.event.EventListener;
import org.ufrgs.engsw.berco.event.Queue;

public class AquecedorHandler implements EventListener<AquecedorEvent>{

    private Aquecedor aquecedor;
    private Queue queue;

    public AquecedorHandler(Aquecedor aquecedor, Queue queue) {
        super();
        this.aquecedor = aquecedor;
        this.queue = queue;
    }

    @Override
    public void onEvent(AquecedorEvent event) {
        this.handleStartEvent(event);
    }

    public void handleEndEvent(AquecedorEvent event) {
        this.aquecedor.toggle();
    }

    public void handleStartEvent(AquecedorEvent event) {
        if(event.getTemperature() != aquecedor.getTemperature()) {
            aquecedor.changeTemperature(event.getTemperature());
        }
        if(event.getEquipmentStatus() != aquecedor.getEquipmentStatus()){
            aquecedor.toggle();
        }
    }
}
