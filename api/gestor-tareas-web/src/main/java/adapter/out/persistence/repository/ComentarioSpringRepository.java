package adapter.out.persistence.repository;

import adapter.out.persistence.entity.ComentarioJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioSpringRepository extends JpaRepository<ComentarioJpaEntity, Long> {

}
