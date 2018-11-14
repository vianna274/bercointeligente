package org.ufrgs.engsw.berco.equipment;

import org.ufrgs.engsw.berco.data.domain.EquipmentStatus;
import org.ufrgs.engsw.berco.data.domain.MusicVolume;
import org.ufrgs.engsw.berco.data.domain.Song;

import java.util.function.Function;

public class Som {

    private EquipmentStatus equipmentStatus;
    private Song currentSong;
    private MusicVolume musicVolume;

    private Function<String, Integer> statusCallback;
    private Function<String, Integer> currentSongCallback;
    private Function<String, Integer> musicVolumeCallback;

    public Som() {
        this.equipmentStatus = EquipmentStatus.OFF;
        this.currentSong = Song.FIRST;
        this.musicVolume = MusicVolume.MEDIUM;
        this.statusCallback = null;
        this.currentSongCallback = null;
        this.musicVolumeCallback = null;
    }

    public EquipmentStatus getEquipmentStatus() {
        return equipmentStatus;
    }

    public void setEquipmentStatus(EquipmentStatus equipmentStatus) {
        this.equipmentStatus = equipmentStatus;
        if (statusCallback != null)
            statusCallback.apply(equipmentStatus.toString());
    }

    public Song getCurrentSong() {
        return currentSong;
    }

    public void setCurrentSong(Song currentSong) {
        this.currentSong = currentSong;
        if (currentSongCallback != null)
            currentSongCallback.apply(currentSong.toString());
    }

    public MusicVolume getMusicVolume() {
        return musicVolume;
    }

    public void setMusicVolume(MusicVolume musicVolume) {
        this.musicVolume = musicVolume;
        if (musicVolumeCallback != null)
            musicVolumeCallback.apply(musicVolume.toString());
    }

    public void toggle() {
        this.equipmentStatus = this.equipmentStatus == EquipmentStatus.OFF ? EquipmentStatus.ON : EquipmentStatus.OFF;
        if (statusCallback != null)
            statusCallback.apply(equipmentStatus.toString());
    }
    public void setStatusCallback(Function<String, Integer> statusCallback) {
        this.statusCallback = statusCallback;
    }

    public void setCurrentSongCallback(Function<String, Integer> currentSongCallback) {
        this.currentSongCallback = currentSongCallback;
    }

    public void setMusicVolumeCallback(Function<String, Integer> musicVolumeCallback) {
        this.musicVolumeCallback = musicVolumeCallback;
    }

}
