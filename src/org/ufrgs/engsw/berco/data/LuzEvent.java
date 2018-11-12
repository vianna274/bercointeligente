package org.ufrgs.engsw.berco.data;

import org.ufrgs.engsw.berco.data.domain.EquipmentStatus;

import java.time.LocalDateTime;

public class LuzEvent extends DefaultEvent {

    private EquipmentStatus equipmentStatus;

    private LuzEvent(LocalDateTime end) {
        super(end);
    }

    public LuzEvent(LocalDateTime end, EquipmentStatus equipmentStatus) {
        super(end);
        this.equipmentStatus = equipmentStatus;
    }


    public EquipmentStatus getEquipmentStatus() {
        return equipmentStatus;
    }

    public void setEquipmentStatus(EquipmentStatus equipmentStatus) {
        this.equipmentStatus = equipmentStatus;
    }

    @Override
    public String toString(){
        return "'EquipmentStatus: '" + this.equipmentStatus + "'\n";
    }
}
