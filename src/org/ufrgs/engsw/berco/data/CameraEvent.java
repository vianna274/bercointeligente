package org.ufrgs.engsw.berco.data;

import org.ufrgs.engsw.berco.data.domain.BabyStatus;
import org.ufrgs.engsw.berco.data.domain.EquipmentStatus;
import org.ufrgs.engsw.berco.data.domain.Recording;
import org.ufrgs.engsw.berco.equipment.Camera;

import java.time.LocalDateTime;
import java.util.Random;

public class CameraEvent extends DefaultEvent {

    private Recording recording;
    private EquipmentStatus equipmentStatus;
    private BabyStatus babyStatus;

    private CameraEvent(LocalDateTime end) {
        super(end);
    }

    public CameraEvent(LocalDateTime end, EquipmentStatus equipmentStatus) {
        super(end);
        this.equipmentStatus = equipmentStatus;
    }

    public CameraEvent(LocalDateTime end,  Recording recording, EquipmentStatus equipmentStatus) {
        super(end);
        this.recording = recording;
        this.equipmentStatus = equipmentStatus;
    }

    public CameraEvent(LocalDateTime begin, LocalDateTime end, EquipmentStatus equipmentStatus) {
        super(begin, end);
        this.equipmentStatus = equipmentStatus;
    }

    public CameraEvent(LocalDateTime begin, LocalDateTime end,  Recording recording, EquipmentStatus equipmentStatus) {
        super(begin, end);
        this.recording = recording;
        this.equipmentStatus = equipmentStatus;
    }

    public CameraEvent(LocalDateTime begin, LocalDateTime end, BabyStatus babyStatus) {
        super(begin, end);
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
