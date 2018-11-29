package br.ufrgs.inf.controller;

import br.ufrgs.inf.data.domain.BabyStatus;
import br.ufrgs.inf.equipment.*;

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

    public BabyStatus getBabyStatus() {
        return camera.getBabyStatus();
    }
}
