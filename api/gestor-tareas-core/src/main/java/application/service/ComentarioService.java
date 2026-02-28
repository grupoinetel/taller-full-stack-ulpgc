package application.service;

import application.port.in.comentario.ActualizarComentarioUseCase;
import application.port.in.comentario.CrearComentarioUseCase;
import application.port.in.comentario.EliminarComentarioUseCase;
import application.port.out.ComentarioPersistencePort;
import domain.model.Comentario;
import exception.ComentarioNotFoundException;

public class ComentarioService implements
        ActualizarComentarioUseCase,
        CrearComentarioUseCase,
        EliminarComentarioUseCase {

    private final ComentarioPersistencePort comentarioPersistencePort;

    public ComentarioService(ComentarioPersistencePort comentarioPersistencePort) {
        this.comentarioPersistencePort = comentarioPersistencePort;
    }

    public Comentario actualizarComentario(Comentario nuevoComentario) {
        Comentario comentario = obtenerComentario(nuevoComentario.getId());

        comentario.setFecha(nuevoComentario.getFecha());
        comentario.setMensaje(nuevoComentario.getMensaje());
        comentario.setId(nuevoComentario.getId());
        comentario.setTarea(nuevoComentario.getTarea());
        comentario.setAutor(nuevoComentario.getAutor());

        return comentarioPersistencePort.save(comentario);
    }

    public Comentario crearComentario(Comentario comentario) {
        return comentarioPersistencePort.save(comentario);
    }

    public void eliminarComentario(Long id) {
        comentarioPersistencePort.delete(id);
    }

    private Comentario obtenerComentario(Long id) {
        return comentarioPersistencePort.findById(id).orElseThrow(ComentarioNotFoundException::new);
    }
}
