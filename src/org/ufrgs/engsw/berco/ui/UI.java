package org.ufrgs.engsw.berco.ui;

import org.ufrgs.engsw.berco.controller.EventCRUD;
import org.ufrgs.engsw.berco.controller.Exibicao;
import org.ufrgs.engsw.berco.data.ExibicaoStatus;
import org.ufrgs.engsw.berco.data.domain.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;


public class UI {

    private EventCRUD eventCrud;
    private Exibicao exibicao;

    public UI(EventCRUD eventCRUD, Exibicao exibicao) {
        this.eventCrud = eventCRUD;
        this.exibicao = exibicao;
    }

    public void execute(int opt)  {
        switch(opt) {
            case 1:
                this.getBabyStatus();
                break;
            case 2:
                this.getEquipmentStatus();
                break;
            case 3:
                this.wakeUpBaby();
                break;
            case 0:
                System.out.println("Exiting");
                System.exit(0);
        }

    }

    private void wakeUpBaby() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime end = now.plus(10, ChronoUnit.SECONDS);
        this.eventCrud.createCameraEvent(now, end, Recording.OFF, EquipmentStatus.ON);
        this.eventCrud.createCameraEvent(now, end, BabyStatus.AWAKE);
    }

    private void getBabyStatus() {
        String res = exibicao.getBabyStatus();
        System.out.println(res);
    }

    private void getEquipmentStatus() {
        ExibicaoStatus res = exibicao.getEquipmentStatus();
        System.out.println(res.toString());
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
        System.out.println("4 - Wake up the baby");
        System.out.println("0 - Sair");
    }
}
