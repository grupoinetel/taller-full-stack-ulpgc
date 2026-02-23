package adapter.in.controller;

import adapter.in.dto.TareaResponse;
import adapter.in.mapper.TareaWebMapper;
import application.port.in.tarea.ActualizarTareaUseCase;
import application.port.in.tarea.CrearTareaUseCase;
import application.port.in.tarea.EliminarTareaUseCase;
import application.port.in.tarea.ObtenerTareaUseCase;
import domain.model.Tarea;
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

    @PostMapping("/crear")
    public void create(@RequestBody ParametrosCrearTarea request) {
        crearTareaUseCase.crearTarea()
    }

    @GetMapping
    public ResponseEntity<List<TareaResponse>> getAll() {
        List<Tarea> tareas = obtenerTareaUseCase.consultarTareas(new ArrayList<>());

        return ResponseEntity.ok();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TareaResponse> get(@PathVariable Long id) {
        Tarea tarea = obtenerTareaUseCase.obtenerTarea(id);

        return ResponseEntity.ok(this.tareaWebMapper.toResponse(tarea));
    }

    @PutMapping("/actualizar/{id}")
    ResponseEntity<TareaResponse> put(@PathVariable Long id,
                                      @RequestBody ParametrosActualizarTarea parametrosActualizarTarea) {
        Tarea tareaActualizada = actualizarTareaUseCase.actualizarTarea();

        return ResponseEntity.ok(this.tareaWebMapper.toResponse(tareaActualizada));
    }

    @DeleteMapping("/borrar/{id}")
    public void delete(@PathVariable Long id) {
        eliminarTareaUseCase.eliminarTarea(id); //TODO PA DECIR UN OKAY O ALGO
    }
}
