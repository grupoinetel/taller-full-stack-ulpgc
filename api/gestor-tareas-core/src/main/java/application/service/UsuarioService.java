package application.service;

import application.port.in.usurio.ObtenerUsuarioUseCase;
import application.port.out.UsuarioPersistencePort;
import domain.model.Usuario;

import java.util.List;

public class UsuarioService implements ObtenerUsuarioUseCase {

    private final UsuarioPersistencePort usuarioPersistencePort;

    public UsuarioService(UsuarioPersistencePort usuarioPersistencePort) {
        this.usuarioPersistencePort = usuarioPersistencePort;
    }

    @Override
    public Usuario obtenerUsuario(Long id) {
        return null;
    }

    @Override
    public List<Usuario> consultarUsuarios(List<Object> filtros) {
        return List.of();
    }
}
