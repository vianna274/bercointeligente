package org.ufrgs.engsw.berco.handler;

import org.ufrgs.engsw.berco.data.DefaultEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Scheduler<T extends DefaultEvent> {

    private ArrayList<T> events;
    private T currentEvent;

    public Scheduler() {
        this.currentEvent = null;
        this.events = new ArrayList<>();
        new Thread(this::eventsManager).start();
    }

    public void scheduleEvent(T event) {
        this.events.add(event);
    }

    private void isAnyEventBegun() {
        LocalDateTime currentDate = LocalDateTime.now();
        for(T event : this.events) {
            if (event.start().compareTo(currentDate) <= 0) {
                this.currentEvent = event;
                this.events.remove(event);
                this.handleStartEvent(this.currentEvent);
                break;
            }
        }
    }

    private void isCurrentEventEnded() {
        LocalDateTime currentDate = LocalDateTime.now();
        if (this.currentEvent.end().compareTo(currentDate) <= 0) {
            this.handleEndEvent(this.currentEvent);
            this.currentEvent = null;
        }
    }

    private void eventsManager() {
        while(true) {
            if (this.currentEvent != null) {
                this.isCurrentEventEnded();
            }
            isAnyEventBegun();
            try {
                Thread.sleep(2000);
            } catch (Exception e) {}
        }
    }

    public abstract void handleEndEvent(T event);

    public abstract void handleStartEvent(T event);
}
