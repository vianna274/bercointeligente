package org.ufrgs.engsw.berco.ui;

import org.ufrgs.engsw.berco.controller.EventCRUD;
import org.ufrgs.engsw.berco.data.AquecedorEvent;
import org.ufrgs.engsw.berco.data.CameraEvent;
import org.ufrgs.engsw.berco.data.domain.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Ui {

    private EventCRUD eventCrud;

    public Ui(EventCRUD eventCRUD) {
        this.eventCrud = eventCRUD;
    }

    public void run() throws Exception {
        Scanner reader = new Scanner(System.in);
        while(true) {
            showMenu();
            int opt = reader.nextInt();
            switch(opt) {
                case 1:
                    showEventMenu();
                    handleEventMenu(reader);
                    break;
                case 2: break;
                case 3: break;
                case 0:
                    System.out.println("Exiting");
                    System.exit(0);
            }
        }

    }

    private void createDummyEvent() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime end = now.plus(1, ChronoUnit.HOURS);

        this.eventCrud.createAquecedorEvent(now, end, EquipmentStatus.OFF);
        this.eventCrud.createCameraEvent(now, end, Recording.OFF, EquipmentStatus.OFF);
        this.eventCrud.createLuzEvent(now, end, EquipmentStatus.ON);
        this.eventCrud.createMobileEvent(now, end, MobileSpeed.FAST, EquipmentStatus.ON);
        this.eventCrud.createSomEvent(now, end, MusicVolume.HIGH, Song.THIRD, EquipmentStatus.ON);
        this.eventCrud.createAquecedorEvent(now, end, Temperature.HOT, EquipmentStatus.ON);

    }

    private void handleEventMenu(Scanner reader) throws Exception {
        int opt = reader.nextInt();
        switch(opt) {
            case 1:
                this.createDummyEvent();
                break;
            case 2: break;
            case 3: break;
            case 0:
                System.out.println("Back to the MENU");
        }
    }

    private void showEventMenu() {
        System.out.println("--- EVENT MENU ---");
        System.out.println("1 - Criar evento");
        System.out.println("2 - Deletar evento");
        System.out.println("3 - Editar evento");
        System.out.println("4 - Pausar evento");
        System.out.println("5 - Continuar evento");
        System.out.println("0 - Back");
    }

    private void showMenu() {
        System.out.println("--- MENU ---");
        System.out.println("1 - Gerenciar eventos");
        System.out.println("2 - Ver status do bebe");
        System.out.println("3 - Ver status dos equipamentos");
        System.out.println("0 - Sair");
    }
}
