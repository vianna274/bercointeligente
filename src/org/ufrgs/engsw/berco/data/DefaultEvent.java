package org.ufrgs.engsw.berco.data;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class DefaultEvent implements Event {

    private LocalDateTime start;
    private LocalDateTime end;

    public DefaultEvent(LocalDateTime end) {
        this.start = LocalDateTime.now();
        this.end = end;
    }

    public DefaultEvent(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String id() {
        return UUID.randomUUID().toString();
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
