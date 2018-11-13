package org.ufrgs.engsw.berco.data;

import org.ufrgs.engsw.berco.data.domain.EquipmentStatus;
import org.ufrgs.engsw.berco.data.domain.MobileSpeed;

import java.time.LocalDateTime;

public class MobileEvent extends DefaultEvent {

    private MobileSpeed speed;
    private EquipmentStatus equipmentStatus;

    private MobileEvent(LocalDateTime end) {
        super(end);
    }

    public MobileEvent(LocalDateTime end, MobileSpeed speed, EquipmentStatus equipmentStatus) {
        super(end);
        this.equipmentStatus = equipmentStatus;
        this.speed = speed;
    }

    public MobileEvent(LocalDateTime begin, LocalDateTime end, MobileSpeed speed, EquipmentStatus equipmentStatus) {
        super(begin, end);
        this.equipmentStatus = equipmentStatus;
        this.speed = speed;
    }

    public MobileSpeed getSpeed() {
        return speed;
    }

    public void setSpeed(MobileSpeed speed) {
        this.speed = speed;
    }

    public EquipmentStatus getEquipmentStatus() {
        return equipmentStatus;
    }

    public void setEquipmentStatus(EquipmentStatus equipmentStatus) {
        this.equipmentStatus = equipmentStatus;
    }

    @Override
    public String toString(){
        return "'EquipmentStatus: '" + this.equipmentStatus + "', Speed: '" + this.speed + "'\n";
    }
}
