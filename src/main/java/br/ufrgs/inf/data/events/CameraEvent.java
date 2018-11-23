package br.ufrgs.inf.data.events;

import br.ufrgs.inf.data.domain.*;

import java.time.LocalDateTime;

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
                Recording.values()[0],BabyStatus.values()[0]);
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
