package adapter.in.controller;

import adapter.in.dto.ComentarioResponse;
import adapter.in.dto.ParametrosCrearComentario;
import adapter.in.mapper.ComentarioMapper;
import application.port.in.comentario.ActualizarComentarioUseCase;
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
    private final ActualizarComentarioUseCase actualizarComentarioUseCase;
    private final EliminarComentarioUseCase eliminarComentarioUseCase;
    private final ObtenerComentarioUseCase obtenerComentarioUseCase;
    private final ComentarioMapper comentarioMapper;

    public ComentarioController(CrearComentarioUseCase crearComentarioUseCase, ActualizarComentarioUseCase actualizarComentarioUseCase, EliminarComentarioUseCase eliminarComentarioUseCase, ObtenerComentarioUseCase obtenerComentarioUseCase, ComentarioMapper comentarioMapper) {
        this.crearComentarioUseCase = crearComentarioUseCase;
        this.actualizarComentarioUseCase = actualizarComentarioUseCase;
        this.eliminarComentarioUseCase = eliminarComentarioUseCase;
        this.obtenerComentarioUseCase = obtenerComentarioUseCase;
        this.comentarioMapper = comentarioMapper;
    }

    @GetMapping("/comentarios/tarea/{id}")
    public ResponseEntity<List<ComentarioResponse>> getComentariosDeTarea(@PathVariable Long id) {
        List<Comentario> comentarios = obtenerComentarioUseCase.obtenerComentariosDeTarea(id);

        return ResponseEntity.ok(comentarioMapper.toComentarioResponseList(comentarios));
    }

    @PostMapping("/{id}")
    public void create(@PathVariable Long id, @Valid @RequestBody ParametrosCrearComentario request) {
        crearComentarioUseCase.crearComentario(comentarioMapper.toCommand(id, request));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        eliminarComentarioUseCase.eliminarComentario(id);
    }
}
