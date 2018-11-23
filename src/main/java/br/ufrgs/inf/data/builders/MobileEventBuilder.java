package br.ufrgs.inf.data.builders;

import br.ufrgs.inf.data.domain.EquipmentStatus;
import br.ufrgs.inf.data.domain.EventName;
import br.ufrgs.inf.data.domain.Operation;
import br.ufrgs.inf.data.domain.MobileSpeed;
import br.ufrgs.inf.data.events.MobileEvent;

import java.time.LocalDateTime;

public class MobileEventBuilder {

    private MobileSpeed _mobileSpeed = null;
    private EquipmentStatus _equipmentStatus = null;
    private Operation _operation = Operation.POST;
    private LocalDateTime _start = null;
    private  LocalDateTime _end = null;
    private EventName _eventName = null;
    private String _id = null;

    public MobileEventBuilder id(String id) {
        this._id = id;
        return this;
    }

    public MobileEventBuilder mobileSpeed(MobileSpeed mobileSpeed) {
        this._mobileSpeed = mobileSpeed;
        return this;
    }

    public MobileEventBuilder equipmentStatus(EquipmentStatus equipmentStatus) {
        this._equipmentStatus = equipmentStatus;
        return this;
    }

    public MobileEventBuilder operation(Operation operation) {
        this._operation = operation;
        return this;
    }

    public MobileEventBuilder start(LocalDateTime start) {
        this._start = start;
        return this;
    }

    public MobileEventBuilder end(LocalDateTime end) {
        this._end = end;
        return this;
    }

    public MobileEventBuilder eventName(EventName eventName) {
        this._eventName = eventName;
        return this;
    }

    public MobileEvent build() {
        if (_id != null)
            return new MobileEvent(_operation, _eventName, _start, _end, _mobileSpeed, _equipmentStatus, _id);
        return new MobileEvent(_operation, _eventName, _start, _end, _mobileSpeed, _equipmentStatus);
    }
}
