package org.ufrgs.engsw.berco.event;

import org.ufrgs.engsw.berco.data.DefaultEvent;

import java.util.ArrayList;
import java.util.Optional;

public class Queue {

    private ArrayList<DefaultEvent> queueEvent;

    public Queue() {
        this.queueEvent = new ArrayList<>();
    }

    public void enqueue(DefaultEvent ev) {
        queueEvent.add(ev);
    }

    public DefaultEvent dequeue() {
        Optional<DefaultEvent> eventToRemove = queueEvent.stream().findFirst();

        if(eventToRemove.isPresent()) {
            queueEvent.remove(eventToRemove.get());
            return eventToRemove.get();
        }
        return null;
    }

    public DefaultEvent getEvent(String id) {
        for(DefaultEvent event : this.queueEvent) {
            if (event.getId() == id)
                return event;
        }
        return null;
    }

}
