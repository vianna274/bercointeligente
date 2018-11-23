package br.ufrgs.inf.data.builders;

import br.ufrgs.inf.data.domain.EquipmentStatus;
import br.ufrgs.inf.data.domain.EventName;
import br.ufrgs.inf.data.domain.Operation;
import br.ufrgs.inf.data.domain.Temperature;
import br.ufrgs.inf.data.events.AquecedorEvent;

import java.time.LocalDateTime;

public class AquecedorEventBuilder {

    private Temperature _temperature = null;
    private EquipmentStatus _equipmentStatus = null;
    private Operation _operation = null;
    private LocalDateTime _start = null;
    private  LocalDateTime _end = null;
    private EventName _eventName = null;
    private String _id = null;

    public AquecedorEventBuilder id(String id) {
        this._id = id;
        return this;
    }

    public AquecedorEventBuilder temperature(Temperature temperature) {
        this._temperature = temperature;
        return this;
    }

    public AquecedorEventBuilder equipmentStatus(EquipmentStatus equipmentStatus) {
        this._equipmentStatus = equipmentStatus;
        return this;
    }

    public AquecedorEventBuilder operation(Operation operation) {
        this._operation = operation;
        return this;
    }

    public AquecedorEventBuilder start(LocalDateTime start) {
        this._start = start;
        return this;
    }

    public AquecedorEventBuilder end(LocalDateTime end) {
        this._end = end;
        return this;
    }

    public AquecedorEventBuilder eventName(EventName eventName) {
        this._eventName = eventName;
        return this;
    }

    public AquecedorEvent build() {
        if (_id != null)
            return new AquecedorEvent(_operation, _eventName, _start, _end, _temperature, _equipmentStatus, _id);
        return new AquecedorEvent(_operation, _eventName, _start, _end, _temperature, _equipmentStatus);
    }
}
