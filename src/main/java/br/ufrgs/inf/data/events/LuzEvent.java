package br.ufrgs.inf.data.events;

import br.ufrgs.inf.data.domain.EquipmentStatus;
import br.ufrgs.inf.data.domain.EventName;
import br.ufrgs.inf.data.domain.Operation;

import java.time.LocalDateTime;
import java.util.Optional;

public class LuzEvent extends DefaultEvent {

    private EquipmentStatus equipmentStatus;

    public LuzEvent(Operation operation, String id) { super(operation, id); }

    public LuzEvent(Operation operation, EventName name, LocalDateTime begin, LocalDateTime end, EquipmentStatus equipmentStatus) {
        super(operation, name, begin, end);
        this.equipmentStatus = equipmentStatus;
    }

    public LuzEvent(Operation operation, EventName name, LocalDateTime begin, LocalDateTime end,
                    EquipmentStatus equipmentStatus, String id) {
        super(operation, name, begin, end, id);
        this.equipmentStatus = equipmentStatus;
    }

    public static LuzEvent merge(LuzEvent newEvent, final LuzEvent oldEvent) {
        if (newEvent == null) {
            newEvent = defaultInstance();
        }

        final Optional<LuzEvent> opt  = Optional.ofNullable(oldEvent);

        opt.map(LuzEvent::getEquipmentStatus).ifPresent(newEvent::setEquipmentStatus);
        opt.map(LuzEvent::getEnd).ifPresent(newEvent::setEnd);
        opt.map(LuzEvent::getStart).ifPresent(newEvent::setStart);
        opt.map(LuzEvent::getOperation).ifPresent(newEvent::setOperation);

        return newEvent;
    }

    public static LuzEvent merge(final LuzEvent oldEvent) {
        return merge(defaultInstance(), oldEvent);
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
