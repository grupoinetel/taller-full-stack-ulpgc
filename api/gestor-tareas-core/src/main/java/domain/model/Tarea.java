package domain.model;

import domain.enums.CategoriaTarea;
import domain.enums.EstadoTarea;
import domain.enums.PrioridadTarea;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Tarea {
    private Long id;
    private Integer numero;
    private String titulo;
    private String imagenUrl;
    private Date fechaLimite;
    private EstadoTarea estado;
    private PrioridadTarea prioridad;
    private CategoriaTarea categoria;
    private String descripcion;
    private Date fechaCreacion;
    private Integer porcentajeRealizado;
    private BigDecimal tiempoEstimado;
    private Usuario autor;
    private List<Usuario> asignados;
    private List<Comentario> comentarios;
}
