package br.ufrgs.inf.event;

import br.ufrgs.inf.data.events.*;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Dispatcher {

    private Queue queue;
    private List<EventListener<AquecedorEvent>> aquecedorListeners;
    private List<EventListener<CameraEvent>> cameraListeners;
    private List<EventListener<LuzEvent>> luzListeners;
    private List<EventListener<MobileEvent>> mobileListeners;
    private List<EventListener<SomEvent>> somListeners;

    public Dispatcher(Queue queue,
                      List<EventListener<AquecedorEvent>> aquecedorListeners,
                      List<EventListener<CameraEvent>> cameraListeners,
                      List<EventListener<LuzEvent>> luzListeners,
                      List<EventListener<MobileEvent>> mobileListeners,
                      List<EventListener<SomEvent>> somListeners) {

        this.queue = queue;
        this.aquecedorListeners = aquecedorListeners;
        this.cameraListeners = cameraListeners;
        this.luzListeners = luzListeners;
        this.mobileListeners = mobileListeners;
        this.somListeners = somListeners;

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        executorService.scheduleAtFixedRate(this::dispatch, 0, 500, TimeUnit.MILLISECONDS);
    }

    private void dispatch() {
        Event event = queue.dequeue();

        if(event == null) return;

        if (event instanceof AquecedorEvent) {
            aquecedorListeners.forEach(listener -> listener.onEvent((AquecedorEvent) event));
            System.out.printf("Aquecedor event : " + event.toString());
        }

        if (event instanceof CameraEvent) {
            cameraListeners.forEach(listener -> listener.onEvent((CameraEvent) event));
            System.out.printf("Camera event : " + event.toString());
        }

        if (event instanceof LuzEvent) {
            luzListeners.forEach(listener -> listener.onEvent((LuzEvent) event));
            //PRINT JUST FOR TESTING
            System.out.printf("Luz event : " + event.toString());
        }

        if (event instanceof MobileEvent) {
            mobileListeners.forEach(listener -> listener.onEvent((MobileEvent) event));
            System.out.printf("Mobile event : " + event.toString());
        }

        if (event instanceof SomEvent) {
            somListeners.forEach(listener -> listener.onEvent((SomEvent) event));
            System.out.printf("Som event : " + event.toString());
        }
    }
}