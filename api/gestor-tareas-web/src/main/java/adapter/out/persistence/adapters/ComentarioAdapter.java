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
import java.util.stream.Collectors;

@Component
public class ComentarioAdapter implements ComentarioPersistencePort {

    private final ComentarioSpringRepository comentarioSpringRepository;

    public ComentarioAdapter(ComentarioSpringRepository comentarioSpringRepository) {
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
        return comentarioSpringRepository.findAll().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        comentarioSpringRepository.deleteById(id);
    }

    @Override
    public List<Comentario> findByTareaId(Long id) {
        return comentarioSpringRepository.findByTareaId(id).stream().map(this::toDomain).collect(Collectors.toList());
    }

    private ComentarioJpaEntity toJpaEntity(Comentario comentario) {
        ComentarioJpaEntity comentarioJpaEntity = new ComentarioJpaEntity(comentario.getId());

        comentarioJpaEntity.setMensaje(comentario.getMensaje());
        comentarioJpaEntity.setFecha(comentario.getFecha());

        if (comentario.getAutor() != null && comentario.getAutor().getId() != null) {
            comentarioJpaEntity.setAutor(new UsuarioJpaEntity(comentario.getAutor().getId()));
        }

        if (comentario.getTarea() != null && comentario.getTarea().getId() != null) {
            comentarioJpaEntity.setTarea(new TareaJpaEntity(comentario.getTarea().getId()));
        }

        return comentarioJpaEntity;
    }

    private Comentario toDomain(ComentarioJpaEntity comentarioJpaEntity) {
        Comentario comentario = new Comentario();

        comentario.setId(comentarioJpaEntity.getId());
        comentario.setMensaje(comentarioJpaEntity.getMensaje());
        comentario.setFecha(comentarioJpaEntity.getFecha());

        if (comentarioJpaEntity.getAutor() != null) {
            Usuario usuario = new Usuario(comentarioJpaEntity.getAutor().getId());
            usuario.setNombre(comentarioJpaEntity.getAutor().getNombre());
            usuario.setAvatarUrl(comentarioJpaEntity.getAutor().getAvatarUrl());
            comentario.setAutor(usuario);
        }

        if (comentarioJpaEntity.getTarea() != null) {
            Tarea tarea = new Tarea(comentarioJpaEntity.getTarea().getId());
            comentario.setTarea(tarea);
        }

        return comentario;
    }
}
