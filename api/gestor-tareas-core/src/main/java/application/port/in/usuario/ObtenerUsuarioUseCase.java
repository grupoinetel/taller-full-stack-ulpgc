package application.port.in.usuario;

import domain.model.Usuario;

import java.util.List;

public interface ObtenerUsuarioUseCase {

    List<Usuario> consultarUsuarios();
}
