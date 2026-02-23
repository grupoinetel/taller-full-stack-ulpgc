package domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Tarea {
    Long id;
    String numeroTarea;
    Date fechaLimite;
    Enum prioridad;
    Enum estado;
    String descripcion;
    Date fechaCreacion;
    Integer porcentajeRealizado;
    List<Usuario> usuarios;
    Usuario autor;
}
