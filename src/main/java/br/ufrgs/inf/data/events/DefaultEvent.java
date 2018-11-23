package br.ufrgs.inf.data.events;

import br.ufrgs.inf.data.domain.EventName;
import br.ufrgs.inf.data.domain.Operation;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class DefaultEvent implements Event {

    private EventName name;
    private Operation operation;
    private LocalDateTime start;
    private LocalDateTime end;
    private String id;

    public DefaultEvent(Operation operation, EventName name, LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.operation = operation;
        this.name = name;
        this.end = end;
        this.id = UUID.randomUUID().toString();
    }

    public DefaultEvent(Operation operation, EventName name, LocalDateTime start, LocalDateTime end, String id) {
        this.start = start;
        this.end = end;
        this.operation = operation;
        this.id = id;
        this.name = name;
    }

    public DefaultEvent(Operation operation, String id) {
        this.operation = operation;
        this.id = id;
        this.start = LocalDateTime.now();
        this.end = null;
    }

    public String getId() { return this.id; }

    public EventName getName() {
        return this.name;
    }

    public void setName(EventName name) {
        this.name = name;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public Operation getOperation() {
        return operation;
    }
}
