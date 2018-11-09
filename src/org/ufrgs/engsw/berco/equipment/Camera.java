package org.ufrgs.engsw.berco.equipment;

import org.ufrgs.engsw.berco.data.domain.EquipmentStatus;
import org.ufrgs.engsw.berco.data.domain.Recording;
import org.ufrgs.engsw.berco.data.domain.Temperature;

public class Camera {

    private EquipmentStatus equipmentStatus;
    private Recording recording;

    public Camera() {
        this.equipmentStatus = EquipmentStatus.OFF;
        this.recording = Recording.OFF;
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

}
