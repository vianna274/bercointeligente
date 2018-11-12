package org.ufrgs.engsw.berco;

import org.ufrgs.engsw.berco.controller.EventCRUD;
import org.ufrgs.engsw.berco.equipment.*;
import org.ufrgs.engsw.berco.event.Dispatcher;
import org.ufrgs.engsw.berco.event.Queue;
import org.ufrgs.engsw.berco.handler.*;
import org.ufrgs.engsw.berco.ui.Ui;

import java.util.Collections;

public class Main {

    public static void main(String[] args) throws Exception {
        // Handlers com seus hardwares
        AquecedorHandler aquecedorHandler = new AquecedorHandler(new Aquecedor());
        CameraHandler cameraHandler = new CameraHandler(new Camera());
        MobileHandler mobileHandler = new MobileHandler(new Mobile());
        SomHandler somHandler = new SomHandler(new Som());
        LuzHandler luzHandler = new LuzHandler(new Luz());
        // Queue criada
        Queue queue = new Queue();
        // Dispatcher da queue
        Dispatcher dispatcher = new Dispatcher(queue, Collections.singletonList(aquecedorHandler),
                Collections.singletonList(cameraHandler), Collections.singletonList(luzHandler),
                Collections.singletonList(mobileHandler), Collections.singletonList(somHandler)); //POP queue events and, based on this event type(on, off...) it dispatch/emit the the assigned handler
        // EventCrud
        EventCRUD eventCrud = new EventCRUD(queue);
        Ui ui = new Ui(eventCrud);
        new Thread(dispatcher::run).start(); // Thread para rodar o dispatcher (provavelmente a logica de tempo estara aqui )
        ui.run();
    }
}
