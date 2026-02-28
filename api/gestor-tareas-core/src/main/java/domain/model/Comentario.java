package domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Comentario {

    private Long id;
    private String mensaje;
    private Date fecha;
    private Tarea tarea;
    private Usuario autor;
}
