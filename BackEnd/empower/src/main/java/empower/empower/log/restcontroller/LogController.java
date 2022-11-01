package empower.empower.log.restcontroller;

import empower.empower.log.entity.Emissions;
import empower.empower.log.entity.Log;
import empower.empower.log.repository.EmissionsRepository;
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
    private EmissionsRepository emRepo;
    private double totalCO2;

    public LogController(LogRepository logRepo, UserRepository userRepo, LogService logService, EmissionsRepository emRepo) {
        this.logRepo = logRepo;
        this.userRepo = userRepo;
        this.logService = logService;
        this.emRepo = emRepo;
    }

    /*
     * Get the list of logs by user id, 
     * will return null if there are no logs or a NoSuchElementException is caught.
     */
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

    //Gets a specific log by its id
    @GetMapping("/{id}")
    public ResponseEntity<Log> get(@PathVariable Long id) {
        try {
            Log l = logService.getLog(id);
            return new ResponseEntity<Log>(l, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Log>(HttpStatus.NOT_FOUND);
        }
    }

    //emissions for all user database
    
    @GetMapping("/co2sum")
    public ResponseEntity<Double> getco2all(){
        try{
            List<Log> logs = logService.listAllLogs();

            for(Log log : logs){
                totalCO2 += log.getEmissions().getEmissionsSaved();
            }
            
            return new ResponseEntity<Double>(totalCO2, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<Double> (HttpStatus.NOT_FOUND);
        }
    }

    //emissions for user 

    @GetMapping("/co2sum/{id}")
    public ResponseEntity<Double> getUserCo2(@PathVariable(value = "user_id") Long userId){
        try{
            List<Log> logs = logService.listUserLogs(userId);

            for(Log log : logs){
                totalCO2 += log.getEmissions().getEmissionsSaved();
            }
            
            return new ResponseEntity<Double>(totalCO2, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<Double> (HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addlog/{user_id}")
    public Log add(@PathVariable(value = "user_id") Long userId, @RequestBody Log log) {
        if (emRepo.findByItemName(log.getItemName()) == null){
            throw new EmissionsNotFoundException();
        }

        return userRepo.findById(userId)
                .map(user -> {
                    log.setUser(user);
                    Set<Log> logs = user.getLogs();
                    Emissions e = emRepo.findByItemName(log.getItemName());
                    log.setEmissions(e);
                    logs.add(log);
                    return logService.saveLog(log);
                }).orElseThrow(() -> new UserNotFoundException(userId));
    }

    //Update a specific user's log with the user id and the log id
    //If the user id does not exist then a UserNotFoundException is thrown
    //If the log id does not exist then a LogNotFoundException is thrown
    @PutMapping("/updatelog/{user_id}/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "user_id") Long userId, @PathVariable Long id,
            @Valid @RequestBody Log log) {

        //throws LogNotFoundException if cannot be found by id, continues otherwise
        checkIfLogExistsById(id);

        if (!userRepo.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }

        return logRepo.findByIdAndUserId(id, userId).map(oldLog -> {
            oldLog.setItemName(log.getItemName());
            oldLog.setItemNotes(log.getItemNotes());
            oldLog.setItemType(log.getItemType());
            oldLog.setCreatedDate(log.getCreatedDate());
            logService.saveLog(oldLog);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new LogNotFoundException(id));

    }

    //throws LogNotFoundException if cannot be found by id, continues otherwise
    private void checkIfLogExistsById(Long id) {
        if (!logRepo.existsById(id)) {
            throw new LogNotFoundException(id);
        }
    }

    //Deletes a specific user's log
    //If the user id does not exist then a UserNotFoundException is thrown
    //If the log id does not exist then a LogNotFoundException is thrown
    @DeleteMapping("/deletelog/{user_id}/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "user_id") Long userId, @PathVariable Long id) {
        
        //throws LogNotFoundException if cannot be found by id, continues otherwise
        checkIfLogExistsById(id);

        Log log = logService.getLog(id);
        return userRepo.findById(userId)
                .map(user -> {
                    Set<Log> logs = user.getLogs();
                    logs.remove(log);
                    logService.deleteLog(id);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new UserNotFoundException(userId));

    }

}
