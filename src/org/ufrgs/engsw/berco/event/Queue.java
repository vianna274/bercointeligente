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

    public Event dequeue(String id) {
        Optional<Event> eventToRemove = queueEvent.stream().filter(ev -> ev.id().equals(id)).findFirst();

        if(eventToRemove.isPresent()) {
            queueEvent.remove(eventToRemove.get());
            return eventToRemove.get();
        }
        return null;
    }

}
