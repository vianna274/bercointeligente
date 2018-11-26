package br.ufrgs.inf.data.events;

import br.ufrgs.inf.data.domain.*;

import java.time.LocalDateTime;
import java.util.Optional;

public class CameraEvent extends DefaultEvent {

    private Recording recording;
    private EquipmentStatus equipmentStatus;
    private BabyStatus babyStatus;

    public CameraEvent(Operation operation, String id) { super(operation, id); }

    public CameraEvent(Operation operation, EventName name, LocalDateTime begin, LocalDateTime end,
                       EquipmentStatus equipmentStatus, Recording recording, BabyStatus babyStatus) {
        super(operation, name, begin, end);
        this.recording = recording;
        this.equipmentStatus = equipmentStatus;
        this.babyStatus = babyStatus;
    }

    public CameraEvent(Operation operation, EventName name, LocalDateTime begin, LocalDateTime end,
                       EquipmentStatus equipmentStatus, Recording recording, BabyStatus babyStatus, String id) {
        super(operation, name, begin, end, id);
        this.recording = recording;
        this.equipmentStatus = equipmentStatus;
        this.babyStatus = babyStatus;
    }

    public static CameraEvent defaultInstance() {
        final LocalDateTime now = LocalDateTime.now();
        return new CameraEvent(Operation.POST, null, now, now, EquipmentStatus.values()[0],
                Recording.values()[0], BabyStatus.values()[2]);
    }

    public static CameraEvent merge(CameraEvent newEvent, final CameraEvent oldEvent) {
        if (newEvent == null) {
            newEvent = defaultInstance();
        }

        final Optional<CameraEvent> opt  = Optional.ofNullable(oldEvent);

        opt.map(CameraEvent::getRecording).ifPresent(newEvent::setRecording);
        opt.map(CameraEvent::getBabyStatus).ifPresent(newEvent::setBabyStatus);
        opt.map(CameraEvent::getEquipmentStatus).ifPresent(newEvent::setEquipmentStatus);
        opt.map(CameraEvent::getEnd).ifPresent(newEvent::setEnd);
        opt.map(CameraEvent::getStart).ifPresent(newEvent::setStart);
        opt.map(CameraEvent::getOperation).ifPresent(newEvent::setOperation);

        return newEvent;
    }

    public static CameraEvent merge(final CameraEvent oldEvent) {
        return merge(defaultInstance(), oldEvent);
    }

    public BabyStatus getBabyStatus(){
        return babyStatus;
    }

    public Recording getRecording() {
        return recording;
    }

    public void setRecording(Recording recording) {
        this.recording = recording;
    }

    public EquipmentStatus getEquipmentStatus() {
        return equipmentStatus;
    }

    public void setBabyStatus(BabyStatus babyStatus) {
        this.babyStatus = babyStatus;
    }

    public void setEquipmentStatus(EquipmentStatus equipmentStatus) {
        this.equipmentStatus = equipmentStatus;
    }

    @Override
    public String toString(){
        return "CameraEvent{ " +
                "operation=" + getOperation() +
                ", name=" + getName() +
                ", recording=" + recording +
                ", equipmentStatus=" + equipmentStatus +
                ", babyStatus=" + babyStatus +
                " }\n";
    }
}
