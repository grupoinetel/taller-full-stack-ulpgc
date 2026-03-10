package application.port.in.comentario;

import domain.model.Comentario;

import java.util.List;

public interface ObtenerComentarioUseCase {

    /**
     * CREAMOS EL NUEVO METODO DEL CASO DE USO
     */

    List<Comentario> consultarComentarios();
}
