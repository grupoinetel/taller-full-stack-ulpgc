package adapter.out.persistence.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "COMMENTS")
public class ComentarioJpaEntity {

    @Id
    Long id;

    Date fechaCreacion;

    String mensaje;

    @ManyToOne
    UsuarioJpaEntity autor;

    @ManyToOne
    TareaJpaEntity tarea;

    public ComentarioJpaEntity(Long id) {
        this.id = id;
    }

    public ComentarioJpaEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public UsuarioJpaEntity getAutor() {
        return autor;
    }

    public void setAutor(UsuarioJpaEntity autor) {
        this.autor = autor;
    }

    public TareaJpaEntity getTarea() {
        return tarea;
    }

    public void setTarea(TareaJpaEntity tarea) {
        this.tarea = tarea;
    }
}
