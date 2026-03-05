package config;

import application.port.out.ComentarioPersistencePort;
import application.port.out.TareaPersistencePort;
import application.port.out.UsuarioPersistencePort;
import application.service.ComentarioService;
import application.service.TareaService;
import application.service.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BeanConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        CorsRegistration corsRegistration = registry.addMapping("/**");
        corsRegistration.allowedMethods("*");
    }

    @Bean
    public TareaService tareaService(TareaPersistencePort tareaPersistencePort) {
        return new TareaService(tareaPersistencePort);
    }

    @Bean
    public ComentarioService comentarioService(ComentarioPersistencePort comentarioPersistencePort) {
        return new ComentarioService(comentarioPersistencePort);
    }

    @Bean
    public UsuarioService usuarioService(UsuarioPersistencePort usuarioPersistencePort){
        return new UsuarioService(usuarioPersistencePort);
    }
}
