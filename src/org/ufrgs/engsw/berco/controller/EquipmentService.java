package org.ufrgs.engsw.berco.controller;

import org.ufrgs.engsw.berco.data.ExibicaoStatus;
import org.ufrgs.engsw.berco.data.domain.BabyStatus;
import org.ufrgs.engsw.berco.equipment.*;

public class EquipmentService {

    private Aquecedor aquecedor;
    private Camera camera;
    private Luz luz;
    private Mobile mobile;
    private Som som;

    public EquipmentService(Aquecedor aquecedor, Camera camera, Luz luz, Mobile mobile, Som som) {
        this.aquecedor = aquecedor;
        this.camera = camera;
        this.luz = luz;
        this.mobile = mobile;
        this.som = som;
    }

    public ExibicaoStatus getEquipmentStatus() {
        ExibicaoStatus response = new ExibicaoStatus(camera.getEquipmentStatus(), luz.getEquipmentStatus(),
                mobile.getEquipmentStatus(), som.getEquipmentStatus(), aquecedor.getEquipmentStatus(),
                camera.getRecording(), aquecedor.getTemperature(), mobile.getSpeed(), som.getCurrentSong(),
                som.getMusicVolume());
        return response;
    }

    public String getBabyStatus() {
        BabyStatus status = camera.getBabyStatus();
        return status == BabyStatus.AWAKE ? "AWAKE" : "SLEEPING";
    }
}
