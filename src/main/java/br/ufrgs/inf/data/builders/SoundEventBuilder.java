package br.ufrgs.inf.data.builders;

import br.ufrgs.inf.data.domain.BabyStatus;
import br.ufrgs.inf.data.domain.EquipmentStatus;
import br.ufrgs.inf.data.domain.EventName;
import br.ufrgs.inf.data.domain.Operation;
import br.ufrgs.inf.data.events.SoundEvent;

import java.time.LocalDateTime;

public class SoundEventBuilder {

    private Operation _operation = Operation.POST;
    private LocalDateTime _start = null;
    private  LocalDateTime _end = null;
    private EventName _eventName = null;
    private BabyStatus _babyStatus = null;

    public SoundEventBuilder operation(Operation operation) {
        this._operation = operation;
        return this;
    }

    public SoundEventBuilder babyStatus(BabyStatus babyStatus) {
        this._babyStatus = babyStatus;
        return this;
    }

    public SoundEventBuilder start(LocalDateTime start) {
        this._start = start;
        return this;
    }

    public SoundEventBuilder end(LocalDateTime end) {
        this._end = end;
        return this;
    }

    public SoundEventBuilder eventName(EventName eventName) {
        this._eventName = eventName;
        return this;
    }

    public SoundEvent build() {
        return new SoundEvent(_operation, _eventName, _babyStatus, _start, _end);
    }
}
