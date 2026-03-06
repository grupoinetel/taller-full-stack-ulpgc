package adapter.in.mapper;

import adapter.in.dto.ComentarioResponse;
import adapter.in.dto.ParametrosCrearComentario;
import application.port.commands.CrearComentarioCommand;
import domain.model.Comentario;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ComentarioWebMapper {

    ComentarioResponse toResponse(Comentario comentario) {
        return new ComentarioResponse(comentario);
    }

    public List<ComentarioResponse> toComentarioResponseList(List<Comentario> comentarios) {
        return comentarios.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public CrearComentarioCommand toCommand(Long id, ParametrosCrearComentario request) {
        return new CrearComentarioCommand(
                request.getContenido(),
                id,
                request.getIdAutor()
        );
    }
}
