package adapter.in.dto;

import domain.enums.CategoriaTarea;
import domain.enums.EstadoTarea;
import domain.enums.PrioridadTarea;
import domain.model.Tarea;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TareaResponse {

    private final Long id;
    private final Integer numero;
    private final String titulo;
    private final String imagenUrl;
    private final Date fechaLimite;
    private final EstadoTarea estado;
    private final PrioridadTarea prioridad;

    public TareaResponse(Tarea tarea) {
        this.id = tarea.getId();
        this.numero = tarea.getNumero();
        this.titulo = tarea.getTitulo();
        this.imagenUrl = tarea.getImagenUrl();
        this.estado = tarea.getEstado();
        this.prioridad = tarea.getPrioridad();
        this.fechaLimite = tarea.getFechaLimite();
    }
}
