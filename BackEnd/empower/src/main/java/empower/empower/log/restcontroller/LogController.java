package empower.empower.log.restcontroller;

import empower.empower.log.entity.Log;
import empower.empower.springjwt.models.User;
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

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/logging")
public class LogController {
    @Autowired
    LogService logService;

    private LogRepository logRepo;
    private UserRepository userRepo;

    public LogController(LogRepository logRepo, UserRepository userRepo){
        this.logRepo = logRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("")
    public List<Log> list() {
        return logService.listAllLogs();
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

    @PostMapping("/addlog/{user_id}")
    public Log add(@PathVariable (value = "user_id") Long userId, @RequestBody Log log) {
        return userRepo.findById(userId)
        .map(user ->{
            log.setUser(user);
            Set<Log> logs = user.getLogs();
            logs.add(log);
            return logService.saveLog(log);
        }).orElseThrow(() -> new UserNotFoundException(userId));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Log log, @PathVariable Long id) {
        try {
            Log existLog = logService.getLog(id);
            log.setId(id);            
            logService.saveLog(log);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        logService.deleteLog(id);
    }
}
