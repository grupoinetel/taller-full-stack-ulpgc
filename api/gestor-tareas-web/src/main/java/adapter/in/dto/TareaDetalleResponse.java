package adapter.in.dto;

import domain.enums.CategoriaTarea;
import domain.model.Tarea;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TareaDetalleResponse extends TareaResponse {

    private final CategoriaTarea categoria;
    private final String descripcion;
    private final Date fechaCreacion;
    private final Integer porcentajeRealizado;
    private final BigDecimal tiempoEstimado;

    @Setter
    private List<ComentarioResponse> comentarios;
    @Setter
    private List<UsuarioResponse> asignados;
    @Setter
    private UsuarioResponse autor;

    public TareaDetalleResponse(Tarea tarea) {
        super(tarea);
        this.categoria = tarea.getCategoria();
        this.descripcion = tarea.getDescripcion();
        this.fechaCreacion = tarea.getFechaCreacion();
        this.porcentajeRealizado = tarea.getPorcentajeRealizado();
        this.tiempoEstimado = tarea.getTiempoEstimado();

        this.autor = new UsuarioResponse(tarea.getAutor());

        this.asignados = tarea.getAsignados().stream().map(UsuarioResponse::new).collect(Collectors.toList());

        this.comentarios = tarea.getComentarios().stream().map(ComentarioResponse::new).collect(Collectors.toList());
    }
}
