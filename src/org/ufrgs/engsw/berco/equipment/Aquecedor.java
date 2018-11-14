package org.ufrgs.engsw.berco.equipment;

import org.ufrgs.engsw.berco.data.domain.EquipmentStatus;
import org.ufrgs.engsw.berco.data.domain.Temperature;

import java.util.function.Function;

public class Aquecedor {

    private EquipmentStatus equipmentStatus;
    private Temperature temperature;

    private Function<String, Integer> statusCallback;
    private Function<String, Integer> temperatureCallback;

    public Aquecedor() {
        this.equipmentStatus = EquipmentStatus.OFF;
        this.temperature = Temperature.NORMAL;
        this.statusCallback = null;
        this.temperatureCallback = null;
    }

    public void changeTemperature(Temperature temperature) {
        this.temperature = temperature;
        if (temperatureCallback != null)
            temperatureCallback.apply(temperature.toString());
    }

    public void toggle() {
        this.equipmentStatus = this.equipmentStatus == EquipmentStatus.OFF ? EquipmentStatus.ON : EquipmentStatus.OFF;
        if (statusCallback != null)
            statusCallback.apply(this.equipmentStatus.toString());
    }

    public EquipmentStatus getEquipmentStatus() {
        return equipmentStatus;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setStatusCallback(Function<String, Integer> statusCallback) {
        this.statusCallback = statusCallback;
    }

    public void setTemperatureCallback(Function<String, Integer> temperatureCallback) {
        this.temperatureCallback = temperatureCallback;
    }
}
