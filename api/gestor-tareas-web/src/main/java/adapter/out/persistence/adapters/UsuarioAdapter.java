package adapter.out.persistence.adapters;

import adapter.out.persistence.entity.TareaJpaEntity;
import adapter.out.persistence.entity.UsuarioJpaEntity;
import adapter.out.persistence.repository.UsuarioSpringRepository;
import application.port.out.UsuarioPersistencePort;
import domain.model.Tarea;
import domain.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UsuarioAdapter implements UsuarioPersistencePort {

    private final UsuarioSpringRepository usuarioSpringRepository;

    public UsuarioAdapter(UsuarioSpringRepository usuarioSpringRepository) {
        this.usuarioSpringRepository = usuarioSpringRepository;
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return this.usuarioSpringRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Usuario> findAll() {
        return this.usuarioSpringRepository.findAll().stream().map(this::toDomain).collect(Collectors.toList());
    }

    private Usuario toDomain(UsuarioJpaEntity usuarioJpaEntity) {
        Usuario usuario = new Usuario(usuarioJpaEntity.getId());

        usuario.setTareas(toDomainTareas(usuarioJpaEntity.getTareas()));
        usuario.setNombre(usuarioJpaEntity.getNombre());
        usuario.setAvatarUrl(usuarioJpaEntity.getAvatarUrl());

        return usuario;
    }

    private List<Tarea> toDomainTareas(List<TareaJpaEntity> tareas) {
        if (tareas == null) {
            return Collections.emptyList();
        }

        List<Tarea> tareasDomain = new ArrayList<>();

        for (TareaJpaEntity tarea : tareas) {
            tareasDomain.add(toDomainTarea(tarea));
        }

        return tareasDomain;
    }

    private Tarea toDomainTarea(TareaJpaEntity tarea) {
        Tarea tareaDomain = new Tarea(tarea.getId());

        tareaDomain.setNumero(tarea.getNumero());
        tareaDomain.setTitulo(tarea.getTitulo());
        tareaDomain.setImagenUrl(tarea.getImagenUrl());
        tareaDomain.setDescripcion(tarea.getDescripcion());
        tareaDomain.setFechaCreacion(tarea.getFechaCreacion());
        tareaDomain.setFechaLimite(tarea.getFechaLimite());
        tareaDomain.setEstado(tarea.getEstado());
        tareaDomain.setPrioridad(tarea.getPrioridad());
        tareaDomain.setCategoria(tarea.getCategoria());
        tareaDomain.setPorcentajeRealizado(tarea.getPorcentajeRealizado());
        tareaDomain.setTiempoEstimado(tarea.getTiempoEstimado());

        return tareaDomain;
    }
}
