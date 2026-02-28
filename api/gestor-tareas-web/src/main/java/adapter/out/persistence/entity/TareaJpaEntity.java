package adapter.out.persistence.entity;

import domain.enums.EstadoTarea;
import domain.enums.Prioridad;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TAREAS")
public class TareaJpaEntity {

    //TODO ANALIZAR SI COLOCAR LA ANOTACION @COLUMN PARA RESPETAR NOMBRES

    @Id
    Long id;

    Integer numTarea;

    @Enumerated(EnumType.STRING)
    Prioridad prioridad;

    @Enumerated(EnumType.STRING)
    EstadoTarea estado;

    String descripcion;

    Integer porcentaje;

    Date fechaFinalizacion;
    Date fechaCreacion;

    @ManyToOne
    UsuarioJpaEntity autor;

    @ManyToMany
    @JoinTable(
            name = "TAREA_USUARIO",
            joinColumns = @JoinColumn(name = "tarea_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    List<UsuarioJpaEntity> usuarios;

    @OneToMany
    List<ComentarioJpaEntity> comentarios;

    public TareaJpaEntity(Long id) {
        this.id = id;
    }

    public TareaJpaEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumTarea() {
        return numTarea;
    }

    public void setNumTarea(Integer numTarea) {
        this.numTarea = numTarea;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad priority) {
        this.prioridad = priority;
    }

    public EstadoTarea getEstado() {
        return estado;
    }

    public void setEstado(EstadoTarea state) {
        this.estado = state;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String description) {
        this.descripcion = description;
    }

    public Integer getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Integer percentage) {
        this.porcentaje = percentage;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date final_date) {
        this.fechaFinalizacion = final_date;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date creation_date) {
        this.fechaCreacion = creation_date;
    }

    public UsuarioJpaEntity getAutor() {
        return autor;
    }

    public void setAutor(UsuarioJpaEntity author) {
        this.autor = author;
    }

    public List<UsuarioJpaEntity> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioJpaEntity> usuarios) {
        this.usuarios = usuarios;
    }

    public List<ComentarioJpaEntity> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioJpaEntity> comentarios) {
        this.comentarios = comentarios;
    }
}
