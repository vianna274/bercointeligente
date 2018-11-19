package br.ufrgs.inf.event;

public interface EventListener<T> {

    void onEvent(T event);
}
