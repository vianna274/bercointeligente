package br.ufrgs.inf.equipment;

import br.ufrgs.inf.data.domain.BabyBottleStatus;

public class BabyBottle {

    private double preDefTemperature;
    private BabyBottleStatus babyBottleStatus;

    public BabyBottle() {
        this.preDefTemperature = 20.4;
        this.babyBottleStatus = BabyBottleStatus.NOT_READY;
    }

    public BabyBottleStatus getBabyBottleStatus() {
        return this.babyBottleStatus;
    }
    public void toggleBabyBottleStatus(){
        if(this.babyBottleStatus.equals(BabyBottleStatus.READY)){
            this.babyBottleStatus = BabyBottleStatus.NOT_READY;
        }
        else{
            this.babyBottleStatus = BabyBottleStatus.READY;
        }

    }
}
