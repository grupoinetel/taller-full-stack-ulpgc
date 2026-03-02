package adapter.in.dto;

import domain.model.Usuario;
import lombok.Getter;

@Getter
public class UsuarioResponse {

    private final Long id;
    private final String nombre;
    private final String avatarUrl;


    public UsuarioResponse(Usuario usuario) {
        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
        this.avatarUrl = usuario.getAvatarUrl();
    }
}
