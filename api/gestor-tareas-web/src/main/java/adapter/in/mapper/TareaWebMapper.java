package adapter.in.mapper;

import adapter.in.dto.ParametrosCrearTarea;
import adapter.in.dto.TareaDetalleResponse;
import adapter.in.dto.TareaResponse;
import application.port.commands.CrearActualizarTareaCommand;
import domain.model.Tarea;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TareaWebMapper {

    public TareaResponse toResponse(Tarea tarea) {
        return new TareaResponse(tarea);
    }

    public TareaDetalleResponse toDetalleResponse(Tarea tarea) {
        return new TareaDetalleResponse(tarea);
    }

    public List<TareaResponse> toResponseList(List<Tarea> tareas) {
        return tareas.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public CrearActualizarTareaCommand toCommand(ParametrosCrearTarea parametros) {
        return new CrearActualizarTareaCommand(
                parametros.getId(),
                parametros.getNumero(),
                parametros.getTitulo(),
                parametros.getImagenUrl(),
                parametros.getFechaLimite(),
                parametros.getEstado(),
                parametros.getPrioridad(),
                parametros.getCategoria(),
                parametros.getDescripcion(),
                parametros.getFechaCreacion(),
                parametros.getPorcentajeRealizado(),
                parametros.getTiempoEstimado(),
                parametros.getAsignadosIds(),
                parametros.getAutorId()
        );
    }
}
