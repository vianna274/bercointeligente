package org.ufrgs.engsw.berco.event;

public interface EventListener<T> {

    void onEvent(T event);
}
