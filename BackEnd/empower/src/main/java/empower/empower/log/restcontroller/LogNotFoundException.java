package empower.empower.log.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // 404 Error
public class LogNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public LogNotFoundException(Long id) {
        super("Could not find log " + id);
    }
    
}