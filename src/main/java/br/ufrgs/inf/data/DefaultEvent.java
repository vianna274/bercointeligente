package br.ufrgs.inf.data;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class DefaultEvent implements Event {

    private String name;
    private LocalDateTime start;
    private LocalDateTime end;
    private String id;

    public DefaultEvent(String name, LocalDateTime end) {
        this.name = name;
        this.start = LocalDateTime.now();
        this.end = end;
        this.id = UUID.randomUUID().toString();
    }

    public DefaultEvent(String name, LocalDateTime start, LocalDateTime end) {
        this(name, end);
        this.start = start;
    }

    public String getId() { return this.id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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

    public void setId(String id) {
        this.id = id;
    }
}
