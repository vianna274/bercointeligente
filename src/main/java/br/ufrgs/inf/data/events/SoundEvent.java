package br.ufrgs.inf.data.events;

import br.ufrgs.inf.data.domain.BabyStatus;
import br.ufrgs.inf.data.domain.EventName;
import br.ufrgs.inf.data.domain.Operation;

import java.time.LocalDateTime;

public class SoundEvent extends DefaultEvent {
    private BabyStatus babyStatus;

    public SoundEvent(Operation operation, String id) {
        super(operation, id);
    }

    public SoundEvent(Operation operation, EventName name, BabyStatus babyStatus,
                      LocalDateTime begin, LocalDateTime end) {
        super(operation, name, begin, end);
        this.babyStatus = babyStatus;
    }
    @Override
    public String toString(){
        return "SoundEvent{ " +
                "operation=" + getOperation() +
                " }\n";
    }
}
