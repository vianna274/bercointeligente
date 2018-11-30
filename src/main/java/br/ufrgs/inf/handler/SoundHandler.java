package br.ufrgs.inf.handler;

import br.ufrgs.inf.data.builders.*;
import br.ufrgs.inf.data.domain.BabyStatus;
import br.ufrgs.inf.data.domain.EventName;
import br.ufrgs.inf.data.domain.Operation;
import br.ufrgs.inf.data.events.*;
import br.ufrgs.inf.equipment.Sound;
import br.ufrgs.inf.event.EventListener;
import br.ufrgs.inf.event.Queue;

import java.time.LocalDateTime;

public class SoundHandler implements EventListener<SoundEvent> {

    private Sound sound;
    private Queue queue;
    private Queue uiQueue;

    public SoundHandler(Sound sound, Queue queue, Queue uiQueue) {
        this.sound = sound;
        this.queue = queue;
        this.uiQueue = uiQueue;
    }

    @Override public void onEvent(SoundEvent event) {
        if (event.getName() == EventName.BABY_WAKE_UP)
            this.handleBabyWokeUp();
    }

    private void handleBabyWokeUp() {
        LocalDateTime start = LocalDateTime.now();

        AquecedorEvent aquecedorEvent = new AquecedorEventBuilder()
                .operation(Operation.ACTION)
                .eventName(EventName.BABY_WAKE_UP)
                .start(start)
                .build();

        MobileEvent mobileEvent = new MobileEventBuilder()
                .operation(Operation.ACTION)
                .eventName(EventName.BABY_WAKE_UP)
                .start(start)
                .build();
        SomEvent somEvent = new SomEventBuilder()
                .operation(Operation.ACTION)
                .eventName(EventName.BABY_WAKE_UP)
                .start(start)
                .build();
        LuzEvent luzEvent = new LuzEventBuilder()
                .operation(Operation.ACTION)
                .eventName(EventName.BABY_WAKE_UP)
                .start(start)
                .build();

        queue.enqueue(aquecedorEvent);
        queue.enqueue(mobileEvent);
        queue.enqueue(somEvent);
        queue.enqueue(luzEvent);

        SoundEvent event= new SoundEventBuilder()
                .operation(Operation.STATUS_CHANGED)
                .babyStatus(BabyStatus.AWAKING)
                .build();

        uiQueue.enqueue(event);
    }
}
