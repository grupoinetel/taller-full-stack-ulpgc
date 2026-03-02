package adapter.in.mapper;

import adapter.in.dto.UsuarioResponse;
import domain.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioMapper {

    public UsuarioResponse toResponse(Usuario usuario) {
        return new UsuarioResponse(usuario);
    }

    public List<UsuarioResponse> toResponseList(List<Usuario> usuarios) {
        return usuarios.stream().map(this::toResponse).toList();
    }
}
