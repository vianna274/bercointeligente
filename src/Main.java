import events.controller.VideoController;
import events.entity.Evento;
import events.entity.VideoEvento;
import events.queue.QueueDeEventos;
import logica.crudeventos.AdminEventos;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] arg) {
        AdminEventos admin = new AdminEventos();
        VideoController videoController = new VideoController();
        Evento e1 = new VideoEvento();
        ((VideoEvento) e1).setFim(LocalDateTime.of(2018, 02, 10, 3, 4));
        ((VideoEvento) e1).setInicio(LocalDateTime.now());
        
        videoController.createEvento(e1);

        QueueDeEventos.listEventsOnQueue();
    }
}
