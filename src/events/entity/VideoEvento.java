package events.entity;

import events.queue.QueueDeEventos;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class VideoEvento extends Evento {

    @Override
    Evento findEventById(String eventId) {
        for (Evento eventFromQueue: QueueDeEventos.getInstance()) {
            if(eventFromQueue.getId().equals(eventId)){
                return eventFromQueue;
            }
        }
        return null;
    }
    @Override
    public LocalDateTime getInicio(String idEvento) {
        return findEventById(idEvento).inicio;
    }

    @Override
    public LocalDateTime getFim(String idEvento) {
        return findEventById(idEvento).fim;
    }

    @Override
    public LocalDateTime setInicio(LocalDateTime time) {
        inicio = time;
        return inicio;
    }

    @Override
    public LocalDateTime setFim(LocalDateTime time) {
        fim = time;
        return fim;
    }

    @Override
    public Status getStatus(String idEvento) {
        return findEventById(idEvento).status;
    }

    /*Returns baby status based on camera, on this case is randomly*/
    //TODO: random baby status function
    public BabyStatus getBabyStatus(){
        return BabyStatus.AWAKE;
    }

    @Override
    public String toString(){
        return  "id: " + this.getId() + "\n inicio: '" + this.inicio + "\n fim: '" + this.fim + "\n baby status: '" + this.getBabyStatus()  + "\n aparelho status: '" + this.status + "'";
    }
}
