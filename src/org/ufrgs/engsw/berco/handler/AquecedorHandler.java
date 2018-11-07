package org.ufrgs.engsw.berco.handler;

import org.ufrgs.engsw.berco.data.AquecedorEvent;
import org.ufrgs.engsw.berco.equipment.Aquecedor;
import org.ufrgs.engsw.berco.event.EventListener;

public class AquecedorHandler implements EventListener<AquecedorEvent>{

    private Aquecedor aquecedor;

    public AquecedorHandler(Aquecedor aquecedor) {
        this.aquecedor = aquecedor;
    }

    @Override
    public void onEvent(AquecedorEvent event) {
        if(event.getTemperature() != aquecedor.getTemperature()) {
            aquecedor.changeTemperature(event.getTemperature());
        }
        if(event.getEquipmentStatus() != aquecedor.getEquipmentStatus()){
            aquecedor.toggle();
        }
    }
}
