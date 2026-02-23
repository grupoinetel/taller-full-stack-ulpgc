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
}
