package adapter.in.controller;

import adapter.in.dto.UsuarioResponse;
import adapter.in.mapper.UsuarioMapper;
import application.port.in.usurio.ObtenerUsuarioUseCase;
import domain.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final ObtenerUsuarioUseCase obtenerUsuarioUseCase;
    private final UsuarioMapper usuarioMapper;

    public UsuarioController(ObtenerUsuarioUseCase obtenerUsuarioUseCase, UsuarioMapper usuarioMapper) {
        this.obtenerUsuarioUseCase = obtenerUsuarioUseCase;
        this.usuarioMapper = usuarioMapper;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> getAll() {
        List<Usuario> usuarios = obtenerUsuarioUseCase.consultarUsuarios(new ArrayList<>());

        return ResponseEntity.ok(usuarioMapper.toResponseList(usuarios));
    }
}
