package events.aquecedor;

import logica.crudeventos.AdminEventos;

import java.util.EventObject;

public class AquecedorEvent extends EventObject {
    private Action action;
    private AdminEventos admin;

    public AquecedorEvent(AdminEventos source, Action action) {
        super(source);
        this.admin = source;
        this.action = action;
    }

    public Action getAction() {
        return action;
    }
    public AdminEventos getSource() { return this.admin; }
}
