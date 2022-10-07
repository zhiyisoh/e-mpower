package empower.empower.log.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import empower.empower.log.entity.Log;

public interface LogRepository extends JpaRepository <Log, Long>{
    List<Log> findByUserId(Long userId);
    Optional<Log> findByIdAndUserId(Long id, Long userId);
}
