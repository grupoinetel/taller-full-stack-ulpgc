package adapter.out.persistence.repository;

import adapter.out.persistence.entity.ComentarioJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioSpringRepository extends JpaRepository<ComentarioJpaEntity, Long> {
    List<ComentarioJpaEntity> findByTareaId(Long idTarea);
}
