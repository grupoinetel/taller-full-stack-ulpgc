package adapter.in.dto;

import domain.enums.CategoriaTarea;
import domain.enums.EstadoTarea;
import domain.enums.PrioridadTarea;
import domain.model.Usuario;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class ParametrosCrearTarea {
    private Long id;
    private Integer numero;
    private String titulo;
    private String imagenUrl;
    private Date fechaLimite;
    private EstadoTarea estado;
    private PrioridadTarea prioridad;
    private CategoriaTarea categoria;
    private String descripcion;
    private Date fechaCreacion;
    private Integer porcentajeRealizado;
    @DecimalMin(value = "0.00", message = "tiempoEstimado debe ser mayor o igual que 0.00")
    @Digits(integer = 5, fraction = 2, message = "tiempoEstimado permite hasta 5 enteros y 2 decimales")
    private BigDecimal tiempoEstimado;
    private List<Usuario> asignados;
    private Usuario autor;

    @AssertTrue(message = "tiempoEstimado debe tener exactamente 2 decimales")
    public boolean isTiempoEstimadoConDosDecimales() {
        return tiempoEstimado == null || tiempoEstimado.scale() == 2;
    }
}
