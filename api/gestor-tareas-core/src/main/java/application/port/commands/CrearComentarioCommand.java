package application.port.commands;

import lombok.Getter;

import java.util.Date;

@Getter
public class CrearComentarioCommand {
    private String contenido;
    private Long idTarea;
    private Long idAutor;
    private Date fecha;

    public CrearComentarioCommand(String contenido, Long idTarea, Long idAutor, Date fecha) {
        this.contenido = contenido;
        this.idTarea = idTarea;
        this.idAutor = idAutor;
        this.fecha = fecha;
    }
}
