package br.ufrgs.inf.equipment;

import br.ufrgs.inf.data.domain.EquipmentStatus;

public abstract class Component {
    private EquipmentStatus equipmentStatus;

    public Component() {
        this.equipmentStatus = EquipmentStatus.OFF;
    }

    public EquipmentStatus getEquipmentStatus() {
        return equipmentStatus;
    }


    public void toggle() {
        this.equipmentStatus = this.equipmentStatus == EquipmentStatus.OFF ? EquipmentStatus.ON : EquipmentStatus.OFF;

    }

    public void turnOff() {
        this.equipmentStatus = EquipmentStatus.OFF;
    }

    public void turnOn() { this.equipmentStatus = EquipmentStatus.ON; }


}
