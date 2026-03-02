package adapter.in.controller;

import adapter.in.dto.ComentarioResponse;
import adapter.in.dto.ParametrosCrearTarea;
import adapter.in.mapper.ComentarioMapper;
import application.port.in.comentario.ActualizarComentarioUseCase;
import application.port.in.comentario.CrearComentarioUseCase;
import application.port.in.comentario.EliminarComentarioUseCase;
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
    private final ComentarioMapper comentarioMapper;

    public ComentarioController(CrearComentarioUseCase crearComentarioUseCase, ActualizarComentarioUseCase actualizarComentarioUseCase, EliminarComentarioUseCase eliminarComentarioUseCase, ComentarioMapper comentarioMapper) {
        this.crearComentarioUseCase = crearComentarioUseCase;
        this.actualizarComentarioUseCase = actualizarComentarioUseCase;
        this.eliminarComentarioUseCase = eliminarComentarioUseCase;
        this.comentarioMapper = comentarioMapper;
    }

    @GetMapping("/comentarios/tarea/{id}")
    public ResponseEntity<List<ComentarioResponse>> getComentariosDeTarea() {

    }

    @PostMapping("/")
    public void create(@Valid @RequestBody ParametrosCrearTarea request) {
        crearComentarioUseCase.crearComentario(comentarioMapper.toCommand(request));
    }
}
