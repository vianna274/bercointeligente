package org.ufrgs.engsw.berco.data;

import org.ufrgs.engsw.berco.data.domain.EquipmentStatus;
import org.ufrgs.engsw.berco.data.domain.Temperature;

import java.time.LocalDateTime;
import java.util.UUID;

public class AquecedorEvent extends DefaultEvent {

    private Temperature temperature;
    private EquipmentStatus equipmentStatus;

    private AquecedorEvent(LocalDateTime end) {
        super(end);
    }

    public AquecedorEvent(LocalDateTime end, Temperature temperature) {
        super(end);
        this.temperature = temperature;
    }

    public AquecedorEvent(LocalDateTime end, EquipmentStatus equipmentStatus) {
        super(end);
        this.equipmentStatus = equipmentStatus;
    }

    public AquecedorEvent(LocalDateTime end, Temperature temperature, EquipmentStatus equipmentStatus) {
        super(end);
        this.temperature = temperature;
        this.equipmentStatus = equipmentStatus;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public EquipmentStatus getEquipmentStatus() {
        return equipmentStatus;
    }

    public void setEquipmentStatus(EquipmentStatus equipmentStatus) {
        this.equipmentStatus = equipmentStatus;
    }
}
