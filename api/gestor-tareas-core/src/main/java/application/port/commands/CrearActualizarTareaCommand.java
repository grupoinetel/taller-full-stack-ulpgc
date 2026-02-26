package application.port.commands;

import domain.enums.EstadoTarea;
import domain.enums.Prioridad;
import domain.model.Usuario;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class CrearActualizarTareaCommand {
    String numeroTarea;
    Date fechaLimite;
    Prioridad prioridad;
    EstadoTarea estado;
    String descripcion;
    Date fechaCreacion;
    Integer porcentajeRealizado;
    List<Usuario> usuarios;
    Usuario autor;

    Long id;

    public CrearActualizarTareaCommand(String numeroTarea, Date fechaLimite, Prioridad prioridad, EstadoTarea estado, String descripcion, Date fechaCreacion, Integer porcentajeRealizado, List<Usuario> usuarios, Usuario autor) {
        this.numeroTarea = numeroTarea;
        this.fechaLimite = fechaLimite;
        this.prioridad = prioridad;
        this.estado = estado;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.porcentajeRealizado = porcentajeRealizado;
        this.usuarios = usuarios;
        this.autor = autor;
    }

    public CrearActualizarTareaCommand(Long id, String numeroTarea, Date fechaLimite, Prioridad prioridad, EstadoTarea estado, String descripcion, Date fechaCreacion, Integer porcentajeRealizado, List<Usuario> usuarios, Usuario autor) {
        this.numeroTarea = numeroTarea;
        this.fechaLimite = fechaLimite;
        this.prioridad = prioridad;
        this.estado = estado;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.porcentajeRealizado = porcentajeRealizado;
        this.usuarios = usuarios;
        this.autor = autor;
        this.id = id;
    }
}
