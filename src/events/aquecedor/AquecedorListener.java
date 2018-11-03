package events.aquecedor;

public interface AquecedorListener {
    void sensorReceived(AquecedorEvent event);
}
