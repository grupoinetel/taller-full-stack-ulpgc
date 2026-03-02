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

        tarea.setNumero(nuevaTarea.getNumero());
        tarea.setTitulo(nuevaTarea.getTitulo());
        tarea.setImagenUrl(nuevaTarea.getImagenUrl());
        tarea.setDescripcion(nuevaTarea.getDescripcion());
        tarea.setFechaLimite(nuevaTarea.getFechaLimite());
        tarea.setFechaCreacion(nuevaTarea.getFechaCreacion());
        tarea.setEstado(nuevaTarea.getEstado());
        tarea.setPrioridad(nuevaTarea.getPrioridad());
        tarea.setCategoria(nuevaTarea.getCategoria());
        tarea.setPorcentajeRealizado(nuevaTarea.getPorcentajeRealizado());
        tarea.setTiempoEstimado(nuevaTarea.getTiempoEstimado());
        tarea.setAsignados(nuevaTarea.getAsignados());
        tarea.setAutor(nuevaTarea.getAutor());

        return tareaPersistencePort.save(tarea);
    }

    public Tarea crearTarea(CrearActualizarTareaCommand tarea) {
        Tarea tareaDomain = new Tarea(tarea.getId());

        tareaDomain.setNumero(tarea.getNumero());
        tareaDomain.setTitulo(tarea.getTitulo());
        tareaDomain.setImagenUrl(tarea.getImagenUrl());
        tareaDomain.setDescripcion(tarea.getDescripcion());
        tareaDomain.setFechaCreacion(tarea.getFechaCreacion());
        tareaDomain.setFechaLimite(tarea.getFechaLimite());
        tareaDomain.setEstado(tarea.getEstado());
        tareaDomain.setPrioridad(tarea.getPrioridad());
        tareaDomain.setCategoria(tarea.getCategoria());
        tareaDomain.setPorcentajeRealizado(tarea.getPorcentajeRealizado());
        tareaDomain.setTiempoEstimado(tarea.getTiempoEstimado());
        tareaDomain.setAsignados(tarea.getAsignados());
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
