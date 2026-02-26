package adapter.in.dto;

import domain.enums.EstadoTarea;
import domain.enums.Prioridad;
import domain.model.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class ParametrosCrearTarea {
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
}
