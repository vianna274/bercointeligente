package br.ufrgs.inf.data;

import br.ufrgs.inf.data.domain.*;

import java.time.LocalDateTime;

public class SomEvent extends DefaultEvent {

    private MusicVolume musicVolume;
    private Song currentSong;
    private EquipmentStatus equipmentStatus;

    public SomEvent(Operation operation, String id) { super(operation, id); }

    public SomEvent(Operation operation, EventName name, LocalDateTime start, LocalDateTime end) {
        super(operation, name, start, end);
    }

    public SomEvent(Operation operation, EventName name, LocalDateTime start) {
        super(operation, name, start);
    }

    public SomEvent(Operation operation, EventName name, LocalDateTime end, MusicVolume volume, Song currentSong, EquipmentStatus equipmentStatus) {
        super(operation, name, end);
        this.equipmentStatus = equipmentStatus;
        this.musicVolume = volume;
        this.currentSong = currentSong;
    }

    public SomEvent(Operation operation, EventName name, LocalDateTime end, Song currentSong, EquipmentStatus equipmentStatus) {
        super(operation, name, end);
        this.equipmentStatus = equipmentStatus;
        this.currentSong = currentSong;
    }

    public SomEvent(Operation operation, EventName name, LocalDateTime begin, LocalDateTime end, MusicVolume volume, Song currentSong, EquipmentStatus equipmentStatus) {
        super(operation, name, begin, end);
        this.equipmentStatus = equipmentStatus;
        this.musicVolume = volume;
        this.currentSong = currentSong;
    }

    public SomEvent(Operation operation, EventName name, LocalDateTime begin, LocalDateTime end, Song currentSong, EquipmentStatus equipmentStatus) {
        super(operation, name, begin, end);
        this.equipmentStatus = equipmentStatus;
        this.currentSong = currentSong;
    }

    public static SomEvent defaultInstance() {
        final LocalDateTime now = LocalDateTime.now();
        return new SomEvent(Operation.POST, null, now, now, MusicVolume.values()[0], Song.values()[0], EquipmentStatus.values()[0]);
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
