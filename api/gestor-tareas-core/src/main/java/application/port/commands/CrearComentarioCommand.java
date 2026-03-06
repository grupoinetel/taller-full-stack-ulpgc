package application.port.commands;

import lombok.Getter;

import java.util.Date;

@Getter
public class CrearComentarioCommand {
    private final String contenido;
    private final Long idTarea;
    private final Long idAutor;

    public CrearComentarioCommand(String contenido, Long idTarea, Long idAutor) {
        this.contenido = contenido;
        this.idTarea = idTarea;
        this.idAutor = idAutor;
    }
}
