package adapter.out.persistence.repository;

import adapter.out.persistence.entity.TareaJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareaSpringRepository extends JpaRepository<TareaJpaEntity, Long> {
}
