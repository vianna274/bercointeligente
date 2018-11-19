package br.ufrgs.inf.handler;

import br.ufrgs.inf.data.AquecedorEvent;
import br.ufrgs.inf.equipment.Aquecedor;
import br.ufrgs.inf.event.EventListener;
import br.ufrgs.inf.event.Queue;

public class AquecedorHandler implements EventListener<AquecedorEvent> {

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
