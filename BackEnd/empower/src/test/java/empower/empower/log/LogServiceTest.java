package empower.empower.log;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ExceptionCollector;

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
        Log newLog = new Log("Battery", "AAAAAA", "Battery",1, currDate);
        
        // mock the "save" operation 
        when(logService.saveLog(any(Log.class))).thenReturn(newLog);

        // act ***
        Log savedLog = logService.saveLog(newLog);
        
        // assert ***
        assertNotNull(savedLog);
        verify(log).save(newLog);
    }
}
//     @Test
//     void updateLog_NotFound_ReturnNull(){
//         Book book = new Book("Updated Title of Book");
//         Long bookId = 10L;
//         when(books.findById(bookId)).thenReturn(Optional.empty());
        
//         Book updatedBook = bookService.updateBook(bookId, book);
        
//         assertNull(updatedBook);
//         verify(books).findById(bookId);
//     }

//     @Test
//     void saveLog_ReturnRightCO2(){
//         //crete a mock log
//         Calendar myCalendar = new GregorianCalendar(2014, 2, 11);
//         Date currDate = new Date(myCalendar.getTimeInMillis());
//         Log newLog = new Log("Phone", "AAAAAAAA", "ICT",2, currDate);
//         Long id = 10L;

//         when();
        
        
//     }

// }
