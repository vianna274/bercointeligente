package org.ufrgs.engsw.berco.ui;

import org.ufrgs.engsw.berco.controller.EquipmentService;
import org.ufrgs.engsw.berco.controller.EventCRUD;
import org.ufrgs.engsw.berco.data.Event;
import org.ufrgs.engsw.berco.data.ExibicaoStatus;
import org.ufrgs.engsw.berco.data.domain.BabyStatus;
import org.ufrgs.engsw.berco.data.domain.EquipmentStatus;
import org.ufrgs.engsw.berco.data.domain.Recording;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


public class UI {

    public enum UICommand {
        BABY_STATUS, EQUIPAMENT_STATUS, BABY_WAKE_UP, DELETE_EVENT, CREATE_EVENT;
    }

    private EventCRUD eventCrud;
    private EquipmentService equipmentService;

    public UI(EventCRUD eventCRUD, EquipmentService equipmentService) {
        this.eventCrud = eventCRUD;
        this.equipmentService = equipmentService;
    }

    public String execute(UICommand opt) {
        return this.execute(opt, null);
    }

    public String execute(UICommand opt, Event event)  {
        switch(opt) {
            case BABY_STATUS:
                return this.getBabyStatus();

            case EQUIPAMENT_STATUS:
                return this.getEquipmentStatus();

            case BABY_WAKE_UP:
                this.wakeUpBaby();
                break;

            case DELETE_EVENT:
                this.deleteScheduledEvent(event.id());
                break;

            case CREATE_EVENT:
                return this.scheduleEvent(event);

            default:
                System.exit(0);
                break;
        }

        return "";
    }

    private void deleteScheduledEvent(String eventId) {
        this.eventCrud.deleteScheduledEvent(eventId);
    }

    private void wakeUpBaby() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime end = now.plus(10, ChronoUnit.SECONDS);
        this.eventCrud.createCameraEvent(now, end, Recording.OFF, EquipmentStatus.ON);
        this.eventCrud.createCameraEvent(now, end, BabyStatus.AWAKE);
    }

    private String getBabyStatus() {
        return equipmentService.getBabyStatus();
    }

    private String scheduleEvent(Event event) {
        return this.eventCrud.scheduleEvent(event);
    }

    private String getEquipmentStatus() {
        ExibicaoStatus res = equipmentService.getEquipmentStatus();
        return res.toString();
    }
}
