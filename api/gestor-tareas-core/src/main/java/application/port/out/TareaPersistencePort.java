package application.port.out;

import domain.model.Tarea;

import java.util.List;
import java.util.Optional;

public interface TareaPersistencePort {

    Tarea save(Tarea tarea);

    Optional<Tarea> findById(Long id);

    List<Tarea> findAll();

    void delete(Long id);
}
