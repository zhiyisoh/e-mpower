package empower.empower.user;

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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ExceptionCollector;

import empower.empower.log.entity.Log;
import empower.empower.log.repository.LogRepository;
import empower.empower.log.service.LogService;
import empower.empower.springjwt.models.User;
import empower.empower.springjwt.repository.UserRepository;
import empower.empower.springjwt.security.service.UserDetailsServiceImpl;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository user;

    @InjectMocks
    private UserDetailsServiceImpl userService;

    @Mock
    PasswordEncoder encoder;
    

    @Test
    void registerProfile_ReturnSavedProfile(){
        // arrange *** create profile
        User newUser = new User("name", "name@mail.com", encoder.encode("test"));
        
        // mock the successfully save user
        when(userService.saveUser(any(User.class))).thenReturn(newUser);

        // act ***
        User registeredUser = userService.saveUser(newUser);
        
        // assert ***
        assertNotNull(registeredUser);
        verify(user).save(newUser);
    }


}
