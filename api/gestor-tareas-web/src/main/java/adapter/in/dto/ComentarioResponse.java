package adapter.in.dto;

import domain.model.Comentario;
import domain.model.Tarea;
import lombok.Getter;

import java.util.Date;

@Getter
public class ComentarioResponse {

    private Long id;
    private String contenido;
    private Date fecha;
    private UsuarioResponse autor;

    public ComentarioResponse(Comentario comentario) {
        this.id = comentario.getId();
        this.contenido = comentario.getMensaje();
        this.fecha = comentario.getFecha();
        this.autor = new UsuarioResponse(comentario.getAutor());
    }
}
