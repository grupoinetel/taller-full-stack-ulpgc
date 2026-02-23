package application.service;

import application.port.in.usurio.ObtenerUsuarioUseCase;
import application.port.out.UsuarioInterfacePort;

public class UsuarioService implements ObtenerUsuarioUseCase {

    private final UsuarioInterfacePort usuarioInterfacePort;

    public UsuarioService(UsuarioInterfacePort usuarioInterfacePort) {
        this.usuarioInterfacePort = usuarioInterfacePort;
    }
}
