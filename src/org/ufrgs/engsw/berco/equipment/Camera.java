package org.ufrgs.engsw.berco.equipment;

import org.ufrgs.engsw.berco.data.domain.BabyStatus;
import org.ufrgs.engsw.berco.data.domain.EquipmentStatus;
import org.ufrgs.engsw.berco.data.domain.Recording;
import org.ufrgs.engsw.berco.data.domain.Temperature;

public class Camera {

    private EquipmentStatus equipmentStatus;
    private Recording recording;
    private BabyStatus babyStatus;

    public Camera() {
        this.equipmentStatus = EquipmentStatus.OFF;
        this.recording = Recording.OFF;
        this.babyStatus = BabyStatus.SLEEPING;
    }

    public void recordingControl(Recording recording){
        this.recording = recording;
    }
    public void toggle() {
        this.equipmentStatus = this.equipmentStatus == EquipmentStatus.OFF ? EquipmentStatus.ON : EquipmentStatus.OFF;
    }
    public EquipmentStatus getEquipmentStatus() {
        return equipmentStatus;
    }

    public Recording getRecording() { return recording; }

    public BabyStatus getBabyStatus() {
        return babyStatus;
    }

    public void setBabyStatus(BabyStatus babyStatus) {
        this.babyStatus = babyStatus;
    }

}
