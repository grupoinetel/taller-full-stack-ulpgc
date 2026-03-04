package application.port.out;

import domain.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioPersistencePort {

    Optional<Usuario> findById(Long id);

    List<Usuario> findAll();
}
