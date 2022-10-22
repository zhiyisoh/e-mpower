package empower.empower.log.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // 404 Error
public class EmissionNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public EmissionNotFoundException(String name) {
        super("Wrong Item Name " + name);
    }
    
}

