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

        //Iteramos y rellenamos los usuarios
        List<Usuario> usuarios = obtainUsuariosDomain(tareaJpaEntity.getUsuarios());
        tarea.setUsuarios(usuarios);

        //Iteramos y rellenamos los comentarios
        List<Comentario> comentarios = obtainComentariosDomain(tareaJpaEntity.getComentarios());
        tarea.setComentarios(comentarios);

        tareaJpaEntity.setNumTarea(Integer.valueOf(tarea.getNumeroTarea()));
        tarea.setFechaCreacion(tareaJpaEntity.getFechaCreacion());
        tarea.setDescripcion(tareaJpaEntity.getDescripcion());
        tarea.setFechaLimite(tareaJpaEntity.getFechaFinalizacion());
        tarea.setPorcentajeRealizado(tareaJpaEntity.getPorcentaje());
        tarea.setPrioridad(tareaJpaEntity.getPrioridad());
        tarea.setPorcentajeRealizado(tareaJpaEntity.getPorcentaje());
        tarea.setEstado(tareaJpaEntity.getEstado());
        tarea.setId(tareaJpaEntity.getId());

        return tarea;
    }

    private List<Usuario> obtainUsuariosDomain(List<UsuarioJpaEntity> usuarios) {
        List<Usuario> listUsuarios = new ArrayList<>();

        for (UsuarioJpaEntity usuario : usuarios) {
            Usuario usuarioDomain = new Usuario();

            usuarioDomain.setUsername(usuario.getNombreUsuario());
            usuarioDomain.setId(usuario.getId());
            usuarioDomain.setNombre(usuario.getNombre());
            usuarioDomain.setApellido(usuario.getApellido());
            usuarioDomain.setAvatar(usuario.getAvatar());

            listUsuarios.add(usuarioDomain);
        }

        return listUsuarios;
    }

    private List<Comentario> obtainComentariosDomain(List<ComentarioJpaEntity> comentarios) {
        List<Comentario> listComentarios = new ArrayList<>();

        for (ComentarioJpaEntity comentario : comentarios) {
            Comentario comentarioDomain = new Comentario();

            comentarioDomain.setId(comentario.getId());
            comentarioDomain.setFechaCreacion(comentario.getFechaCreacion());
            comentarioDomain.setMensaje(comentario.getMensaje());
            comentarioDomain.setUsuarioCreador(new Usuario(comentario.getAutor().getId(),
                    comentario.getAutor().getNombreUsuario()));

            listComentarios.add(comentarioDomain);
        }

        return listComentarios;
    }

    public TareaJpaEntity toJpaEntity(Tarea tarea) {
        TareaJpaEntity tareaJpaEntity = new TareaJpaEntity(tarea.getId());

        //Iteramos y rellenamos los usuarios
        List<UsuarioJpaEntity> usuarios = obtainUsuariosJPA(tarea.getUsuarios());
        tareaJpaEntity.setUsuarios(usuarios);

        //Iteramos y rellenamos los comentarios
        List<ComentarioJpaEntity> comentarios = obtainComentariosJPA(tarea.getComentarios());
        tareaJpaEntity.setComentarios(comentarios);

        tareaJpaEntity.setNumTarea(Integer.valueOf(tarea.getNumeroTarea()));
        tareaJpaEntity.setFechaCreacion(tarea.getFechaCreacion());
        tareaJpaEntity.setDescripcion(tarea.getDescripcion());
        tareaJpaEntity.setFechaFinalizacion(tarea.getFechaLimite());
        tareaJpaEntity.setPorcentaje(tarea.getPorcentajeRealizado());
        tareaJpaEntity.setPrioridad(tarea.getPrioridad());
        tareaJpaEntity.setEstado(tarea.getEstado());
        tareaJpaEntity.setAutor(new UsuarioJpaEntity(tarea.getAutor().getId()));
        tareaJpaEntity.setId(tarea.getId());

        return tareaJpaEntity;
    }

    private List<ComentarioJpaEntity> obtainComentariosJPA(List<Comentario> comentarios) {
        List<ComentarioJpaEntity> listComentarios = new ArrayList<>();

        for (Comentario comentario : comentarios) {
            listComentarios.add(new ComentarioJpaEntity(comentario.getId()));
        }

        return listComentarios;
    }

    private List<UsuarioJpaEntity> obtainUsuariosJPA(List<Usuario> usuarios) {
        List<UsuarioJpaEntity> listUsuarios = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            listUsuarios.add(new UsuarioJpaEntity(usuario.getId()));
        }

        return listUsuarios;
    }
}
