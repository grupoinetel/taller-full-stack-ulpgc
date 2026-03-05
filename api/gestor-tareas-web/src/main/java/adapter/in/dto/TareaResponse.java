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
    private final EstadoTarea estado;
    private final PrioridadTarea prioridad;
    private final CategoriaTarea categoria;
    private final String descripcion;
    private final Date fechaCreacion;
    private final Date fechaLimite;
    private final Integer porcentajeRealizado;
    private final BigDecimal tiempoEstimado;

    @Setter
    private List<ComentarioResponse> comentarios;
    @Setter
    private List<UsuarioResponse> asignados;
    @Setter
    private UsuarioResponse autor;

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

        this.autor = new UsuarioResponse(tarea.getAutor());

        this.asignados = tarea.getAsignados().stream().map(UsuarioResponse::new).collect(Collectors.toList());
        tarea.getAsignados().forEach(usuario -> this.asignados.add(new UsuarioResponse(usuario)));

        this.comentarios = tarea.getComentarios().stream().map(ComentarioResponse::new).collect(Collectors.toList());
        tarea.getComentarios().forEach(comentario -> this.comentarios.add(new ComentarioResponse(comentario)));
    }
}
