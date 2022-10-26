package empower.empower.bin.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // 404 Error
public class BinNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public BinNotFoundException(){
        
    }

    public BinNotFoundException(Long id){
        super("Could not find bin "+id);
    }
}
