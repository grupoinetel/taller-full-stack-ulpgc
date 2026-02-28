package adapter.out.persistence.adapters;

import adapter.out.persistence.entity.ComentarioJpaEntity;
import adapter.out.persistence.entity.TareaJpaEntity;
import adapter.out.persistence.entity.UsuarioJpaEntity;
import adapter.out.persistence.repository.TareaSpringRepository;
import application.port.out.TareaPersistencePort;
import domain.model.Comentario;
import domain.model.Tarea;
import domain.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class TareaAdapter implements TareaPersistencePort {

    private final TareaSpringRepository tareaSpringRepository;

    public TareaAdapter(TareaSpringRepository tareaSpringRepository) {
        this.tareaSpringRepository = tareaSpringRepository;
    }

    @Override
    public Tarea save(Tarea tarea) {
        return toDomain(tareaSpringRepository.save(toJpaEntity(tarea)));
    }

    @Override
    public Optional<Tarea> findById(Long id) {
        return tareaSpringRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Tarea> findAll() {
        return tareaSpringRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public void delete(Long id) {
        tareaSpringRepository.deleteById(id);
    }

    public Tarea toDomain(TareaJpaEntity tareaJpaEntity) {
        Tarea tarea = new Tarea();

        tarea.setId(tareaJpaEntity.getId());
        tarea.setNumero(tareaJpaEntity.getNumero());
        tarea.setTitulo(tareaJpaEntity.getTitulo());
        tarea.setImagenUrl(tareaJpaEntity.getImagenUrl());
        tarea.setDescripcion(tareaJpaEntity.getDescripcion());
        tarea.setFechaCreacion(tareaJpaEntity.getFechaCreacion());
        tarea.setFechaLimite(tareaJpaEntity.getFechaLimite());
        tarea.setEstado(tareaJpaEntity.getEstado());
        tarea.setPrioridad(tareaJpaEntity.getPrioridad());
        tarea.setCategoria(tareaJpaEntity.getCategoria());
        tarea.setPorcentajeRealizado(tareaJpaEntity.getPorcentajeRealizado());
        tarea.setTiempoEstimado(tareaJpaEntity.getTiempoEstimado());

        if (tareaJpaEntity.getAutor() != null) {
            tarea.setAutor(toDomainUsuario(tareaJpaEntity.getAutor()));
        }

        tarea.setAsignados(toDomainUsuarios(tareaJpaEntity.getAsignados()));
        tarea.setComentarios(toDomainComentarios(tareaJpaEntity.getComentarios()));

        return tarea;
    }

    public TareaJpaEntity toJpaEntity(Tarea tarea) {
        TareaJpaEntity tareaJpaEntity = new TareaJpaEntity(tarea.getId());

        tareaJpaEntity.setNumero(tarea.getNumero());
        tareaJpaEntity.setTitulo(tarea.getTitulo());
        tareaJpaEntity.setImagenUrl(tarea.getImagenUrl());
        tareaJpaEntity.setDescripcion(tarea.getDescripcion());
        tareaJpaEntity.setFechaCreacion(tarea.getFechaCreacion());
        tareaJpaEntity.setFechaLimite(tarea.getFechaLimite());
        tareaJpaEntity.setEstado(tarea.getEstado());
        tareaJpaEntity.setPrioridad(tarea.getPrioridad());
        tareaJpaEntity.setCategoria(tarea.getCategoria());
        tareaJpaEntity.setPorcentajeRealizado(tarea.getPorcentajeRealizado());
        tareaJpaEntity.setTiempoEstimado(tarea.getTiempoEstimado());

        if (tarea.getAutor() != null && tarea.getAutor().getId() != null) {
            tareaJpaEntity.setAutor(new UsuarioJpaEntity(tarea.getAutor().getId()));
        }

        tareaJpaEntity.setAsignados(toJpaUsuarios(tarea.getAsignados()));
        tareaJpaEntity.setComentarios(toJpaComentarios(tarea.getComentarios()));

        return tareaJpaEntity;
    }

    private Usuario toDomainUsuario(UsuarioJpaEntity usuarioJpaEntity) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioJpaEntity.getId());
        usuario.setNombre(usuarioJpaEntity.getNombre());
        usuario.setAvatarUrl(usuarioJpaEntity.getAvatarUrl());
        return usuario;
    }

    private List<Usuario> toDomainUsuarios(List<UsuarioJpaEntity> usuariosJpa) {
        if (usuariosJpa == null) {
            return Collections.emptyList();
        }

        List<Usuario> usuarios = new ArrayList<>();
        for (UsuarioJpaEntity usuarioJpa : usuariosJpa) {
            usuarios.add(toDomainUsuario(usuarioJpa));
        }
        return usuarios;
    }

    private List<Comentario> toDomainComentarios(List<ComentarioJpaEntity> comentariosJpa) {
        if (comentariosJpa == null) {
            return Collections.emptyList();
        }

        List<Comentario> comentarios = new ArrayList<>();
        for (ComentarioJpaEntity comentarioJpa : comentariosJpa) {
            Comentario comentario = new Comentario();
            comentario.setId(comentarioJpa.getId());
            comentario.setMensaje(comentarioJpa.getMensaje());
            comentario.setFecha(comentarioJpa.getFecha());

            if (comentarioJpa.getAutor() != null) {
                comentario.setAutor(toDomainUsuario(comentarioJpa.getAutor()));
            }

            comentarios.add(comentario);
        }
        return comentarios;
    }

    private List<UsuarioJpaEntity> toJpaUsuarios(List<Usuario> usuarios) {
        if (usuarios == null) {
            return Collections.emptyList();
        }

        List<UsuarioJpaEntity> usuariosJpa = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario != null && usuario.getId() != null) {
                usuariosJpa.add(new UsuarioJpaEntity(usuario.getId()));
            }
        }
        return usuariosJpa;
    }

    private List<ComentarioJpaEntity> toJpaComentarios(List<Comentario> comentarios) {
        if (comentarios == null) {
            return Collections.emptyList();
        }

        List<ComentarioJpaEntity> comentariosJpa = new ArrayList<>();
        for (Comentario comentario : comentarios) {
            if (comentario != null && comentario.getId() != null) {
                comentariosJpa.add(new ComentarioJpaEntity(comentario.getId()));
            }
        }
        return comentariosJpa;
    }
}
