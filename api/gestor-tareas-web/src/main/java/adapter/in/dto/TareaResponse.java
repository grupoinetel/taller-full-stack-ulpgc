package adapter.in.dto;

import domain.model.Tarea;
import lombok.Getter;

import java.util.Date;

@Getter
public class TareaResponse {

    private final Long id;
    private final String numeroTarea;
    private final String descripcion;
    private final Date fechaCreacion;
    private final Date fechaLimite;

    public TareaResponse(Tarea tarea) {
        this.id = tarea.getId();
        this.numeroTarea = tarea.getNumeroTarea();
        this.descripcion = tarea.getDescripcion();
        this.fechaCreacion = tarea.getFechaCreacion();
        this.fechaLimite = tarea.getFechaLimite();
    }
}
