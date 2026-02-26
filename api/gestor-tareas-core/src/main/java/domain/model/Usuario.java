package domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Usuario {
    Long id;
    String nombre;
    String apellido;
    String username;
    String avatar; //TODO revisar tipo de variable
    List<Tarea> tareas;

    public Usuario(Long id, String nombreUsuario) {
        this.id = id;
        this.nombre = nombreUsuario;
    }

    public Usuario() {
    }
}
