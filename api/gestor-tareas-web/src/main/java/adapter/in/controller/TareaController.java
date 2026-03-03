package adapter.in.controller;

import adapter.in.dto.ParametrosCrearTarea;
import adapter.in.dto.TareaResponse;
import adapter.in.mapper.TareaWebMapper;
import application.port.in.tarea.ActualizarTareaUseCase;
import application.port.in.tarea.CrearTareaUseCase;
import application.port.in.tarea.EliminarTareaUseCase;
import application.port.in.tarea.ObtenerTareaUseCase;
import domain.model.Tarea;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tareas")
public class TareaController {

    private final ActualizarTareaUseCase actualizarTareaUseCase;
    private final CrearTareaUseCase crearTareaUseCase;
    private final EliminarTareaUseCase eliminarTareaUseCase;
    private final ObtenerTareaUseCase obtenerTareaUseCase;
    private final TareaWebMapper tareaWebMapper;

    public TareaController(ActualizarTareaUseCase actualizarTareaUseCase,
                           CrearTareaUseCase crearTareaUseCase,
                           EliminarTareaUseCase eliminarTareaUseCase,
                           ObtenerTareaUseCase obtenerTareaUseCase,
                           TareaWebMapper tareaWebMapper) {
        this.actualizarTareaUseCase = actualizarTareaUseCase;
        this.crearTareaUseCase = crearTareaUseCase;
        this.eliminarTareaUseCase = eliminarTareaUseCase;
        this.obtenerTareaUseCase = obtenerTareaUseCase;
        this.tareaWebMapper = tareaWebMapper;
    }

    @PostMapping("/")
    public void create(@Valid @RequestBody ParametrosCrearTarea request) {
        crearTareaUseCase.crearTarea(tareaWebMapper.toCommand(request));
    }

    @GetMapping
    public ResponseEntity<List<TareaResponse>> getAll() {
        List<Tarea> tareas = obtenerTareaUseCase.consultarTareas(new ArrayList<>());

        return ResponseEntity.ok(tareaWebMapper.toResponseList(tareas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TareaResponse> get(@PathVariable("id") Long id) {
        Tarea tarea = obtenerTareaUseCase.obtenerTarea(id);

        return ResponseEntity.ok(this.tareaWebMapper.toResponse(tarea));
    }

    @PutMapping("/{id}")
    ResponseEntity<TareaResponse> put(@PathVariable("id") Long id,
                                      @Valid @RequestBody ParametrosCrearTarea parametrosActualizarTarea) {
        parametrosActualizarTarea.setId(id);
        Tarea tareaActualizada = actualizarTareaUseCase.actualizarTarea(tareaWebMapper.toCommand(parametrosActualizarTarea));

        return ResponseEntity.ok(this.tareaWebMapper.toResponse(tareaActualizada));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        eliminarTareaUseCase.eliminarTarea(id); //TODO PA DECIR UN OKAY O ALGO
    }
}
