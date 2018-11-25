package br.ufrgs.inf.controller;

import br.ufrgs.inf.data.events.CameraEvent;
import br.ufrgs.inf.data.events.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EventManager<T extends Event> {

    private List<T> events;

    private CameraEvent currentCameraEvent;

    public EventManager() {
        this.events = new ArrayList<>();
    }

    public List<T> getEvents() {
        return events;
    }

    public void setEvents(List<T> events) {
        this.events = events;
    }

    public T add(final T event) {
        this.events.add(event);
        return event;
    }

    public boolean remove(final T event) {
        return this.events.remove(event);
    }

    public List<T> listEventByClass(final Class<T> clazz) {
        return this.events.stream()
                          .filter(c -> c.getClass().equals(clazz))
                          .map(clazz::cast)
                          .collect(Collectors.toList());
    }

    public T replaceById(final T event) {
        final Optional<Integer> idx = IntStream.of(this.events.size() - 1)
            .filter(i -> this.events.get(i).getId().equals(event.getId()))
            .boxed()
            .findFirst();

        idx.ifPresent(i ->
            this.events.set(i, event)
        );

        return event;
    }

    public CameraEvent getCurrentCameraEvent() {
        return currentCameraEvent;
    }

    public void setCurrentCameraEvent(CameraEvent currentCameraEvent) {
        this.currentCameraEvent = currentCameraEvent;
    }
}
