package empower.empower.log;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.sql.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import empower.empower.log.entity.Log;
import empower.empower.log.repository.LogRepository;
import empower.empower.log.service.LogService;
import empower.empower.springjwt.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class LogServiceTest {
    
    @Mock
    private LogRepository log;

    @InjectMocks
    private LogService logService;

    @Mock
    private UserRepository user;
    

    @Test
    void saveLog_ReturnSavedLog(){
        // arrange *** create log
        Calendar myCalendar = new GregorianCalendar(2014, 2, 11);
        Date currDate = new Date(myCalendar.getTimeInMillis());
        Log newLog = new Log("newObject", "AAAAAA", "type", currDate);
        
        // mock the "save" operation 
        when(logService.saveLog(any(Log.class))).thenReturn(newLog);

        // act ***
        Log savedLog = logService.saveLog(newLog);
        
        // assert ***
        assertNotNull(savedLog);
        verify(log).save(newLog);
    }

    @Test
    void updateLog_NotFound_ReturnNull(){
        Calendar myCalendar = new GregorianCalendar(2014, 2, 11);
        Date currDate = new Date(myCalendar.getTimeInMillis());
        Log someLog = new Log("name", "notes", "lamp", currDate);

        Long id = 99L;
        when(log.findById(id)).thenReturn(Optional.empty());

        Log updatedLog = logService.updateLog(id, someLog);
        assertNull(updatedLog);
        verify(log).findById(id);
    }

    @Test
    void deleteLog_LogNotFound_ReturnNull(){
        //arrange, nothing to arrange?


        //act
        Long id = 40L;

        //assert
        logService.deleteLog(id);
        verify(log).deleteById(id);
    }
}
