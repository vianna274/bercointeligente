package org.ufrgs.engsw.berco.ui;

import org.ufrgs.engsw.berco.controller.EventCRUD;
import org.ufrgs.engsw.berco.controller.Exibicao;
import org.ufrgs.engsw.berco.data.ExibicaoStatus;
import org.ufrgs.engsw.berco.data.domain.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.function.Function;


public class UI {

    private EventCRUD eventCrud;
    private Exibicao exibicao;
    private Function<String, Integer> callback;

    public UI(EventCRUD eventCRUD, Exibicao exibicao) {
        this.eventCrud = eventCRUD;
        this.exibicao = exibicao;
    }

    public String execute(int opt)  {
        switch(opt) {
            case 1:
                return this.getBabyStatus();
            case 2:
                return this.getEquipmentStatus();
            case 3:
                this.wakeUpBaby();
                break;
            case 0:
                System.out.println("Exiting");
                System.exit(0);
        }
        return "";
    }

    private void wakeUpBaby() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime end = now.plus(10, ChronoUnit.SECONDS);
        this.eventCrud.createCameraEvent(now, end, Recording.OFF, EquipmentStatus.ON);
        this.eventCrud.createCameraEvent(now, end, BabyStatus.AWAKE);
    }

    private String getBabyStatus() {
        String res = exibicao.getBabyStatus();
        return res;
    }

    private String getEquipmentStatus() {
        ExibicaoStatus res = exibicao.getEquipmentStatus();
        return res.toString();
    }

    private void createDummyEvent()  {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime end = now.plus(10, ChronoUnit.SECONDS);

        this.eventCrud.createCameraEvent(now, end, Recording.ON, EquipmentStatus.ON);
        this.eventCrud.createLuzEvent(now, end, EquipmentStatus.ON);
        this.eventCrud.createMobileEvent(now, end, MobileSpeed.FAST, EquipmentStatus.ON);
        this.eventCrud.createSomEvent(now, end, MusicVolume.HIGH, Song.THIRD, EquipmentStatus.ON);
        this.eventCrud.createAquecedorEvent(now, end, Temperature.HOT, EquipmentStatus.ON);

    }
}
