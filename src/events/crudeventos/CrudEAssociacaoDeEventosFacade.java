package events.crudeventos;

import events.entity.Evento;
import events.queue.QueueDeEventos;

public class CrudEAssociacaoDeEventosFacade {

    public void sendToQueue(Evento evento){
        QueueDeEventos.getInstance().add(evento);
    }
}
