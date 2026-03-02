package application.port.in.comentario;

import domain.model.Comentario;

import java.util.List;

public interface ObtenerComentarioUseCase {

    List<Comentario> obtenerComentariosDeTarea(Long idTarea);
}
