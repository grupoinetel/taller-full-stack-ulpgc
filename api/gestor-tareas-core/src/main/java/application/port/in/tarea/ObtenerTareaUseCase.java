package application.port.in.tarea;

import domain.model.Tarea;

import java.util.List;

public interface ObtenerTareaUseCase {

    Tarea obtenerTarea(Long id);

    List<Tarea> consultarTareas(List<Object> filtros); //TODO ACTUALZIAR CON PARAMETROS
}
