package domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Comentario {

    Long id;
    String mensaje;
    Tarea tarea;
    Usuario usuarioCreador;
    Date fechaCreacion;
}
