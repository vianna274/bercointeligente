package org.ufrgs.engsw.berco.event;

import org.ufrgs.engsw.berco.data.AquecedorEvent;
import org.ufrgs.engsw.berco.data.CameraEvent;
import org.ufrgs.engsw.berco.data.Event;
import org.ufrgs.engsw.berco.equipment.Camera;

import java.util.List;

public class Dispatcher {

    private Queue queue;
    private List<EventListener<AquecedorEvent>> aquecedorListeners;
    private List<EventListener<CameraEvent>> cameraListeners;

    public Dispatcher(Queue queue, List<EventListener<AquecedorEvent>> aquecedorListeners, List<EventListener<CameraEvent>> cameraListeners) {
        this.queue = queue;
        this.aquecedorListeners = aquecedorListeners;
        this.cameraListeners = cameraListeners;
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
    }
}
