package empower.empower.log.restcontroller;

import empower.empower.log.entity.Log;
import empower.empower.log.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/logging")
public class LogController {
    @Autowired
    LogService logService;

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

    @PostMapping("")
    public void add(@RequestBody Log log) {
        logService.saveLog(log);
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
