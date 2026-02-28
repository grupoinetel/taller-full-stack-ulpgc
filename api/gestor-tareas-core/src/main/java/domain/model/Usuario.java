package domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Usuario {
    private Long id;
    private String nombre;
    private String avatarUrl;
    private List<Tarea> tareas;

    public Usuario(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Usuario() {
    }
}
