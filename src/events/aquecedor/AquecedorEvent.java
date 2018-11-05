package events.aquecedor;

import logica.crudeventos.AdminEventos;
import logica.displaystatus.DisplayStatus;

import java.util.Date;
import java.util.EventObject;

public class AquecedorEvent extends EventObject {
    private Action action;
    private AdminEventos admin;
    private DisplayStatus displayStatus;
    private Date begin;
    private Date end;

    public AquecedorEvent(DisplayStatus source, Action action) {
        super(source);
        this.displayStatus = source;
        this.action = action;
        this.begin = null;
        this.end = null;
    }


    public AquecedorEvent(AdminEventos source, Action action) {
        super(source);
        this.admin = source;
        this.action = action;
        this.begin = null;
        this.end = null;
    }

    public AquecedorEvent(AdminEventos source, Action action, Date begin, Date end) {
        super(source);
        this.admin = source;
        this.action = action;
        this.begin = begin;
        this.end = end;
    }

    public Action getAction() {
        return action;
    }

    public DisplayStatus getDisplayStatus() { return this.displayStatus; }
    public Date getBegin() { return begin; }
    public Date getEnd() { return end; }
}
