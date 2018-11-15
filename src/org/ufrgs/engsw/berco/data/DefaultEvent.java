package org.ufrgs.engsw.berco.data;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class DefaultEvent implements Event {

    private LocalDateTime start;
    private LocalDateTime end;
    private String id;

    public DefaultEvent(LocalDateTime end) {
        this.start = LocalDateTime.now();
        this.end = end;
        this.id = UUID.randomUUID().toString();
    }

    public DefaultEvent(LocalDateTime start, LocalDateTime end) {
        this(end);
        this.start = start;
    }

    public String getId() { return this.id; }

    @Override
    public String id() {
        return this.id;
    }

    @Override
    public LocalDateTime start() {
        return this.start;
    }

    @Override
    public LocalDateTime end() {
        return this.end;
    }

    @Override
    public String toString(){
        return "start: '" + this.start + "', end: '" + this.end + "'";
    }
}
