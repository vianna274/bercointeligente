package events.queue;

import events.entity.Evento;

import java.util.ArrayList;

public class QueueDeEventos {

    static ArrayList<Evento> queueDeEventos = new ArrayList<Evento>();

    public static ArrayList<Evento> getInstance()
    {
        return queueDeEventos;
    }

    public static void listEventsOnQueue(){
        int j =0;
        for (Evento e: queueDeEventos) {
            System.out.println("evento " + j++);
            System.out.println(e);
        }
    }
}
