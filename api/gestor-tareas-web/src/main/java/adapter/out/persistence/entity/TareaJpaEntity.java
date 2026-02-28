package adapter.out.persistence.entity;

import domain.enums.CategoriaTarea;
import domain.enums.EstadoTarea;
import domain.enums.PrioridadTarea;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TAREAS")
public class TareaJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NUMERO", nullable = false, unique = true)
    private Integer numero;

    @Column(name = "TITULO", nullable = false, length = 250)
    private String titulo;

    @Column(name = "IMAGEN_URL")
    private String imagenUrl;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_LIMITE")
    private Date fechaLimite;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO", nullable = false, length = 50)
    private EstadoTarea estado;

    @Enumerated(EnumType.STRING)
    @Column(name = "PRIORIDAD", nullable = false, length = 50)
    private PrioridadTarea prioridad;

    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORIA", length = 50)
    private CategoriaTarea categoria;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_CREACION", nullable = false)
    private Date fechaCreacion;

    @Column(name = "PORCENTAJE_REALIZADO", nullable = false)
    private Integer porcentajeRealizado;

    @Column(name = "TIEMPO_ESTIMADO", precision = 7, scale = 2)
    private BigDecimal tiempoEstimado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "AUTOR_ID", nullable = false)
    private UsuarioJpaEntity autor;

    @ManyToMany
    @JoinTable(
            name = "TAREAS_ASIGNADOS",
            joinColumns = @JoinColumn(name = "TAREA_ID"),
            inverseJoinColumns = @JoinColumn(name = "USUARIO_ID")
    )
    private List<UsuarioJpaEntity> asignados = new ArrayList<>();

    @OneToMany(mappedBy = "tarea")
    private List<ComentarioJpaEntity> comentarios = new ArrayList<>();

    public TareaJpaEntity() {
    }

    public TareaJpaEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public EstadoTarea getEstado() {
        return estado;
    }

    public void setEstado(EstadoTarea estado) {
        this.estado = estado;
    }

    public PrioridadTarea getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(PrioridadTarea prioridad) {
        this.prioridad = prioridad;
    }

    public CategoriaTarea getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaTarea categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getPorcentajeRealizado() {
        return porcentajeRealizado;
    }

    public void setPorcentajeRealizado(Integer porcentajeRealizado) {
        this.porcentajeRealizado = porcentajeRealizado;
    }

    public BigDecimal getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(BigDecimal tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }

    public UsuarioJpaEntity getAutor() {
        return autor;
    }

    public void setAutor(UsuarioJpaEntity autor) {
        this.autor = autor;
    }

    public List<UsuarioJpaEntity> getAsignados() {
        return asignados;
    }

    public void setAsignados(List<UsuarioJpaEntity> asignados) {
        this.asignados = asignados;
    }

    public List<ComentarioJpaEntity> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioJpaEntity> comentarios) {
        this.comentarios = comentarios;
    }
}
