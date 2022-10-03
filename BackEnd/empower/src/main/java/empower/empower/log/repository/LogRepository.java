package empower.empower.log.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import empower.empower.log.entity.Log;

public interface LogRepository extends JpaRepository <Log, Long>{
    
}
