package empower.empower.log.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.validator.constraints.ModCheck;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import empower.empower.log.entity.Log;
import empower.empower.log.repository.LogRepository;
import empower.empower.springjwt.models.User;

@ExtendWith(MockitoExtension.class)
public class LogServiceTest {
    
    @Mock
    private LogRepository logs;

    @InjectMocks
    private LogService logService;

    // @Test
    // void addLog_SameId_ReturnNull() {

    //     // assert ***
    //     assertNull(savedLog);
    //     verify(books).findByTitle(book.getTitle());

    //     Log log = new Log(1,"item 1");
    //     List<Log> sameIds = new ArrayList<Log>();
    //     sameIds.add(new Log(1,"item 2"));
    //     when(logs.findByIdAndUserId(log.getId())).thenReturn(sameIds);
    //     Log savedLog = logService.saveLog(log);
    //     assertNull(savedLog);
    //     verify(logs).findByTitle(log.getId());

    // }   //highlight then ctrl+/ to uncomment

//     @Test
//     public void deleteLog_ValidBookId_Success() throws Exception {
//     Log log = logs.save(new Log(1,"item 1"));
//     URI uri = new URI(baseUrl + port + "/logging/" + log.getId().longValue());
//     users.save(new User("admin", encoder.encode("goodpassword"), "ROLE_ADMIN"));

//     ResponseEntity<Void> result = restTemplate.withBasicAuth("admin", "goodpassword")
//     .exchange(uri, HttpMethod.DELETE, null, Void.class);
//     assertEquals(200, result.getStatusCode().value());
//     // An empty Optional should be returned by "findById" after deletion
//     Optional<Log> emptyValue = Optional.empty();
//     assertEquals(emptyValue, logs.findByIdAndItemName(log.getId(),log.getItemName()));
//     }

//     @Test
//     public void deleteLog_InvalidLogId_Failure() throws Exception {
//     URI uri = new URI(baseUrl + port + "/logging/1");
//     users.save(new User("admin", encoder.encode("goodpassword"), "ROLE_ADMIN"));
   
//     ResponseEntity<Void> result = restTemplate.withBasicAuth("admin", "goodpassword")
//     .exchange(uri, HttpMethod.DELETE, null, Void.class);
   
//     assertEquals(404, result.getStatusCode().value());
//     }

//     @Test
//     public void updateBook_ValidLogId_Success() throws Exception {
//     Log log = logs.save(new Log(1,"item 1"));
//     URI uri = new URI(baseUrl + port + "/logging/" + log.getId().longValue());
//     Log newLogInfo = new Book(1,"item 2");
//     users.save(new User("admin", encoder.encode("goodpassword"), "ROLE_ADMIN"));

//     ResponseEntity<Log> result = restTemplate.withBasicAuth("admin", "goodpassword")
//     .exchange(uri, HttpMethod.PUT, new HttpEntity<>(newLogInfo), Log.class);

//     assertEquals(200, result.getStatusCode().value());
//     assertEquals(newLogInfo.getItemName(), result.getBody().getItemName());
//     }

//     @Test
//     public void updateLog_InvalidLogId_Failure() throws Exception {
//     URI uri = new URI(baseUrl + port + "/books/1");
//     Log newLogInfo = new Book(1,"new name");
//     users.save(new User("admin", encoder.encode("goodpassword"), "ROLE_ADMIN"));
   
//     ResponseEntity<Log> result = restTemplate.withBasicAuth("admin", "goodpassword")
//    .exchange(uri, HttpMethod.PUT, new HttpEntity<>(newLogInfo), Log.class);
   
//     assertEquals(404, result.getStatusCode().value());
//     }

}
