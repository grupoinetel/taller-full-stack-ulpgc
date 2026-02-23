package application.service;

import application.port.in.comentario.ActualizarComentarioUseCase;
import application.port.in.comentario.CrearComentarioUseCase;
import application.port.in.comentario.EliminarComentarioUseCase;
import application.port.out.ComentarioPersistencePort;

public class ComentarioService implements
        ActualizarComentarioUseCase,
        CrearComentarioUseCase,
        EliminarComentarioUseCase {

    private final ComentarioPersistencePort comentarioPersistencePort;

    public ComentarioService(ComentarioPersistencePort comentarioPersistencePort) {
        this.comentarioPersistencePort = comentarioPersistencePort;
    }
}
