package adapter.out.persistence.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USUARIOS")
public class UsuarioJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOMBRE", nullable = false, length = 200)
    private String nombre;

    @Column(name = "AVATAR_URL")
    private String avatarUrl;

    @ManyToMany(mappedBy = "asignados")
    private List<TareaJpaEntity> tareas = new ArrayList<>();

    public UsuarioJpaEntity() {
    }

    public UsuarioJpaEntity(Long id) {
        this.id = id;
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

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public List<TareaJpaEntity> getTareas() {
        return tareas;
    }

    public void setTareas(List<TareaJpaEntity> tareas) {
        this.tareas = tareas;
    }
}
