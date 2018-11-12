package org.ufrgs.engsw.berco.data;

import org.ufrgs.engsw.berco.data.domain.EquipmentStatus;
import org.ufrgs.engsw.berco.data.domain.MusicVolume;
import org.ufrgs.engsw.berco.data.domain.Song;

import java.time.LocalDateTime;

public class SomEvent extends DefaultEvent {

    private MusicVolume musicVolume;
    private Song currentSong;
    private EquipmentStatus equipmentStatus;

    private SomEvent(LocalDateTime end) {
        super(end);
    }

    public SomEvent(LocalDateTime end, MusicVolume volume, Song currentSong, EquipmentStatus equipmentStatus) {
        super(end);
        this.equipmentStatus = equipmentStatus;
        this.musicVolume = volume;
        this.currentSong = currentSong;
    }

    public SomEvent(LocalDateTime end, Song currentSong, EquipmentStatus equipmentStatus) {
        super(end);
        this.equipmentStatus = equipmentStatus;
        this.currentSong = currentSong;
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
