package br.ufrgs.inf.data.builders;

import br.ufrgs.inf.data.domain.*;
import br.ufrgs.inf.data.events.SomEvent;

import java.time.LocalDateTime;

public class SomEventBuilder {

    private MusicVolume _musicVolume = null;
    private Song _song = null;
    private EquipmentStatus _equipmentStatus = null;
    private Operation _operation = Operation.POST;
    private LocalDateTime _start = null;
    private  LocalDateTime _end = null;
    private EventName _eventName = null;
    private String _id = null;

    public SomEventBuilder id(String id) {
        this._id = id;
        return this;
    }

    public SomEventBuilder musicVolume(MusicVolume musicVolume) {
        this._musicVolume = musicVolume;
        return this;
    }

    public SomEventBuilder song(Song song) {
        this._song = song;
        return this;
    }

    public SomEventBuilder equipmentStatus(EquipmentStatus equipmentStatus) {
        this._equipmentStatus = equipmentStatus;
        return this;
    }

    public SomEventBuilder operation(Operation operation) {
        this._operation = operation;
        return this;
    }

    public SomEventBuilder start(LocalDateTime start) {
        this._start = start;
        return this;
    }

    public SomEventBuilder end(LocalDateTime end) {
        this._end = end;
        return this;
    }

    public SomEventBuilder eventName(EventName eventName) {
        this._eventName = eventName;
        return this;
    }

    public SomEvent build() {
        if (_id != null)
            return new SomEvent(_operation, _eventName, _start, _end, _musicVolume, _song, _equipmentStatus, _id);
        return new SomEvent(_operation, _eventName, _start, _end, _musicVolume, _song, _equipmentStatus);
    }
}
