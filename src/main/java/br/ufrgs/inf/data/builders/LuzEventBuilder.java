package br.ufrgs.inf.data.builders;

import br.ufrgs.inf.data.domain.EquipmentStatus;
import br.ufrgs.inf.data.domain.EventName;
import br.ufrgs.inf.data.domain.Operation;
import br.ufrgs.inf.data.events.LuzEvent;

import java.time.LocalDateTime;

public class LuzEventBuilder {

    private EquipmentStatus _equipmentStatus = null;
    private Operation _operation = Operation.POST;
    private LocalDateTime _start = null;
    private  LocalDateTime _end = null;
    private EventName _eventName = null;
    private String _id = null;

    public LuzEventBuilder id(String id) {
        this._id = id;
        return this;
    }

    public LuzEventBuilder equipmentStatus(EquipmentStatus equipmentStatus) {
        this._equipmentStatus = equipmentStatus;
        return this;
    }

    public LuzEventBuilder operation(Operation operation) {
        this._operation = operation;
        return this;
    }

    public LuzEventBuilder start(LocalDateTime start) {
        this._start = start;
        return this;
    }

    public LuzEventBuilder end(LocalDateTime end) {
        this._end = end;
        return this;
    }

    public LuzEventBuilder eventName(EventName eventName) {
        this._eventName = eventName;
        return this;
    }

    public LuzEvent build() {
        if (_id != null)
            return new LuzEvent(_operation, _eventName, _start, _end, _equipmentStatus, _id);
        return new LuzEvent(_operation, _eventName, _start, _end, _equipmentStatus);
    }
}
