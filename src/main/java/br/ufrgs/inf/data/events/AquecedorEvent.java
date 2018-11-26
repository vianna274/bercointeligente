package br.ufrgs.inf.data.events;

import br.ufrgs.inf.data.domain.EquipmentStatus;
import br.ufrgs.inf.data.domain.EventName;
import br.ufrgs.inf.data.domain.Operation;
import br.ufrgs.inf.data.domain.Temperature;

import java.time.LocalDateTime;
import java.util.Optional;

public class AquecedorEvent extends DefaultEvent {

    private Temperature temperature;
    private EquipmentStatus equipmentStatus;

    public AquecedorEvent(Operation operation, String id) { super(operation, id); }

    public AquecedorEvent(Operation operation, EventName name, LocalDateTime begin, LocalDateTime end,
                          Temperature temperature, EquipmentStatus equipmentStatus) {
        super(operation, name, begin, end);
        this.temperature = temperature;
        this.equipmentStatus = equipmentStatus;
    }

    public AquecedorEvent(Operation operation, EventName name, LocalDateTime begin, LocalDateTime end,
                          Temperature temperature, EquipmentStatus equipmentStatus, String id) {
        super(operation, name, begin, end, id);
        this.temperature = temperature;
        this.equipmentStatus = equipmentStatus;
    }

    public static AquecedorEvent defaultInstance() {
        final LocalDateTime now = LocalDateTime.now();
        return new AquecedorEvent(Operation.POST, null, now, now, Temperature.values()[0], EquipmentStatus.values()[0]);
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public EquipmentStatus getEquipmentStatus() {
        return equipmentStatus;
    }

    public static AquecedorEvent merge(AquecedorEvent newEvent, final AquecedorEvent oldEvent) {
        if (newEvent == null) {
            newEvent = defaultInstance();
        }

        final Optional<AquecedorEvent> opt  = Optional.ofNullable(oldEvent);

        opt.map(AquecedorEvent::getTemperature).ifPresent(newEvent::setTemperature);
        opt.map(AquecedorEvent::getEquipmentStatus).ifPresent(newEvent::setEquipmentStatus);
        opt.map(AquecedorEvent::getEnd).ifPresent(newEvent::setEnd);
        opt.map(AquecedorEvent::getStart).ifPresent(newEvent::setStart);
        opt.map(AquecedorEvent::getOperation).ifPresent(newEvent::setOperation);

        return newEvent;
    }

    public static AquecedorEvent merge(final AquecedorEvent oldEvent) {
        return merge(defaultInstance(), oldEvent);
    }

    public void setEquipmentStatus(EquipmentStatus equipmentStatus) {
        this.equipmentStatus = equipmentStatus;
    }

    @Override
    public String toString() {
        return "AquecedorEvent{ " +
                "operation=" + getOperation() +
                ", name=" + getName() +
                ", temperature=" + temperature +
                ", equipmentStatus=" + equipmentStatus +
                " }\n";
    }
}
