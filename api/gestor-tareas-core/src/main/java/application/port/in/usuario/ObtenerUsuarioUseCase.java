package application.port.in.usuario;

import domain.model.Usuario;

import java.util.List;

public interface ObtenerUsuarioUseCase {

    Usuario obtenerUsuario(Long id);

    List<Usuario> consultarUsuarios(List<Object> filtros);
}
