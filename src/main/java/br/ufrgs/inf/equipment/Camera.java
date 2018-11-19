package br.ufrgs.inf.equipment;

import br.ufrgs.inf.data.domain.BabyStatus;
import br.ufrgs.inf.data.domain.EquipmentStatus;
import br.ufrgs.inf.data.domain.Recording;

import java.util.function.Function;

public class Camera {

    private EquipmentStatus equipmentStatus;
    private Recording recording;
    private BabyStatus babyStatus;

    private Function<String, Integer> equipmentStatusCallback;
    private Function<String, Integer> recordingCallback;
    private Function<String, Integer> babyStatusCallback;

    public Camera() {
        this.equipmentStatus = EquipmentStatus.OFF;
        this.recording = Recording.OFF;
        this.babyStatus = BabyStatus.SLEEPING;
        this.babyStatusCallback = null;
        this.equipmentStatusCallback = null;
        this.recordingCallback = null;
    }

    public void recordingControl(Recording recording){
        this.recording = recording;
        if (recordingCallback != null)
            recordingCallback.apply(recording.toString());
    }
    public void toggle() {
        this.equipmentStatus = this.equipmentStatus == EquipmentStatus.OFF ? EquipmentStatus.ON : EquipmentStatus.OFF;
        if (equipmentStatusCallback != null)
            equipmentStatusCallback.apply(equipmentStatus.toString());
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
        if (babyStatusCallback != null)
            babyStatusCallback.apply(babyStatus.toString());
    }

    public void setEquipmentStatusCallback(Function<String, Integer> equipmentStatusCallback) {
        this.equipmentStatusCallback = equipmentStatusCallback;
    }

    public void setRecordingCallback(Function<String, Integer> recordingCallback) {
        this.recordingCallback = recordingCallback;
    }

    public void setBabyStatusCallback(Function<String, Integer> babyStatusCallback) {
        this.babyStatusCallback = babyStatusCallback;
    }

}
