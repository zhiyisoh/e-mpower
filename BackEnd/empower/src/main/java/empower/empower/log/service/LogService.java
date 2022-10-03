package empower.empower.log.service;

import empower.empower.log.entity.Log;
import empower.empower.log.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.transaction.Transactional;

@Service
@Transactional
public class LogService {
    @Autowired
    private LogRepository logRepository;

    public List<Log> listAllLogs(){
        return logRepository.findAll();
    }

    public void saveLog(Log log){
        logRepository.save(log);
    }

    public Log getLog(Long id){
        return logRepository.findById(id).get();
    }

    public void deleteLog(Long id){
        logRepository.deleteById(id);
    }

}
