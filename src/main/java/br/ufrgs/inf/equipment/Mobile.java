package br.ufrgs.inf.equipment;

import br.ufrgs.inf.data.domain.EquipmentStatus;
import br.ufrgs.inf.data.domain.MobileSpeed;

import java.util.function.Function;

public class Mobile extends Component {

    private MobileSpeed speed;

    public Mobile() {
        super();
        this.speed = MobileSpeed.SLOW;
    }

    public MobileSpeed getSpeed() {
        return speed;
    }

    public void setSpeed(MobileSpeed speed) {
        this.speed = speed;
    }

}
