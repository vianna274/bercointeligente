package br.ufrgs.inf.controller;

import br.ufrgs.inf.data.events.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EventManager<T extends Event> {

    private List<T> events;

    private List<Consumer<AquecedorEvent>> heaterListeners;
    private List<Consumer<CameraEvent>> cameraListeners;
    private List<Consumer<SomEvent>> soundListeners;
    private List<Consumer<LuzEvent>> lightListeners;
    private List<Consumer<MobileEvent>> mobileListeners;

    private AquecedorEvent currentHeaterEvent;
    private CameraEvent currentCameraEvent;
    private SomEvent currentSomEvent;
    private LuzEvent currentLuzEvent;
    private MobileEvent currentMobileEvent;

    public EventManager() {
        this.events = new ArrayList<>();
        this.lightListeners = new ArrayList<>();
        this.cameraListeners = new ArrayList<>();
        this.mobileListeners = new ArrayList<>();
        this.soundListeners = new ArrayList<>();
        this.heaterListeners = new ArrayList<>();
    }

    public List<T> getEvents() {
        return events;
    }

    public void addCameraListener(final Consumer<CameraEvent> consumer) {
        this.cameraListeners.add(consumer);
    }

    public void notifyCameraListeners(final CameraEvent event) {
        this.cameraListeners.forEach(s -> s.accept(event));
    }

    public void addHeaterListener(final Consumer<AquecedorEvent> consumer) {
        this.heaterListeners.add(consumer);
    }

    public void notifyHeaterListeners(final AquecedorEvent event) {
        this.heaterListeners.forEach(s -> s.accept(event));
    }

    public void addSoundListener(final Consumer<SomEvent> consumer) {
        this.soundListeners.add(consumer);
    }

    public void notifySoundListeners(final SomEvent event) {
        this.soundListeners.forEach(s -> s.accept(event));
    }

    public void addLightListener(final Consumer<LuzEvent> consumer) {
        this.lightListeners.add(consumer);
    }

    public void notifyLightListeners(final LuzEvent event) {
        this.lightListeners.forEach(s -> s.accept(event));
    }

    public void addMobileListener(final Consumer<MobileEvent> consumer) {
        this.mobileListeners.add(consumer);
    }

    public void notifyMobileListeners(final MobileEvent event) {
        this.mobileListeners.forEach(s -> s.accept(event));
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

    public T replaceById(final T event) {
        final Optional<Integer> idx = IntStream.of(this.events.size() - 1)
            .filter(i -> this.events.get(i).getId().equals(event.getId()))
            .boxed()
            .findFirst();

        idx.ifPresent(i ->
            this.events.set(i, event)
        );

        return event;
    }

    public CameraEvent getCurrentCameraEvent() {
        return currentCameraEvent;
    }

    public SomEvent getCurrentSomEvent() {
        return currentSomEvent;
    }

    public LuzEvent getCurrentLuzEvent() {
        return currentLuzEvent;
    }

    public MobileEvent getCurrentMobileEvent() {
        return currentMobileEvent;
    }

    public void setCurrentCameraEvent(CameraEvent currentCameraEvent) {
        this.currentCameraEvent = currentCameraEvent;
        this.notifyCameraListeners(currentCameraEvent);
    }

    public void setCurrentSomEvent(SomEvent currentSomEvent) {
        this.currentSomEvent = currentSomEvent;
        this.notifySoundListeners(currentSomEvent);
    }

    public void setCurrentLuzEvent(LuzEvent currentLuzEvent) {
        this.currentLuzEvent = currentLuzEvent;
        this.notifyLightListeners(currentLuzEvent);
    }

    public void setCurrentMobileEvent(MobileEvent currentMobileEvent) {
        this.currentMobileEvent = currentMobileEvent;
        this.notifyMobileListeners(currentMobileEvent);
    }

    public AquecedorEvent getCurrentHeaterEvent() {
        return currentHeaterEvent;
    }

    public void setCurrentHeaterEvent(AquecedorEvent currentHeaterEvent) {
        this.currentHeaterEvent = currentHeaterEvent;
        this.notifyHeaterListeners(currentHeaterEvent);
    }
}
