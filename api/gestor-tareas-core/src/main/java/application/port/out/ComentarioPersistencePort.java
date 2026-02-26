package application.port.out;

import domain.model.Comentario;

import java.util.List;
import java.util.Optional;

public interface ComentarioPersistencePort {

    Comentario save(Comentario comentario);

    Optional<Comentario> findById(Long id);

    List<Comentario> findAll();

    void delete(Long id);
}
