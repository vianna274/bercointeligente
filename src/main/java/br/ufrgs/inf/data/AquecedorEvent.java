package br.ufrgs.inf.data;

import br.ufrgs.inf.data.domain.EquipmentStatus;
import br.ufrgs.inf.data.domain.EventName;
import br.ufrgs.inf.data.domain.Operation;
import br.ufrgs.inf.data.domain.Temperature;

import java.time.LocalDateTime;

public class AquecedorEvent extends DefaultEvent {

    private Temperature temperature;
    private EquipmentStatus equipmentStatus;

    public AquecedorEvent(Operation operation, String id) {
        super(operation, id);
    }

    public AquecedorEvent(Operation operation, EventName name, LocalDateTime start, LocalDateTime end) {
        super(operation, name, start, end);
    }

    public AquecedorEvent(Operation operation, EventName name, LocalDateTime start) {
        super(operation, name, start);
    }

    public AquecedorEvent(Operation operation, EventName name, LocalDateTime end, Temperature temperature) {
        super(operation, name, end);
        this.temperature = temperature;
    }

    public AquecedorEvent(Operation operation, EventName name, LocalDateTime end, EquipmentStatus equipmentStatus) throws Exception {
        super(operation, name, end);
        this.equipmentStatus = equipmentStatus;
        if(this.temperature == null && equipmentStatus == EquipmentStatus.ON) throw new Exception("Não é possivel não setar temperatura com aparelho ligado");
    }

    public AquecedorEvent(Operation operation, EventName name, LocalDateTime end, Temperature temperature, EquipmentStatus equipmentStatus) {
        super(operation, name, end);
        this.temperature = temperature;
        this.equipmentStatus = equipmentStatus;
    }

    public AquecedorEvent(Operation operation, EventName name, LocalDateTime begin, LocalDateTime end, EquipmentStatus equipmentStatus) throws Exception {
        super(operation, name, begin, end);
        this.equipmentStatus = equipmentStatus;
        if(this.temperature == null && equipmentStatus == EquipmentStatus.ON) throw new Exception("Não é possivel não setar temperatura com aparelho ligado");
    }

    public AquecedorEvent(Operation operation, EventName name, LocalDateTime begin, LocalDateTime end, Temperature temperature, EquipmentStatus equipmentStatus) {
        super(operation, name, begin, end);
        this.temperature = temperature;
        this.equipmentStatus = equipmentStatus;
    }

    public AquecedorEvent(Operation operation, LocalDateTime end, EventName name, Temperature temperature, EquipmentStatus equipmentStatus) {
        super(operation, name, end);
        this.temperature = temperature;
        this.equipmentStatus = equipmentStatus;
    }

    public AquecedorEvent(Operation operation, LocalDateTime start, LocalDateTime end, EventName name, Temperature temperature, EquipmentStatus equipmentStatus) {
        super(operation, name, start, end);
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
