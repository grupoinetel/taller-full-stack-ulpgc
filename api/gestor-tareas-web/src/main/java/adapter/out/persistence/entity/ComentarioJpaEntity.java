package adapter.out.persistence.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "COMENTARIOS")
public class ComentarioJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TAREA_ID", nullable = false)
    private TareaJpaEntity tarea;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "AUTOR_ID", nullable = false)
    private UsuarioJpaEntity autor;

    @Column(name = "MENSAJE", nullable = false)
    private String mensaje;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA", nullable = false)
    private Date fecha;

    public ComentarioJpaEntity() {
    }

    public ComentarioJpaEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TareaJpaEntity getTarea() {
        return tarea;
    }

    public void setTarea(TareaJpaEntity tarea) {
        this.tarea = tarea;
    }

    public UsuarioJpaEntity getAutor() {
        return autor;
    }

    public void setAutor(UsuarioJpaEntity autor) {
        this.autor = autor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
