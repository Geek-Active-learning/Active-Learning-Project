package activelearning.com.DL.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import activelearning.com.BL.entities.Unit;


public interface UnitRepository extends JpaRepository<Unit, Long> {
}