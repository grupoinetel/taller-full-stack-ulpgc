package application.service;

import application.port.commands.CrearComentarioCommand;
import application.port.in.comentario.ActualizarComentarioUseCase;
import application.port.in.comentario.CrearComentarioUseCase;
import application.port.in.comentario.EliminarComentarioUseCase;
import application.port.in.comentario.ObtenerComentarioUseCase;
import application.port.out.ComentarioPersistencePort;
import domain.model.Comentario;
import domain.model.Tarea;
import domain.model.Usuario;
import exception.ComentarioNotFoundException;

import java.util.Date;
import java.util.List;

public class ComentarioService implements
        ActualizarComentarioUseCase,
        CrearComentarioUseCase,
        ObtenerComentarioUseCase,
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

    public Comentario crearComentario(CrearComentarioCommand comentario) {
        Comentario comentarioDomain = new Comentario();

        comentarioDomain.setTarea(new Tarea(comentario.getIdTarea()));
        comentarioDomain.setMensaje(comentario.getContenido());
        comentarioDomain.setAutor(new Usuario(comentario.getIdAutor()));
        comentarioDomain.setFecha(new Date());
        comentarioDomain.setId(null);

        return comentarioPersistencePort.save(comentarioDomain);
    }

    public void eliminarComentario(Long id) {
        comentarioPersistencePort.delete(id);
    }

    private Comentario obtenerComentario(Long id) {
        return comentarioPersistencePort.findById(id).orElseThrow(ComentarioNotFoundException::new);
    }

    @Override
    public List<Comentario> obtenerComentariosDeTarea(Long idTarea) {
        return comentarioPersistencePort.findByTareaId(idTarea);
    }

    @Override
    public List<Comentario> consultarComentarios() {
        return comentarioPersistencePort.findAll();
    }
}
