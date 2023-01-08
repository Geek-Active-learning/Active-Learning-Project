package src.main.java.com.DL.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import src.main.java.com.BL.entities.Unit;

public interface UnitRepository extends JpaRepository<Unit, Long> {
}