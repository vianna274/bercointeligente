package org.ufrgs.engsw.berco.handler;

import org.ufrgs.engsw.berco.data.Event;
import org.ufrgs.engsw.berco.event.Queue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduler {

    private ArrayList<Event> events;

    private Queue queue;

    public Scheduler(Queue queue) {
        this.events = new ArrayList<>();
        this.queue = queue;

        final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(this::run, 0, 1, TimeUnit.SECONDS);
    }

    public void scheduleEvent(Event event) {
        this.events.add(event);
    }

    public void removeEvent(final String id) {
        Optional<Event> event = this.events.stream().filter(e -> e.id().equals(id)).findFirst();
        event.ifPresent(e -> events.remove(e));
    }

    private void run() {
        LocalDateTime currentDate = LocalDateTime.now();

        for(Event event : this.events) {
            if (event.start().compareTo(currentDate) <= 0) {
                this.events.remove(event);
                this.queue.enqueue(event);
                break;
            }
        }
    }
}
