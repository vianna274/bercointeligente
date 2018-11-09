package org.ufrgs.engsw.berco.equipment;

import org.ufrgs.engsw.berco.data.domain.EquipmentStatus;
import org.ufrgs.engsw.berco.data.domain.Temperature;

public class Aquecedor {

    private EquipmentStatus equipmentStatus;
    private Temperature temperature;

    public Aquecedor() {
        this.equipmentStatus = EquipmentStatus.OFF;
        this.temperature = Temperature.NORMAL;
    }

    public void changeTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public void toggle() {
        this.equipmentStatus = this.equipmentStatus == EquipmentStatus.OFF ? EquipmentStatus.ON : EquipmentStatus.OFF;
    }

    public EquipmentStatus getEquipmentStatus() {
        return equipmentStatus;
    }

    public Temperature getTemperature() {
        return temperature;
    }
}
