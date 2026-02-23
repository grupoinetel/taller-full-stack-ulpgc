package adapter.out.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "USUARIOS")
public class UsuarioJpaEntity {

    @Id
    Long id;

    String name;

    String surname;

    String username;

    String avatar;

    @ManyToMany(mappedBy = "usuarios")
    List<TareaJpaEntity> tareas;
}
