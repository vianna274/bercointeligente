package br.ufrgs.inf.data;

import br.ufrgs.inf.data.domain.EquipmentStatus;
import br.ufrgs.inf.data.domain.EventName;
import br.ufrgs.inf.data.domain.Operation;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LuzEvent extends DefaultEvent {

    private EquipmentStatus equipmentStatus;


    public LuzEvent(Operation operation, EventName name, LocalDateTime start) {
        super(operation, name, start);
    }

    public LuzEvent(Operation operation, String id) { super (operation, id); };

    public LuzEvent(Operation operation, EventName name, LocalDateTime start, LocalDateTime end) {
        super(operation, name, start, end);
    }

    public LuzEvent(Operation operation, EventName name, LocalDateTime end, EquipmentStatus equipmentStatus) {
        super(operation, name, end);
        this.equipmentStatus = equipmentStatus;
    }
    public LuzEvent(Operation operation, EventName name, LocalDateTime begin, LocalDateTime end, EquipmentStatus equipmentStatus) {
        super(operation, name, begin, end);
        this.equipmentStatus = equipmentStatus;
    }

    public static LuzEvent defaultInstance() {
        final LocalDateTime now = LocalDateTime.now();
        return new LuzEvent(Operation.POST, null, now, now, EquipmentStatus.values()[0]);
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
        return "LuzEvent{ " +
                "operation=" + getOperation() +
                ", name=" + getName() +
                ", equipmentStatus=" + equipmentStatus +
                " }\n";
    }
}
