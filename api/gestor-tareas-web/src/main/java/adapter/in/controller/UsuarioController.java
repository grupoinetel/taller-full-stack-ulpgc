package adapter.in.controller;

import adapter.in.dto.UsuarioResponse;
import adapter.in.mapper.UsuarioWebMapper;
import application.port.in.usuario.ObtenerUsuarioUseCase;
import domain.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final ObtenerUsuarioUseCase obtenerUsuarioUseCase;
    private final UsuarioWebMapper usuarioWebMapper;

    public UsuarioController(ObtenerUsuarioUseCase obtenerUsuarioUseCase, UsuarioWebMapper usuarioWebMapper) {
        this.obtenerUsuarioUseCase = obtenerUsuarioUseCase;
        this.usuarioWebMapper = usuarioWebMapper;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> getAll() {
        List<Usuario> usuarios = obtenerUsuarioUseCase.consultarUsuarios();

        return ResponseEntity.ok(usuarioWebMapper.toResponseList(usuarios));
    }
}
