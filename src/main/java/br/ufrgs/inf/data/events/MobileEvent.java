package br.ufrgs.inf.data.events;

import br.ufrgs.inf.data.domain.EquipmentStatus;
import br.ufrgs.inf.data.domain.EventName;
import br.ufrgs.inf.data.domain.MobileSpeed;
import br.ufrgs.inf.data.domain.Operation;

import java.time.LocalDateTime;
import java.util.Optional;

public class MobileEvent extends DefaultEvent {

    private MobileSpeed speed;
    private EquipmentStatus equipmentStatus;

    public MobileEvent(Operation operation, String id) { super(operation, id); }

    public MobileEvent(Operation operation, EventName name, LocalDateTime begin, LocalDateTime end, MobileSpeed speed,
                       EquipmentStatus equipmentStatus) {
        super(operation, name, begin, end);
        this.equipmentStatus = equipmentStatus;
        this.speed = speed;
    }

    public MobileEvent(Operation operation, EventName name, LocalDateTime begin, LocalDateTime end, MobileSpeed speed,
                       EquipmentStatus equipmentStatus, String id) {
        super(operation, name, begin, end, id);
        this.equipmentStatus = equipmentStatus;
        this.speed = speed;
    }

    public static MobileEvent merge(MobileEvent newEvent, final MobileEvent oldEvent) {
        if (newEvent == null) {
            newEvent = defaultInstance();
        }

        final Optional<MobileEvent> opt  = Optional.ofNullable(oldEvent);

        opt.map(MobileEvent::getSpeed).ifPresent(newEvent::setSpeed);
        opt.map(MobileEvent::getEquipmentStatus).ifPresent(newEvent::setEquipmentStatus);
        opt.map(MobileEvent::getEnd).ifPresent(newEvent::setEnd);
        opt.map(MobileEvent::getStart).ifPresent(newEvent::setStart);
        opt.map(MobileEvent::getOperation).ifPresent(newEvent::setOperation);

        return newEvent;
    }

    public static MobileEvent merge(final MobileEvent oldEvent) {
        return merge(defaultInstance(), oldEvent);
    }

    public static MobileEvent defaultInstance() {
        final LocalDateTime now = LocalDateTime.now();
        return new MobileEvent(Operation.POST, null, now, now, MobileSpeed.values()[0], EquipmentStatus.values()[0]);
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
        return "MobileEvent{ " +
                "operation=" + getOperation() +
                ", name=" + getName() +
                ", mobileSpeed=" + speed +
                ", equipmentStatus=" + equipmentStatus +
                " }\n";
    }
}
