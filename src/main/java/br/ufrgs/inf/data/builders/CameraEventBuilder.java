package br.ufrgs.inf.data.builders;

import br.ufrgs.inf.data.domain.*;
import br.ufrgs.inf.data.events.CameraEvent;

import java.time.LocalDateTime;

public class CameraEventBuilder {

    private Recording _recording = null;
    private BabyStatus _babyStatus = null;
    private EquipmentStatus _equipmentStatus = null;
    private Operation _operation = Operation.POST;
    private LocalDateTime _start = null;
    private  LocalDateTime _end = null;
    private EventName _eventName = null;
    private String _id = null;

    public CameraEventBuilder id(String id) {
        this._id = id;
        return this;
    }

    public CameraEventBuilder recording(Recording recording) {
        this._recording = recording;
        return this;
    }

    public CameraEventBuilder babyStatus(BabyStatus babyStatus) {
        this._babyStatus = babyStatus;
        return this;
    }

    public CameraEventBuilder equipmentStatus(EquipmentStatus equipmentStatus) {
        this._equipmentStatus = equipmentStatus;
        return this;
    }

    public CameraEventBuilder operation(Operation operation) {
        this._operation = operation;
        return this;
    }

    public CameraEventBuilder start(LocalDateTime start) {
        this._start = start;
        return this;
    }

    public CameraEventBuilder end(LocalDateTime end) {
        this._end = end;
        return this;
    }

    public CameraEventBuilder eventName(EventName eventName) {
        this._eventName = eventName;
        return this;
    }

    public CameraEvent build() {
        if (_id != null)
            return new CameraEvent(_operation, _eventName, _start, _end, _equipmentStatus, _recording, _babyStatus, _id);
        return new CameraEvent(_operation, _eventName, _start, _end, _equipmentStatus, _recording, _babyStatus);
    }
}
