package br.ufrgs.inf.handler;

import br.ufrgs.inf.data.events.DefaultEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Scheduler<T extends DefaultEvent> {

    private ArrayList<T> events;
    private T currentEvent;

    private Function<T, Integer> startEventCallback;
    private Function<T, Integer> endEventCallback;
    private Function<T, Integer> pauseEventCallback;
    private Function<T, Integer> resumeEventCallback;

    public Scheduler() {
        this.events = new ArrayList<>();

        final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(this::run, 0, 1, TimeUnit.SECONDS);
    }

    public void scheduleEvent(T event) {
        switch (event.getOperation()) {
            case POST:
                this.events.add(event);
                break;
            case PUT:
                this.replaceScheduled(event);
                break;
            case DELETE:
                this.removeEvent(event);
                break;
            case PAUSE:
                this.pauseEvent(event);
                break;
            case RESUME:
                this.resumeEvent(event);
                break;
            case ACTION:
                startEvent(event);
                break;
            case STATUS_CHANGED:
                System.out.println("[Warn] Schedule recebeu STATUS_CHANGED");
                break;
        }
    }

    public void pauseEvent(T event) {
        if (pauseEventCallback != null)
            pauseEventCallback.apply(event);
    }

    public void resumeEvent(T event) {
        if (resumeEventCallback != null)
            resumeEventCallback.apply(event);
    }

    public void startEvent(T event) {
        if (startEventCallback != null)
            startEventCallback.apply(event);
    }

    ;

    public void endEvent(T event) {
        if (endEventCallback != null)
            endEventCallback.apply(event);
    }

    ;

    public void removeEvent(T event) {
        final String id = event.getId();
        if (currentEvent != null && currentEvent.getId().equals(id)) {
            endEventCallback.apply(event);
            currentEvent = null;
        }
        Optional<T> eventToRemove = this.events.stream().filter(e -> e.getId().equals(id)).findFirst();
        eventToRemove.ifPresent(e -> events.remove(e));
    }

    public void replaceScheduled(T event) {
        IntStream.range(0, this.events.size())
                .filter(idx -> this.events.get(idx).getId().equals(event.getId()))
                .map(idx -> {
                    this.events.set(idx, event);
                    return idx;
                })
                .findFirst()
                .orElseGet(() -> {
                    System.out.println("Criei");
                    this.events.add(event);
                    return 0;
                });

        ;
    }

    private void run() {
        LocalDateTime currentDate = LocalDateTime.now();

        if (currentEvent != null && currentEvent.getEnd() != null && currentEvent.getEnd().compareTo(currentDate) <= 0) {
            System.out.println("Ending " + currentEvent);
            endEvent(currentEvent);
            currentEvent = null;
        }

        for (T event : this.events) {
            if (event.getStart().compareTo(currentDate) <= 0) {
                System.out.println("Starting " + event);
                currentEvent = event;
                this.events.remove(event);
                startEvent(currentEvent);
                break;
            }
        }
    }

    public void setStartEventCallback(Function<T, Integer> startEventCallback) {
        this.startEventCallback = startEventCallback;
    }

    public void setEndEventCallback(Function<T, Integer> endEventCallback) {
        this.endEventCallback = endEventCallback;
    }

    public void setPauseEventCallback(Function<T, Integer> pauseEventCallback) {
        this.pauseEventCallback = pauseEventCallback;
    }

    public void setResumeEventCallback(Function<T, Integer> resumeEventCallback) {
        this.resumeEventCallback = resumeEventCallback;
    }
}
