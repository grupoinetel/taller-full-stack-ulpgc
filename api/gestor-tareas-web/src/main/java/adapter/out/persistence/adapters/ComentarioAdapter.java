package adapter.out.persistence.adapters;

import adapter.out.persistence.entity.ComentarioJpaEntity;
import adapter.out.persistence.entity.TareaJpaEntity;
import adapter.out.persistence.entity.UsuarioJpaEntity;
import adapter.out.persistence.repository.ComentarioSpringRepository;
import application.port.out.ComentarioPersistencePort;
import domain.model.Comentario;
import domain.model.Tarea;
import domain.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ComentarioAdapter implements ComentarioPersistencePort {

    private final ComentarioSpringRepository comentarioSpringRepository;

    public ComentarioAdapter(ComentarioSpringRepository comentarioSpringRepository, TareaAdapter tareaAdapter) {
        this.comentarioSpringRepository = comentarioSpringRepository;
    }

    @Override
    public Comentario save(Comentario comentario) {
        return toDomain(comentarioSpringRepository.save(toJpaEntity(comentario)));
    }

    @Override
    public Optional<Comentario> findById(Long id) {
        return comentarioSpringRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Comentario> findAll() {
        return comentarioSpringRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public void delete(Long id) {
        comentarioSpringRepository.deleteById(id);
    }

    private ComentarioJpaEntity toJpaEntity(Comentario comentario) {
        ComentarioJpaEntity comentarioJpaEntity = new ComentarioJpaEntity();

        comentarioJpaEntity.setFechaCreacion(comentario.getFechaCreacion());
        comentarioJpaEntity.setMensaje(comentario.getMensaje());
        comentarioJpaEntity.setAutor(new UsuarioJpaEntity(comentario.getUsuarioCreador().getId()));
        comentarioJpaEntity.setId(comentario.getId());
        comentarioJpaEntity.setTarea(new TareaJpaEntity(comentario.getTarea().getId()));

        return comentarioJpaEntity;
    }

    private Comentario toDomain(ComentarioJpaEntity comentarioJpaEntity) {
        Comentario comentario = new Comentario();

        Usuario usuario = obtainUsuario(comentarioJpaEntity.getAutor());
        Tarea tarea = obtainTarea(comentario.getTarea());

        comentario.setId(comentarioJpaEntity.getId());
        comentario.setFechaCreacion(comentarioJpaEntity.getFechaCreacion());
        comentario.setMensaje(comentarioJpaEntity.getMensaje());
        comentario.setUsuarioCreador(usuario);
        comentario.setTarea(tarea);

        return comentario;
    }

    private Tarea obtainTarea(Tarea tareaComentario) {
        Tarea tarea = new Tarea();

        tarea.setId(tareaComentario.getId());
        tarea.setDescripcion(tareaComentario.getDescripcion());
        tarea.setFechaCreacion(tareaComentario.getFechaCreacion());
        tarea.setEstado(tareaComentario.getEstado());
        tarea.setPrioridad(tareaComentario.getPrioridad());
        tarea.setNumeroTarea(tareaComentario.getNumeroTarea());
        tarea.setFechaLimite(tareaComentario.getFechaLimite());
        tarea.setPorcentajeRealizado(tareaComentario.getPorcentajeRealizado());
        tarea.setAutor(tareaComentario.getAutor());
        tarea.setUsuarios(tareaComentario.getUsuarios());

        return tarea;
    }

    private Usuario obtainUsuario(UsuarioJpaEntity autor) {
        Usuario usuario = new Usuario();

        usuario.setId(autor.getId());
        usuario.setNombre(autor.getNombre());
        usuario.setAvatar(autor.getAvatar());
        usuario.setAvatar(autor.getAvatar());
        usuario.setApellido(autor.getApellido());
        usuario.setUsername(autor.getNombreUsuario());

        return usuario;
    }
}
