package br.ufrgs.inf.equipment;

import br.ufrgs.inf.data.domain.BabyStatus;
import br.ufrgs.inf.data.domain.EquipmentStatus;
import br.ufrgs.inf.data.domain.Recording;

import java.util.function.Function;

public class Camera extends Component {

    private Recording recording;
    private BabyStatus babyStatus;

    public Camera() {
        super();
        this.recording = Recording.OFF;
        this.babyStatus = BabyStatus.SLEEPING;
    }

    public void recordingControl(Recording recording){
        this.recording = recording;
    }

    public Recording getRecording() { return recording; }

    public BabyStatus getBabyStatus() {
        return babyStatus;
    }

    public void setBabyStatus(BabyStatus babyStatus) {
        this.babyStatus = babyStatus;
    }
}
