package org.ufrgs.engsw.berco.handler;

import org.ufrgs.engsw.berco.data.CameraEvent;
import org.ufrgs.engsw.berco.data.domain.BabyStatus;
import org.ufrgs.engsw.berco.data.domain.EquipmentStatus;
import org.ufrgs.engsw.berco.data.domain.Recording;
import org.ufrgs.engsw.berco.equipment.Camera;
import org.ufrgs.engsw.berco.event.EventListener;

public class CameraHandler implements EventListener<CameraEvent> {

    private Camera camera;

    public CameraHandler(Camera camera) {
        this.camera = camera;
    }

    @Override
    public void onEvent(CameraEvent event) {
        if(event.getBabyStatus() == BabyStatus.AWAKE) {
            event.setRecording(Recording.ON);
            event.setEquipmentStatus(EquipmentStatus.ON);
            camera.recordingControl(event.getRecording());
        }
        else {
            event.setRecording(Recording.OFF);
            event.setEquipmentStatus(EquipmentStatus.OFF);
            camera.recordingControl(event.getRecording());
        }
    }
}
