package org.ufrgs.engsw.berco.data;

import java.time.LocalDateTime;

/**
 * Common event behavior.
 */
public interface Event {

    /**
     * Event's id.
     */
    String id();

    /**
     * Event's start time.
     */
    LocalDateTime start();

    /**
     * Event's end time.
     */
    LocalDateTime end();
}
