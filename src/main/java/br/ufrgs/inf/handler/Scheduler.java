package br.ufrgs.inf.handler;

import br.ufrgs.inf.data.Event;
import br.ufrgs.inf.event.Queue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

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
        Optional<Event> event = this.events.stream().filter(e -> e.getId().equals(id)).findFirst();
        event.ifPresent(e -> events.remove(e));
    }

    public void replaceScheduled(Event event) {
        IntStream.range(0, this.events.size())
                 .filter(idx -> this.events.get(idx).getId().equals(event.getId()))
                 .findFirst()
                 .ifPresent(idx -> this.events.set(idx, event));
    }

    private void run() {
        LocalDateTime currentDate = LocalDateTime.now();

        for(Event event : this.events) {
            if (event.getStart().compareTo(currentDate) <= 0) {
                this.events.remove(event);
                this.queue.enqueue(event);
                break;
            }
        }
    }
}
