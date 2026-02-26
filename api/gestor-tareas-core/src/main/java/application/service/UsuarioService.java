package application.service;

import application.port.in.usurio.ObtenerUsuarioUseCase;
import application.port.out.UsuarioInterfacePort;
import domain.model.Usuario;

import java.util.List;

public class UsuarioService implements ObtenerUsuarioUseCase {

    private final UsuarioInterfacePort usuarioInterfacePort;

    public UsuarioService(UsuarioInterfacePort usuarioInterfacePort) {
        this.usuarioInterfacePort = usuarioInterfacePort;
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
