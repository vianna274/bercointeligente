package events.entity;

import java.time.LocalDateTime;

public abstract class Evento {

   private static int i =0; //atributo de classe
   protected String id = Integer.toString(i);
   protected LocalDateTime inicio = null;
   protected LocalDateTime fim = null;
   protected Status status = Status.NAO_INICIADO;

   public String setId(){
      return Integer.toString(i++);
   }

   public String getId(){return id;}

   abstract Evento findEventById(String eventId);

   abstract LocalDateTime getInicio(String idEvento);

   abstract LocalDateTime getFim(String idEvento);

   abstract LocalDateTime setInicio(LocalDateTime time);

   abstract LocalDateTime setFim(LocalDateTime time);

   abstract Status getStatus(String idEvento);
}
