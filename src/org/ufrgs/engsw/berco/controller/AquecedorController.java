package org.ufrgs.engsw.berco.controller;

import org.ufrgs.engsw.berco.data.AquecedorEvent;
import org.ufrgs.engsw.berco.data.domain.EquipmentStatus;
import org.ufrgs.engsw.berco.equipment.Aquecedor;
import org.ufrgs.engsw.berco.event.Dispatcher;
import org.ufrgs.engsw.berco.event.Queue;
import org.ufrgs.engsw.berco.handler.AquecedorHandler;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;

public class AquecedorController {

    public static void main(String[] args) {
        Aquecedor aquecedor = new Aquecedor(); //Hardware
        AquecedorHandler aquecedorHandler = new AquecedorHandler(aquecedor); // Listen to events and emit commands to hardware
        Queue queue = new Queue(); //Just a queue, events pop in and pop out, no processing is done to this data, the only possibility is to remove something from queue after event was sent
        Dispatcher dispatcher = new Dispatcher(queue, Collections.singletonList(aquecedorHandler)); //POP queue events and, based on this event type(on, off...) it dispatch/emit the the assigned handler

        //TODO: IMPORTANT: Event, like aquecedorEvent below should be created by a interface when user inputs data on our app, so it should be a controller(in our case, our CRUD administrator designed on our architecture)
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime end = now.plus(1, ChronoUnit.HOURS);
        AquecedorEvent aquecedorEvent = new AquecedorEvent(end, EquipmentStatus.ON);

        queue.enqueue(aquecedorEvent);
        dispatcher.dispatch();
    }
}
