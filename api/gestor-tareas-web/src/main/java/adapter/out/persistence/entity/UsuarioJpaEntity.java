package adapter.out.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "USUARIOS")
public class UsuarioJpaEntity {

    @Id
    Long id;

    String nombre;

    String apellido;

    String nombreUsuario;

    String avatar;

    @ManyToMany(mappedBy = "usuarios")
    List<TareaJpaEntity> tareas;

    public UsuarioJpaEntity(Long id) {
        this.id = id;
    }

    public UsuarioJpaEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<TareaJpaEntity> getTareas() {
        return tareas;
    }

    public void setTareas(List<TareaJpaEntity> tareas) {
        this.tareas = tareas;
    }
}
