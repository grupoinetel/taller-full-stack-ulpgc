package application.port.out;

import domain.model.Comentario;

import java.util.List;
import java.util.Optional;

public interface ComentarioPersistencePort {

    Comentario save(Comentario comentario);

    Optional<Comentario> findById(Long id);

    List<Comentario> findAll();

    void delete(Long id);

    /**
     * CREAMOS EL NUEVO METODO EN EL PERSISTENCE PORT HACIENDO USO DE LAS VENTAJAS QUE OFRECE SPRING* (SpringRepository)
     */
}
