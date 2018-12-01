package br.ufrgs.inf.equipment;

import br.ufrgs.inf.data.domain.EquipmentStatus;
import br.ufrgs.inf.data.domain.MusicVolume;
import br.ufrgs.inf.data.domain.Song;

import java.util.function.Function;

public class Som extends Component {

    private Song currentSong;
    private MusicVolume musicVolume;

    public Som() {
        super();
        this.currentSong = Song.FIRST;
        this.musicVolume = MusicVolume.MEDIUM;
    }

    public Song getCurrentSong() {
        return currentSong;
    }

    public void setCurrentSong(Song currentSong) {
        this.currentSong = currentSong;
    }

    public MusicVolume getMusicVolume() {
        return musicVolume;
    }

    public void setMusicVolume(MusicVolume musicVolume) {
        this.musicVolume = musicVolume;
    }
}
