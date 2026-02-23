package adapter.out.persistence.entity;

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

    Integer priority;

    String state;

    String description;

    Integer percentage;

    Date final_date;
    Date creation_date;

    @ManyToOne
    UsuarioJpaEntity author;

    @ManyToMany(mappedBy = "tareas")
    List<UsuarioJpaEntity> usuarios;

    @OneToMany
    List<ComentarioJpaEntity> comentarios;

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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public Date getFinal_date() {
        return final_date;
    }

    public void setFinal_date(Date final_date) {
        this.final_date = final_date;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public UsuarioJpaEntity getAuthor() {
        return author;
    }

    public void setAuthor(UsuarioJpaEntity author) {
        this.author = author;
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
