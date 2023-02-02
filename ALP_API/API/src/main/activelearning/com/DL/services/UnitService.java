package activelearning.com.DL.services;

import activelearning.com.BL.entities.Unit;
import activelearning.com.DL.constants.Messages;
import activelearning.com.DL.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnitService {

    private final UnitRepository unitRepository;

    @Autowired
    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    public List<Unit> getUnits() {
        return  unitRepository.findAll();
    }

    public Optional<Unit> getUnitById(Long unitId) {
        return unitRepository.findById(unitId);
    }

    public ResponseEntity<String> createNewUnit(Unit unit) {
        unitRepository.save(unit);
        return ResponseEntity.status(HttpStatus.OK).body(String.format(Messages.USER_CREATED_SUCCESS_MESSAGE, unit.toString()));
    }

    public ResponseEntity<String> updateUnit(Unit unit, Long unitId) {
        return null;
    }

    public ResponseEntity<String> deleteUnit(Long unitId) {
        return null;
    }
}
