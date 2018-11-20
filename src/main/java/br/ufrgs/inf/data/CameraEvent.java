package br.ufrgs.inf.data;

import br.ufrgs.inf.data.domain.*;

import java.time.LocalDateTime;

public class CameraEvent extends DefaultEvent {

    private Recording recording;
    private EquipmentStatus equipmentStatus;
    private BabyStatus babyStatus;

    public static CameraEvent defaultInstance() {
        final LocalDateTime now = LocalDateTime.now();
        return new CameraEvent(Operation.POST, null, now, now, Recording.values()[0], EquipmentStatus.values()[0]);
    }


    public CameraEvent(Operation operation, EventName name, LocalDateTime start) {
        super(operation, name, start);
    }

    public CameraEvent(Operation operation, EventName name, LocalDateTime start, LocalDateTime end) {
        super(operation, name, start, end);
    }

    public CameraEvent(Operation operation, String id) { super(operation, id); }

    public CameraEvent(Operation operation, EventName name, LocalDateTime end, EquipmentStatus equipmentStatus) {
        super(operation, name, end);
        this.equipmentStatus = equipmentStatus;
    }

    public CameraEvent(Operation operation, EventName name, LocalDateTime end,  Recording recording, EquipmentStatus equipmentStatus) {
        super(operation, name, end);
        this.recording = recording;
        this.equipmentStatus = equipmentStatus;
    }

    public CameraEvent(Operation operation, EventName name, LocalDateTime begin, LocalDateTime end, EquipmentStatus equipmentStatus) {
        super(operation, name, begin, end);
        this.equipmentStatus = equipmentStatus;
    }

    public CameraEvent(Operation operation, EventName name, LocalDateTime begin, LocalDateTime end,  Recording recording, EquipmentStatus equipmentStatus) {
        super(operation, name, begin, end);
        this.recording = recording;
        this.equipmentStatus = equipmentStatus;
    }

    public CameraEvent(Operation operation, EventName name, LocalDateTime begin, LocalDateTime end, BabyStatus babyStatus) {
        super(operation, name, begin, end);
        this.babyStatus = babyStatus;
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
