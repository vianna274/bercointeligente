package br.ufrgs.inf.controller;

import br.ufrgs.inf.data.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EventManager<T extends Event> {

    private List<T> events;

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
}
