package org.ufrgs.engsw.berco.event;

import org.ufrgs.engsw.berco.data.AquecedorEvent;
import org.ufrgs.engsw.berco.data.Event;

import java.util.List;

public class Dispatcher {

    private Queue queue;
    private List<EventListener<AquecedorEvent>> aquecedorListeners;

    public Dispatcher(Queue queue, List<EventListener<AquecedorEvent>> aquecedorListeners) {
        this.queue = queue;
        this.aquecedorListeners = aquecedorListeners;
    }

    public void dispatch() {
        Event event = queue.dequeue();

        if(event == null) return;

        if (event instanceof AquecedorEvent) {
            aquecedorListeners.forEach(listener -> listener.onEvent((AquecedorEvent) event));
        }
    }
}
