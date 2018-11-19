package br.ufrgs.inf.data;

import br.ufrgs.inf.data.domain.EquipmentStatus;

import java.time.LocalDateTime;

public class LuzEvent extends DefaultEvent {

    private EquipmentStatus equipmentStatus;

    private LuzEvent(String name, LocalDateTime end) {
        super(name, end);
    }

    public LuzEvent(String name, LocalDateTime end, EquipmentStatus equipmentStatus) {
        super(name, end);
        this.equipmentStatus = equipmentStatus;
    }
    public LuzEvent(String name, LocalDateTime begin, LocalDateTime end, EquipmentStatus equipmentStatus) {
        super(name, begin, end);
        this.equipmentStatus = equipmentStatus;
    }

    public static LuzEvent defaultInstance() {
        final LocalDateTime now = LocalDateTime.now();
        return new LuzEvent("", now, now, EquipmentStatus.values()[0]);
    }

    public void toggleLigthStatus(){
        if(equipmentStatus.equals(EquipmentStatus.OFF)){
            this.equipmentStatus = EquipmentStatus.ON;
        }
        else{
            this.equipmentStatus = EquipmentStatus.OFF;
        }
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
