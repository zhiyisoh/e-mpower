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

    public List<Log> listUserLogs(Long userId){
        return logRepository.findByUserId(userId);
    }

    public Log saveLog(Log log){
        
        return logRepository.save(log);
    }

    public Log getLog(Long id){
        return logRepository.findById(id).get();
    }

    public void deleteLog(Long id){

        logRepository.deleteById(id);
    }

    public Log updateLog(Long id, Log newLog){
        return logRepository.findById(id).map(log -> {
            log.setItemName(newLog.getItemName());
            log.setItemNotes(newLog.getItemNotes());
            log.setItemType(newLog.getItemType());
            log.setCreatedDate(newLog.getCreatedDate());
            return logRepository.save(log);
        }).orElse(null);
    }
}
