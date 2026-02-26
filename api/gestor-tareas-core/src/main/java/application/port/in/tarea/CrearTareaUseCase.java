package application.port.in.tarea;

import application.port.commands.CrearActualizarTareaCommand;
import domain.model.Tarea;

public interface CrearTareaUseCase {

    Tarea crearTarea(CrearActualizarTareaCommand tarea);
}
