package application.port.out;

import domain.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioInterfacePort {

    Optional<Usuario> findById(Long id);

    List<Usuario> findAll();
}
