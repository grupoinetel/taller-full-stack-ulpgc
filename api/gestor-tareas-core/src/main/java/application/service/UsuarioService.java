package application.service;

import application.port.in.usuario.ObtenerUsuarioUseCase;
import application.port.out.UsuarioPersistencePort;
import domain.model.Usuario;

import java.util.List;

public class UsuarioService implements ObtenerUsuarioUseCase {

    private final UsuarioPersistencePort usuarioPersistencePort;

    public UsuarioService(UsuarioPersistencePort usuarioPersistencePort) {
        this.usuarioPersistencePort = usuarioPersistencePort;
    }

    @Override
    public List<Usuario> consultarUsuarios() {
        return usuarioPersistencePort.findAll();
    }
}
