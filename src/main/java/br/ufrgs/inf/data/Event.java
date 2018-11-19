package br.ufrgs.inf.data;

import java.time.LocalDateTime;

/**
 * Common event behavior.
 */
public interface Event {

    String getName();

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
