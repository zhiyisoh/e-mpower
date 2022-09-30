package empower.empower.log;

import java.util.List;
import java.util.Optional;

public interface LogRepository {
   Long save(Log log);
    int editLog(Log log);
    int deleteById(Long id);
    List<Log> findAll();

    // Using Optional - the return value of this method may contain a null value
    Optional<Log> findById(Long id);
 
}
