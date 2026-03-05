package application.port.commands;

import domain.enums.CategoriaTarea;
import domain.enums.EstadoTarea;
import domain.enums.PrioridadTarea;
import domain.model.Usuario;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
public class CrearActualizarTareaCommand {
    private final Long id;
    private final Integer numero;
    private final String titulo;
    private final String imagenUrl;
    private final Date fechaLimite;
    private final EstadoTarea estado;
    private final PrioridadTarea prioridad;
    private final CategoriaTarea categoria;
    private final String descripcion;
    private final Date fechaCreacion;
    private final Integer porcentajeRealizado;
    private final BigDecimal tiempoEstimado;
    private final List<Long> asignados;
    private final Long autor;

    public CrearActualizarTareaCommand(
            Long id,
            Integer numero,
            String titulo,
            String imagenUrl,
            Date fechaLimite,
            EstadoTarea estado,
            PrioridadTarea prioridad,
            CategoriaTarea categoria,
            String descripcion,
            Date fechaCreacion,
            Integer porcentajeRealizado,
            BigDecimal tiempoEstimado,
            List<Long> asignados,
            Long autor
    ) {
        this.id = id;
        this.numero = numero;
        this.titulo = titulo;
        this.imagenUrl = imagenUrl;
        this.fechaLimite = fechaLimite;
        this.estado = estado;
        this.prioridad = prioridad;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.porcentajeRealizado = porcentajeRealizado;
        this.tiempoEstimado = tiempoEstimado;
        this.asignados = asignados;
        this.autor = autor;
    }
}
