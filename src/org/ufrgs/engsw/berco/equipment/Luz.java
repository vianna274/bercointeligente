package org.ufrgs.engsw.berco.equipment;

import org.ufrgs.engsw.berco.data.domain.EquipmentStatus;

import java.util.function.Function;

public class Luz {

    private EquipmentStatus equipmentStatus;
    private Function<String, Integer> equipmentStatusCallback;

    public Luz() {
        this.equipmentStatus = EquipmentStatus.OFF;
        this.equipmentStatusCallback = null;
    }

    public EquipmentStatus getEquipmentStatus() {
        return equipmentStatus;
    }

    public void setEquipmentStatus(EquipmentStatus equipmentStatus) {
        this.equipmentStatus = equipmentStatus;
        if (equipmentStatusCallback != null)
            equipmentStatusCallback.apply(equipmentStatus.toString());
    }

    public void toggle() {
        this.equipmentStatus = this.equipmentStatus == EquipmentStatus.OFF ? EquipmentStatus.ON : EquipmentStatus.OFF;
        if (equipmentStatusCallback != null)
            equipmentStatusCallback.apply(equipmentStatus.toString());
    }

    public void setEquipmentStatusCallback(Function<String, Integer> equipmentStatusCallback) {
        this.equipmentStatusCallback = equipmentStatusCallback;
    }
}
