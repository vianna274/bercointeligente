package br.ufrgs.inf.equipment;

import br.ufrgs.inf.data.domain.Temperature;


public class Aquecedor extends Component {

    private Temperature temperature;

    public Aquecedor() {
        super();
        this.temperature = Temperature.AMBIENT;
    }

    public void changeTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public Temperature getTemperature() {
        return temperature;
    }
}
