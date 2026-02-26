package application.service;

import application.port.commands.CrearActualizarTareaCommand;
import application.port.in.tarea.ActualizarTareaUseCase;
import application.port.in.tarea.CrearTareaUseCase;
import application.port.in.tarea.EliminarTareaUseCase;
import application.port.in.tarea.ObtenerTareaUseCase;
import application.port.out.TareaPersistencePort;
import domain.model.Tarea;
import exception.TareaNotFoundException;

import java.util.List;

public class TareaService implements
        CrearTareaUseCase,
        ObtenerTareaUseCase,
        ActualizarTareaUseCase,
        EliminarTareaUseCase {

    private final TareaPersistencePort tareaPersistencePort;

    public TareaService(TareaPersistencePort tareaPersistencePort) {
        this.tareaPersistencePort = tareaPersistencePort;
    }

    public Tarea actualizarTarea(CrearActualizarTareaCommand nuevaTarea) {

        Tarea tarea = obtenerTarea(nuevaTarea.getId());

        tarea.setDescripcion(nuevaTarea.getDescripcion());
        tarea.setFechaLimite(nuevaTarea.getFechaLimite());
        tarea.setNumeroTarea(nuevaTarea.getNumeroTarea());
        tarea.setFechaCreacion(nuevaTarea.getFechaCreacion());
        tarea.setPrioridad(nuevaTarea.getPrioridad());
        tarea.setEstado(nuevaTarea.getEstado());
        tarea.setPorcentajeRealizado(nuevaTarea.getPorcentajeRealizado());
        tarea.setUsuarios(nuevaTarea.getUsuarios());
        tarea.setAutor(nuevaTarea.getAutor());

        return tareaPersistencePort.save(tarea);
    }

    public Tarea crearTarea(CrearActualizarTareaCommand tarea) {
        Tarea tareaDomain = new Tarea();

        tareaDomain.setDescripcion(tarea.getDescripcion());
        tareaDomain.setFechaLimite(tarea.getFechaLimite());
        tareaDomain.setNumeroTarea(tarea.getNumeroTarea());
        tareaDomain.setFechaCreacion(tarea.getFechaCreacion());
        tareaDomain.setPrioridad(tarea.getPrioridad());
        tareaDomain.setEstado(tarea.getEstado());
        tareaDomain.setPorcentajeRealizado(tarea.getPorcentajeRealizado());
        tareaDomain.setUsuarios(tarea.getUsuarios());
        tareaDomain.setAutor(tarea.getAutor());

        return tareaPersistencePort.save(tareaDomain);
    }

    public void eliminarTarea(Long id) {
        tareaPersistencePort.delete(id);
    }

    public Tarea obtenerTarea(Long id) {
        return tareaPersistencePort.findById(id).orElseThrow(TareaNotFoundException::new);
    }

    public List<Tarea> consultarTareas(List<Object> filtros) {
        return tareaPersistencePort.findAll();
    }
}
