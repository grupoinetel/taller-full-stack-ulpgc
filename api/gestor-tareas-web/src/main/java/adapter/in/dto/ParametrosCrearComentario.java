package adapter.in.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ParametrosCrearComentario {
    private Long id;
    private String contenido;
    private Long idTarea;
    private Long idAutor;
    private Date fecha;
}
