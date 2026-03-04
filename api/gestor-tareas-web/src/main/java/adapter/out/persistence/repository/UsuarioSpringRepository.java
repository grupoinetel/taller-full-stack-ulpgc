package adapter.out.persistence.repository;

import adapter.out.persistence.entity.UsuarioJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioSpringRepository extends JpaRepository<UsuarioJpaEntity, Long> {
}
