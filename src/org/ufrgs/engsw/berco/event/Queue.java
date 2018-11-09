package org.ufrgs.engsw.berco.event;

import com.sun.org.apache.regexp.internal.RE;
import org.ufrgs.engsw.berco.data.DefaultEvent;
import org.ufrgs.engsw.berco.data.Event;

import java.util.ArrayList;
import java.util.Optional;

public class Queue {

    private ArrayList<Event> queueEvent;

    public Queue() {
        this.queueEvent = new ArrayList<>();
    }

    public void enqueue(Event ev) {
        queueEvent.add(ev);
    }

    public Event dequeue() {
        Optional<Event> eventToRemove = queueEvent.stream().findFirst();

        if(eventToRemove.isPresent()) {
            queueEvent.remove(eventToRemove.get());
            return eventToRemove.get();
        }
        return null;
    }

}
