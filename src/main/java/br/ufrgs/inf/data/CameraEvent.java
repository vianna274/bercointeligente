package br.ufrgs.inf.data;

import br.ufrgs.inf.data.domain.BabyStatus;
import br.ufrgs.inf.data.domain.EquipmentStatus;
import br.ufrgs.inf.data.domain.Recording;

import java.time.LocalDateTime;

public class CameraEvent extends DefaultEvent {

    private Recording recording;
    private EquipmentStatus equipmentStatus;
    private BabyStatus babyStatus;

    public static CameraEvent defaultInstance() {
        final LocalDateTime now = LocalDateTime.now();
        return new CameraEvent("", now, now, Recording.values()[0], EquipmentStatus.values()[0]);
    }

    private CameraEvent(String name, LocalDateTime end) {
        super(name, end);
    }

    public CameraEvent(String name, LocalDateTime end, EquipmentStatus equipmentStatus) {
        super(name, end);
        this.equipmentStatus = equipmentStatus;
    }

    public CameraEvent(String name, LocalDateTime end,  Recording recording, EquipmentStatus equipmentStatus) {
        super(name, end);
        this.recording = recording;
        this.equipmentStatus = equipmentStatus;
    }

    public CameraEvent(String name, LocalDateTime begin, LocalDateTime end, EquipmentStatus equipmentStatus) {
        super(name, begin, end);
        this.equipmentStatus = equipmentStatus;
    }

    public CameraEvent(String name, LocalDateTime begin, LocalDateTime end,  Recording recording, EquipmentStatus equipmentStatus) {
        super(name, begin, end);
        this.recording = recording;
        this.equipmentStatus = equipmentStatus;
    }

    public CameraEvent(String name, LocalDateTime begin, LocalDateTime end, BabyStatus babyStatus) {
        super(name, begin, end);
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
        return "Recording: '" + this.recording + "', EquipmentStatus: '" + this.equipmentStatus + "', BabyStatus: '" + getBabyStatus() + "'\n";
    }
}
