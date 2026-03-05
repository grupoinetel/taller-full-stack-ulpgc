package application.service;

import application.port.commands.CrearActualizarTareaCommand;
import application.port.in.tarea.ActualizarTareaUseCase;
import application.port.in.tarea.CrearTareaUseCase;
import application.port.in.tarea.EliminarTareaUseCase;
import application.port.in.tarea.ObtenerTareaUseCase;
import application.port.out.TareaPersistencePort;
import domain.model.Tarea;
import domain.model.Usuario;
import exception.TareaNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

        tarea.setNumero(tarea.getNumero());
        tarea.setTitulo(nuevaTarea.getTitulo());
        tarea.setImagenUrl(nuevaTarea.getImagenUrl());
        tarea.setDescripcion(nuevaTarea.getDescripcion());
        tarea.setFechaLimite(nuevaTarea.getFechaLimite());
        tarea.setFechaCreacion(tarea.getFechaCreacion());
        tarea.setEstado(nuevaTarea.getEstado());
        tarea.setPrioridad(nuevaTarea.getPrioridad());
        tarea.setCategoria(nuevaTarea.getCategoria());
        tarea.setPorcentajeRealizado(nuevaTarea.getPorcentajeRealizado());
        tarea.setTiempoEstimado(nuevaTarea.getTiempoEstimado());

        List<Usuario> asignados = new ArrayList<>();

        nuevaTarea.getAsignados().forEach(usuario -> asignados.add(new Usuario(usuario)));

        tarea.setAsignados(asignados);
        tarea.setAutor(new Usuario(tarea.getAutor().getId()));

        return tareaPersistencePort.save(tarea);
    }

    public Tarea crearTarea(CrearActualizarTareaCommand tarea) {
        Tarea tareaDomain = new Tarea(tarea.getId());

        Random random = new Random();
        tareaDomain.setNumero(random.nextInt(100000));
        tareaDomain.setTitulo(tarea.getTitulo());
        tareaDomain.setImagenUrl(tarea.getImagenUrl());
        tareaDomain.setDescripcion(tarea.getDescripcion());
        tareaDomain.setFechaCreacion(new java.util.Date());
        tareaDomain.setFechaLimite(tarea.getFechaLimite());
        tareaDomain.setEstado(tarea.getEstado());
        tareaDomain.setPrioridad(tarea.getPrioridad());
        tareaDomain.setCategoria(tarea.getCategoria());
        tareaDomain.setPorcentajeRealizado(tarea.getPorcentajeRealizado());
        tareaDomain.setTiempoEstimado(tarea.getTiempoEstimado());

        List<Usuario> asignados = new ArrayList<>();

        tarea.getAsignados().forEach(usuario -> asignados.add(new Usuario(usuario)));

        tareaDomain.setAsignados(asignados);

        tareaDomain.setAutor(new Usuario(tarea.getAutor()));

        return tareaPersistencePort.save(tareaDomain);
    }

    public void eliminarTarea(Long id) {
        tareaPersistencePort.delete(id);
    }

    public Tarea obtenerTarea(Long id) {
        return tareaPersistencePort.findById(id).orElseThrow(TareaNotFoundException::new);
    }

    public List<Tarea> consultarTareas() {
        return tareaPersistencePort.findAll();
    }
}
