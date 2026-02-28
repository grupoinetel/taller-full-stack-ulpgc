package adapter.in.dto;

import domain.enums.CategoriaTarea;
import domain.enums.EstadoTarea;
import domain.enums.PrioridadTarea;
import domain.model.Tarea;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
public class TareaResponse {

    private final Long id;
    private final Integer numero;
    private final String titulo;
    private final String imagenUrl;
    private final EstadoTarea estado;
    private final PrioridadTarea prioridad;
    private final CategoriaTarea categoria;
    private final String descripcion;
    private final Date fechaCreacion;
    private final Date fechaLimite;
    private final Integer porcentajeRealizado;
    private final BigDecimal tiempoEstimado;

    public TareaResponse(Tarea tarea) {
        this.id = tarea.getId();
        this.numero = tarea.getNumero();
        this.titulo = tarea.getTitulo();
        this.imagenUrl = tarea.getImagenUrl();
        this.estado = tarea.getEstado();
        this.prioridad = tarea.getPrioridad();
        this.categoria = tarea.getCategoria();
        this.descripcion = tarea.getDescripcion();
        this.fechaCreacion = tarea.getFechaCreacion();
        this.fechaLimite = tarea.getFechaLimite();
        this.porcentajeRealizado = tarea.getPorcentajeRealizado();
        this.tiempoEstimado = tarea.getTiempoEstimado();
    }
}
