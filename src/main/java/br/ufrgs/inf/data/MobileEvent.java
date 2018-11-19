package br.ufrgs.inf.data;

import br.ufrgs.inf.data.domain.EquipmentStatus;
import br.ufrgs.inf.data.domain.MobileSpeed;

import java.time.LocalDateTime;

public class MobileEvent extends DefaultEvent {

    private MobileSpeed speed;
    private EquipmentStatus equipmentStatus;

    private MobileEvent(String name, LocalDateTime end) {
        super(name, end);
    }

    public MobileEvent(String name, LocalDateTime end, MobileSpeed speed, EquipmentStatus equipmentStatus) {
        super(name, end);
        this.equipmentStatus = equipmentStatus;
        this.speed = speed;
    }

    public MobileEvent(String name, LocalDateTime begin, LocalDateTime end, MobileSpeed speed, EquipmentStatus equipmentStatus) {
        super(name, begin, end);
        this.equipmentStatus = equipmentStatus;
        this.speed = speed;
    }

    public static MobileEvent defaultInstance() {
        final LocalDateTime now = LocalDateTime.now();
        return new MobileEvent("", now, now, MobileSpeed.values()[0], EquipmentStatus.values()[0]);
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
