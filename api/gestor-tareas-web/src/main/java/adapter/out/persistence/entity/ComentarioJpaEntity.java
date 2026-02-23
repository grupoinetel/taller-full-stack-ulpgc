package adapter.out.persistence.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "COMMENTS")
public class ComentarioJpaEntity {

    @Id
    Long id;

    Date creation_date;

    String message;

    @ManyToOne
    UsuarioJpaEntity author;

    @ManyToOne
    TareaJpaEntity tarea;
}
