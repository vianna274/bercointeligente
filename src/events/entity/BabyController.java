package events.entity;

public class BabyController {

    private VideoEvento videoEvento = new VideoEvento();
    private BabyStatus babyStatus;


    public void getBabyStatus(){
        videoEvento.getBabyStatus();
    }
}
