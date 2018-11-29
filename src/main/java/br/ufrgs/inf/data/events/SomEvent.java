package br.ufrgs.inf.data.events;

import br.ufrgs.inf.data.domain.*;

import java.time.LocalDateTime;
import java.util.Optional;

public class SomEvent extends DefaultEvent {

    private MusicVolume musicVolume;
    private Song currentSong;
    private EquipmentStatus equipmentStatus;

    public SomEvent(Operation operation, String id) { super(operation, id); }

    public SomEvent(Operation operation, EventName name, LocalDateTime begin, LocalDateTime end, MusicVolume volume,
                    Song currentSong, EquipmentStatus equipmentStatus) {
        super(operation, name, begin, end);
        this.equipmentStatus = equipmentStatus;
        this.musicVolume = volume;
        this.currentSong = currentSong;
    }

    public SomEvent(Operation operation, EventName name, LocalDateTime begin, LocalDateTime end, MusicVolume volume,
                    Song currentSong, EquipmentStatus equipmentStatus, String id) {
        super(operation, name, begin, end, id);
        this.equipmentStatus = equipmentStatus;
        this.musicVolume = volume;
        this.currentSong = currentSong;
    }

    public static SomEvent defaultInstance() {
        final LocalDateTime now = LocalDateTime.now();
        return new SomEvent(Operation.POST, null, now, now, MusicVolume.values()[0], Song.values()[0], EquipmentStatus.values()[0]);
    }

    public static SomEvent merge(SomEvent newEvent, final SomEvent oldEvent) {
        if (newEvent == null) {
            newEvent = defaultInstance();
        }

        final Optional<SomEvent> opt  = Optional.ofNullable(oldEvent);

        opt.map(SomEvent::getCurrentSong).ifPresent(newEvent::setCurrentSong);
        opt.map(SomEvent::getMusicVolume).ifPresent(newEvent::setMusicVolume);
        opt.map(SomEvent::getEquipmentStatus).ifPresent(newEvent::setEquipmentStatus);
        opt.map(SomEvent::getEnd).ifPresent(newEvent::setEnd);
        opt.map(SomEvent::getStart).ifPresent(newEvent::setStart);
        opt.map(SomEvent::getOperation).ifPresent(newEvent::setOperation);

        return newEvent;
    }

    public static SomEvent merge(final SomEvent oldEvent) {
        return merge(defaultInstance(), oldEvent);
    }

    public MusicVolume getMusicVolume() {
        return musicVolume;
    }

    public void setMusicVolume(MusicVolume musicVolume) {
        this.musicVolume = musicVolume;
    }

    public Song getCurrentSong() {
        return currentSong;
    }

    public void setCurrentSong(Song currentSong) {
        this.currentSong = currentSong;
    }


    public EquipmentStatus getEquipmentStatus() {
        return equipmentStatus;
    }

    public void setEquipmentStatus(EquipmentStatus equipmentStatus) {
        this.equipmentStatus = equipmentStatus;
    }

    @Override
    public String toString(){
        return "SomEvent{ " +
                "operation=" + getOperation() +
                ", name=" + getName() +
                ", volume=" + musicVolume +
                ", equipmentStatus=" + equipmentStatus +
                ", song=" + currentSong +
                " }\n";
    }
}
