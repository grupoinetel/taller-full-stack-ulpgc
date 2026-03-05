package adapter.in.controller;

import adapter.in.dto.ComentarioResponse;
import adapter.in.dto.ParametrosCrearComentario;
import adapter.in.mapper.ComentarioWebMapper;
import application.port.in.comentario.CrearComentarioUseCase;
import application.port.in.comentario.EliminarComentarioUseCase;
import application.port.in.comentario.ObtenerComentarioUseCase;
import domain.model.Comentario;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    private final CrearComentarioUseCase crearComentarioUseCase;
    private final EliminarComentarioUseCase eliminarComentarioUseCase;
    private final ObtenerComentarioUseCase obtenerComentarioUseCase;
    private final ComentarioWebMapper comentarioWebMapper;

    public ComentarioController(CrearComentarioUseCase crearComentarioUseCase, EliminarComentarioUseCase eliminarComentarioUseCase, ObtenerComentarioUseCase obtenerComentarioUseCase, ComentarioWebMapper comentarioWebMapper) {
        this.crearComentarioUseCase = crearComentarioUseCase;
        this.eliminarComentarioUseCase = eliminarComentarioUseCase;
        this.obtenerComentarioUseCase = obtenerComentarioUseCase;
        this.comentarioWebMapper = comentarioWebMapper;
    }

    @GetMapping("/tarea/{id}")
    public ResponseEntity<List<ComentarioResponse>> getComentariosDeTarea(@PathVariable("id") Long id) {
        List<Comentario> comentarios = obtenerComentarioUseCase.obtenerComentariosDeTarea(id);

        return ResponseEntity.ok(comentarioWebMapper.toComentarioResponseList(comentarios));
    }

    @GetMapping
    public ResponseEntity<List<ComentarioResponse>> getComentarios() {
        List<Comentario> comentarios = obtenerComentarioUseCase.consultarComentarios();

        return ResponseEntity.ok(comentarioWebMapper.toComentarioResponseList(comentarios));
    }

    @PostMapping("/{id}")
    public void create(@PathVariable("id") Long id, @Valid @RequestBody ParametrosCrearComentario request) {
        crearComentarioUseCase.crearComentario(comentarioWebMapper.toCommand(id, request));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        eliminarComentarioUseCase.eliminarComentario(id);
    }
}
