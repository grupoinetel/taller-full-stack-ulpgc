package adapter.out.persistence;

import adapter.out.persistence.entity.TareaJpaEntity;
import adapter.out.persistence.repository.TareaSpringRepository;
import application.port.out.TareaPersistencePort;
import domain.model.Tarea;
import org.springframework.stereotype.Component;

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
    }

    @Override
    public Optional<Tarea> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Tarea> findAll() {
        return List.of();
    }

    @Override
    public void delete(Long id) {
    }

    private TareaJpaEntity toJpaEntity(Tarea tarea) {
        TareaJpaEntity tareaJpaEntity = new TareaJpaEntity();

        //TODO REVISAR QUE TODO CORRESPONDA
        tareaJpaEntity.setNumTarea(Integer.valueOf(tarea.getNumeroTarea()));
        tareaJpaEntity.setCreation_date(tarea.getFechaCreacion());
        tareaJpaEntity.setDescription(tarea.getDescripcion());
        tareaJpaEntity.setFinal_date(tarea.getFechaLimite());
        tareaJpaEntity.setPercentage(tarea.getPorcentajeRealizado());
        tareaJpaEntity.setPriority(tarea.getPrioridad());
        tareaJpaEntity.setPercentage(tarea.getPorcentajeRealizado());

        return tareaJpaEntity;
    }

    private Tarea toDomain(TareaJpaEntity tareaJpaEntity) {
        Tarea tarea = new Tarea();

        //TODO REVISAR QUE TODO CORRESPONDA
        tareaJpaEntity.setNumTarea(Integer.valueOf(tarea.getNumeroTarea()));
        tarea.setFechaCreacion(tareaJpaEntity.getCreation_date());
        tarea.setDescripcion(tareaJpaEntity.getDescription());
        tarea.setFechaLimite(tareaJpaEntity.getFinal_date());
        tarea.setPorcentajeRealizado(tareaJpaEntity.getPercentage());
        tarea.setPrioridad(tareaJpaEntity.getPriority());
        tarea.setPorcentajeRealizado(tareaJpaEntity.getPercentage());

        return tarea;
    }
}
