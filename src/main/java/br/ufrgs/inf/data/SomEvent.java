package br.ufrgs.inf.data;

import br.ufrgs.inf.data.domain.EquipmentStatus;
import br.ufrgs.inf.data.domain.MusicVolume;
import br.ufrgs.inf.data.domain.Song;

import java.time.LocalDateTime;

public class SomEvent extends DefaultEvent {

    private MusicVolume musicVolume;
    private Song currentSong;
    private EquipmentStatus equipmentStatus;

    private SomEvent(String name, LocalDateTime end) {
        super(name, end);
    }

    public SomEvent(String name, LocalDateTime end, MusicVolume volume, Song currentSong, EquipmentStatus equipmentStatus) {
        super(name, end);
        this.equipmentStatus = equipmentStatus;
        this.musicVolume = volume;
        this.currentSong = currentSong;
    }

    public SomEvent(String name, LocalDateTime end, Song currentSong, EquipmentStatus equipmentStatus) {
        super(name, end);
        this.equipmentStatus = equipmentStatus;
        this.currentSong = currentSong;
    }

    public SomEvent(String name, LocalDateTime begin, LocalDateTime end, MusicVolume volume, Song currentSong, EquipmentStatus equipmentStatus) {
        super(name, begin, end);
        this.equipmentStatus = equipmentStatus;
        this.musicVolume = volume;
        this.currentSong = currentSong;
    }

    public SomEvent(String name, LocalDateTime begin, LocalDateTime end, Song currentSong, EquipmentStatus equipmentStatus) {
        super(name, begin, end);
        this.equipmentStatus = equipmentStatus;
        this.currentSong = currentSong;
    }

    public static SomEvent defaultInstance() {
        final LocalDateTime now = LocalDateTime.now();
        return new SomEvent("", now, now, MusicVolume.values()[0], Song.values()[0], EquipmentStatus.values()[0]);
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
        return "'EquipmentStatus: '" + this.equipmentStatus + "', MusicVolume: '" + this.musicVolume +
                "' , Song: '" + this.currentSong + "'\n";
    }
}
