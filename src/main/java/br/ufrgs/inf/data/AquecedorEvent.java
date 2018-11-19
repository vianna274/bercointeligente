package br.ufrgs.inf.data;

import br.ufrgs.inf.data.domain.EquipmentStatus;
import br.ufrgs.inf.data.domain.Temperature;

import java.time.LocalDateTime;

public class AquecedorEvent extends DefaultEvent {

    private Temperature temperature;
    private EquipmentStatus equipmentStatus;

    private AquecedorEvent(String name, LocalDateTime end) {
        super(name, end);
    }

    public AquecedorEvent(String name, LocalDateTime end, Temperature temperature) {
        super(name, end);
        this.temperature = temperature;
    }

    public AquecedorEvent(String name, LocalDateTime end, EquipmentStatus equipmentStatus) throws Exception {
        super(name, end);
        this.equipmentStatus = equipmentStatus;
        if(this.temperature == null && equipmentStatus == EquipmentStatus.ON) throw new Exception("Não é possivel não setar temperatura com aparelho ligado");
    }

    public AquecedorEvent(String name, LocalDateTime end, Temperature temperature, EquipmentStatus equipmentStatus) {
        super(name, end);
        this.temperature = temperature;
        this.equipmentStatus = equipmentStatus;
    }

    public AquecedorEvent(String name, LocalDateTime begin, LocalDateTime end, EquipmentStatus equipmentStatus) throws Exception {
        super(name, begin, end);
        this.equipmentStatus = equipmentStatus;
        if(this.temperature == null && equipmentStatus == EquipmentStatus.ON) throw new Exception("Não é possivel não setar temperatura com aparelho ligado");
    }

    public AquecedorEvent(String name, LocalDateTime begin, LocalDateTime end, Temperature temperature, EquipmentStatus equipmentStatus) {
        super(name, begin, end);
        this.temperature = temperature;
        this.equipmentStatus = equipmentStatus;
    }

    public AquecedorEvent(LocalDateTime end, String name, Temperature temperature, EquipmentStatus equipmentStatus) {
        super(name, end);
        this.temperature = temperature;
        this.equipmentStatus = equipmentStatus;
    }

    public AquecedorEvent(LocalDateTime start, LocalDateTime end, String name, Temperature temperature, EquipmentStatus equipmentStatus) {
        super(name, start, end);
        this.temperature = temperature;
        this.equipmentStatus = equipmentStatus;
    }

    public static AquecedorEvent defaultInstance() {
        final LocalDateTime now = LocalDateTime.now();
        return new AquecedorEvent("", now, now, Temperature.values()[0], EquipmentStatus.values()[0]);
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
        return "AquecedorEvent{" +
                "temperature=" + temperature +
                ", equipmentStatus=" + equipmentStatus +
                '}';
    }
}
