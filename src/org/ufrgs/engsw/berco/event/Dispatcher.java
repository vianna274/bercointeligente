package org.ufrgs.engsw.berco.event;

import org.ufrgs.engsw.berco.data.*;
import org.ufrgs.engsw.berco.equipment.Camera;
import org.ufrgs.engsw.berco.equipment.Luz;

import java.util.List;

public class Dispatcher {

    private Queue queue;
    private List<EventListener<AquecedorEvent>> aquecedorListeners;
    private List<EventListener<CameraEvent>> cameraListeners;
    private List<EventListener<LuzEvent>> luzListeners;
    private List<EventListener<MobileEvent>> mobileListeners;
    private List<EventListener<SomEvent>> somListeners;

    public Dispatcher(Queue queue, List<EventListener<AquecedorEvent>> aquecedorListeners,
                      List<EventListener<CameraEvent>> cameraListeners, List<EventListener<LuzEvent>> luzListeners,
                      List<EventListener<MobileEvent>> mobileListeners, List<EventListener<SomEvent>> somListeners) {
        this.queue = queue;
        this.aquecedorListeners = aquecedorListeners;
        this.cameraListeners = cameraListeners;
        this.luzListeners = luzListeners;
        this.mobileListeners = mobileListeners;
        this.somListeners = somListeners;
    }

    public void dispatch() {
        Event event = queue.dequeue();
        if(event == null) return;
        if (event instanceof AquecedorEvent) {
            aquecedorListeners.forEach(listener -> listener.onEvent((AquecedorEvent) event));
            //PRINT JUST FOR TESTING
            System.out.printf("Aquecedor event : " + event.toString());
        }
        if (event instanceof CameraEvent) {
            cameraListeners.forEach(listener -> listener.onEvent((CameraEvent) event));
            //PRINT JUST FOR TESTING
            System.out.printf("Camera event : " + event.toString());
        }
        if (event instanceof LuzEvent) {
            luzListeners.forEach(listener -> listener.onEvent((LuzEvent) event));
            //PRINT JUST FOR TESTING
            System.out.printf("Luz event : " + event.toString());
        }
        if (event instanceof MobileEvent) {
            mobileListeners.forEach(listener -> listener.onEvent((MobileEvent) event));
            //PRINT JUST FOR TESTING
            System.out.printf("Mobile event : " + event.toString());
        }
        if (event instanceof SomEvent) {
            somListeners.forEach(listener -> listener.onEvent((SomEvent) event));
            //PRINT JUST FOR TESTING
            System.out.printf("Som event : " + event.toString());
        }

    }

    public void run () {
        while(true) {
            this.dispatch();
            try {
                Thread.sleep(300);
            } catch (Exception e) {}
        }
    }
}
