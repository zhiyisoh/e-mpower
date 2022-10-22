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

    public double getUserCO2(Long userId){
        double UserCO2 = 0.0;
        List<Log> userWaste= this.listUserLogs(userId);
        for(Log lg: userWaste){
            UserCO2 += lg.getLogC02();
        }
        return UserCO2;
    }

    public double getAllCO2(){
        double total = 0.0;
        List<Log> alLogs = listAllLogs();
        for(Log lg: alLogs){
            total += lg.getLogC02();
        }
        return total;
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

}
