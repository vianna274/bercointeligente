package br.ufrgs.inf.equipment;

import br.ufrgs.inf.data.domain.EquipmentStatus;
import br.ufrgs.inf.data.domain.MobileSpeed;

import java.util.function.Function;

public class Mobile {

    private EquipmentStatus equipmentStatus;
    private MobileSpeed speed;

    private Function<String, Integer> equipmentCallback;
    private Function<String, Integer> mobileSpeedCallback;

    public Mobile() {
        this.equipmentStatus = EquipmentStatus.OFF;
        this.speed = MobileSpeed.MEDIUM;
        this.equipmentCallback = null;
        this.mobileSpeedCallback = null;
    }

    public void toggle() {
        this.equipmentStatus = this.equipmentStatus == EquipmentStatus.OFF ? EquipmentStatus.ON : EquipmentStatus.OFF;
        if (equipmentCallback != null)
            equipmentCallback.apply(this.equipmentStatus.toString());
    }

    public EquipmentStatus getEquipmentStatus() {
        return equipmentStatus;
    }

    public void setEquipmentStatus(EquipmentStatus equipmentStatus) {
        this.equipmentStatus = equipmentStatus;
        if (equipmentCallback != null)
            equipmentCallback.apply(this.equipmentStatus.toString());
    }

    public MobileSpeed getSpeed() {
        return speed;
    }

    public void setSpeed(MobileSpeed speed) {
        this.speed = speed;
        if (mobileSpeedCallback != null)
            mobileSpeedCallback.apply(speed.toString());
    }

    public void setEquipmentCallback(Function<String, Integer> equipmentCallback) {
        this.equipmentCallback = equipmentCallback;
    }

    public void setMobileSpeedCallback(Function<String, Integer> mobileSpeedCallback) {
        this.mobileSpeedCallback = mobileSpeedCallback;
    }
}
