package org.ufrgs.engsw.berco.data;

import org.ufrgs.engsw.berco.data.domain.*;

public class ExibicaoStatus {

    private EquipmentStatus cameraStatus, luzStatus, mobileStatus,
            somStatus, aquecedorStatus;
    private Recording recording;
    private Temperature temperature;
    private MobileSpeed mobileSpeed;
    private Song song;
    private MusicVolume musicVolume;

    public ExibicaoStatus(EquipmentStatus cameraStatus, EquipmentStatus luzStatus, EquipmentStatus mobileStatus,
                         EquipmentStatus somStatus, EquipmentStatus aquecedorStatus, Recording recording,
                         Temperature temperature, MobileSpeed mobileSpeed, Song song, MusicVolume musicVolume) {
        this.cameraStatus = cameraStatus;
        this.luzStatus = luzStatus;
        this.mobileStatus = mobileStatus;
        this.somStatus = somStatus;
        this.aquecedorStatus = aquecedorStatus;
        this.recording = recording;
        this.temperature = temperature;
        this.mobileSpeed = mobileSpeed;
        this.song = song;
        this.musicVolume = musicVolume;
    }

    public EquipmentStatus getCameraStatus() {
        return cameraStatus;
    }

    public EquipmentStatus getLuzStatus() {
        return luzStatus;
    }

    public EquipmentStatus getMobileStatus() {
        return mobileStatus;
    }

    public EquipmentStatus getSomStatus() {
        return somStatus;
    }

    public EquipmentStatus getAquecedorStatus() {
        return aquecedorStatus;
    }

    public Recording getRecording() {
        return recording;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public MobileSpeed getMobileSpeed() {
        return mobileSpeed;
    }

    public Song getSong() {
        return song;
    }

    public MusicVolume getMusicVolume() {
        return musicVolume;
    }

    public String toString() {
        return "[Camera] EquipmentStatus: '" + this.cameraStatus + "', Recording: '" + this.recording + "'\n" +
                "[Som] EquipmentStatus: '" + this.somStatus + "', Song: '" + this.song + "', Volume: '" + this.musicVolume + "'\n" +
                "[Aquecedor] EquipmentStatus: '" + this.aquecedorStatus + "', Temperature: '" + this.temperature + "'\n" +
                "[Mobile] EquipmentStatus: '" + this.mobileStatus + "', Speed: '" + this.mobileSpeed + "'\n" +
                "[Luz] EquipmentStatus: '" + this.luzStatus + "'\n";
    }
}
