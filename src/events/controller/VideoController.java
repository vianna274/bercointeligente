package events.controller;

import events.crudeventos.CrudEAssociacaoDeEventosFacade;
import events.entity.Evento;

public class VideoController {

    private CrudEAssociacaoDeEventosFacade crudEAssociacaoDeEventosFacade;

    public VideoController() {
        crudEAssociacaoDeEventosFacade = new CrudEAssociacaoDeEventosFacade();
    }

    public void createEvento(Evento evento){
        crudEAssociacaoDeEventosFacade.sendToQueue(evento);
    }
}
