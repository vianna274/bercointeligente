package br.ufrgs.inf.data.events;

import br.ufrgs.inf.data.domain.EventName;

import java.time.LocalDateTime;

/**
 * Common event behavior.
 */
public interface Event {

    EventName getName();

    /**
     * Event's id.
     */
    String getId();

    /**
     * Event's start time.
     */
    LocalDateTime getStart();

    /**
     * Event's end time.
     */
    LocalDateTime getEnd();
}
