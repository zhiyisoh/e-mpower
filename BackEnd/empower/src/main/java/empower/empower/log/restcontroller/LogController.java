package empower.empower.log.restcontroller;

import empower.empower.log.entity.Emissions;
import empower.empower.log.entity.Log;
import empower.empower.log.repository.EmissionRepository;
import empower.empower.log.repository.LogRepository;
import empower.empower.log.service.LogService;
import empower.empower.springjwt.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/logging")
public class LogController {
    @Autowired
    LogService logService;

    private LogRepository logRepo;
    private UserRepository userRepo;
    private EmissionRepository emRepo;
    private Double totalCO2 = 0.0;

    public LogController(LogRepository logRepo, UserRepository userRepo, EmissionRepository emRepo, LogService logService) {
        this.logRepo = logRepo;
        this.userRepo = userRepo;
        this.emRepo = emRepo;
        this.logService = logService;
    }

    @GetMapping("/userlogs/{user_id}")
    public List<Log> listLogs(@PathVariable Long user_id) {
        try {

            List<Log> list = logService.listUserLogs(user_id);

            if (list.isEmpty()) {
                return null;
            }

            return list;
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Log> get(@PathVariable Long id) {
        try {
            Log l = logService.getLog(id);
            return new ResponseEntity<Log>(l, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Log>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/co2sum")
    public ResponseEntity<Double> getco2(){
        try{
            Double de = 4.20;
            return new ResponseEntity<Double>(de, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<Double> (HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addlog/{user_id}")
    public Log add(@PathVariable(value = "user_id") Long userId, @RequestBody Log log) {
        return userRepo.findById(userId)
                .map(user -> {
                    Emissions e = emRepo.findByItemName(log.getItemName());
                    log.setEmissions(e);
                    totalCO2 += e.getEmissionsSaved() * log.getItemQuantity();
                    log.setUser(user);
                    Set<Log> logs = user.getLogs();
                    logs.add(log);
                    return logService.saveLog(log);
                }).orElseThrow(() -> new UserNotFoundException(userId));
    }

    @PutMapping("/updatelog/{user_id}/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "user_id") Long userId, @PathVariable Long id,
            @Valid @RequestBody Log log) {

        if (!logRepo.existsById(id)) {
            throw new LogNotFoundException(id);
        }

        if (!userRepo.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }

        return logRepo.findByIdAndUserId(id, userId).map(oldLog -> {
            totalCO2 -= oldLog.getEmissions().getEmissionsSaved() * oldLog.getItemQuantity();

            oldLog.setItemName(log.getItemName());
            oldLog.setItemNotes(log.getItemNotes());
            oldLog.setItemQuantity(log.getItemQuantity());
            oldLog.setItemType(log.getItemType());
            oldLog.setCreatedDate(log.getCreatedDate());
            logService.saveLog(oldLog);

            totalCO2 += oldLog.getEmissions().getEmissionsSaved() * oldLog.getItemQuantity();
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new LogNotFoundException(id));

    }

    @DeleteMapping("/deletelog/{user_id}/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "user_id") Long userId, @PathVariable Long id) {
        if (!logRepo.existsById(id)) {
            throw new LogNotFoundException(id);
        }

        Log log = logService.getLog(id);
        return userRepo.findById(userId)
                .map(user -> {
                    totalCO2 -= log.getEmissions().getEmissionsSaved() * log.getItemQuantity();
                    Set<Log> logs = user.getLogs();
                    logs.remove(log);
                    logService.deleteLog(id);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new UserNotFoundException(userId));

    }

}
