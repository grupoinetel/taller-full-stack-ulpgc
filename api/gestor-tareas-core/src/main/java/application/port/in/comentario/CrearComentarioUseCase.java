package application.port.in.comentario;

import application.port.commands.CrearComentarioCommand;
import domain.model.Comentario;

public interface CrearComentarioUseCase {

    Comentario crearComentario(CrearComentarioCommand comentario);
}
