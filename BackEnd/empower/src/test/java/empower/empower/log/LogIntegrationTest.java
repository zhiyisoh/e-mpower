package empower.empower.log;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;
import java.net.http.HttpHeaders;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.sql.Date;

import empower.empower.log.entity.*;
import empower.empower.log.repository.*;
import empower.empower.bin.repository.*;
import empower.empower.springjwt.models.*;
import empower.empower.springjwt.repository.*;

/** Start an actual HTTP server listening at a random port*/
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class LogIntegrationTest {

	@LocalServerPort
	private int port;

	private final String baseUrl = "http://localhost:";

	@Autowired
	/**
	 * Use TestRestTemplate for testing a real instance of your application as an external actor.
	 * Convenient subclass of RestTemplate that is suitable for integration tests.
 	 * It is fault tolerant, and optionally can carry Basic authentication headers.
	 */
	private TestRestTemplate restTemplate;

	@Autowired
	private LogRepository logRepo;

	@Autowired
	private BinRepository binRepo;

    @Autowired
    private UserRepository userRepo;

	@Autowired
    private EmissionsRepository emRepo;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;


	@AfterEach
	void tearDown(){
		logRepo.deleteAll();
        binRepo.deleteAll();
		userRepo.deleteAll();
	}

	@Test
	public void getLog_InvalidLogId_Failure() throws Exception {

		
		//creating role for authentication
		Set<Role> roles = new HashSet<Role>();
		Role userRole = roleRepository.save(new Role(1,ERole.ROLE_USER));
		Role adminRole = roleRepository.save(new Role(2,ERole.ROLE_ADMIN));
		roles.add(userRole);
		roles.add(adminRole);

		userRepo.save(new User("admin", "admin@gmail.com", encoder.encode("goodpassword"), roles));
		URI uri = new URI(baseUrl + port + "/api/logging/0");
		ResponseEntity<Log> result = restTemplate.withBasicAuth("admin", "goodpassword").exchange(uri, HttpMethod.GET, null, Log.class);

		assertEquals(404, result.getStatusCode().value());
	}

	@Test
	public void getLog_validLogId_Success() throws Exception {

		//creating role for authentication
		Set<Role> roles = new HashSet<Role>();
		Role userRole = roleRepository.save(new Role(1,ERole.ROLE_USER));
		Role adminRole = roleRepository.save(new Role(2,ERole.ROLE_ADMIN));
		roles.add(userRole);
		roles.add(adminRole);
		User user = new User("admin", "admin@gmail.com", encoder.encode("goodpassword"), roles);
		userRepo.save(user);
		Emissions em = emRepo.save(new Emissions("AA", 0.02));

		Calendar myCalendar = new GregorianCalendar(2014, 2, 11);
        Date currDate = new Date(myCalendar.getTimeInMillis());
        Log savedLog = logRepo.save(new Log("AA", "Stuff", "Bulb", currDate, user, em));
		Long logId = savedLog.getId();

		URI uri = new URI(baseUrl + port + "/api/logging/" + logId);
		ResponseEntity<Log> result = restTemplate.withBasicAuth("admin", "goodpassword").exchange(uri, HttpMethod.GET, null, Log.class);

		assertEquals(200, result.getStatusCode().value());
	}

	@Test
	public void deleteLog_validLogId_Success() throws Exception {

		//creating role for authentication
		Set<Role> roles = new HashSet<Role>();
		Role userRole = roleRepository.save(new Role(1,ERole.ROLE_USER));
		Role adminRole = roleRepository.save(new Role(2,ERole.ROLE_ADMIN));
		roles.add(userRole);
		roles.add(adminRole);
		User user = new User("admin", "admin@gmail.com", encoder.encode("goodpassword"), roles);
		userRepo.save(user);

		Emissions em = emRepo.save(new Emissions("AA", 0.02));

		Calendar myCalendar = new GregorianCalendar(2014, 2, 11);
        Date currDate = new Date(myCalendar.getTimeInMillis());
        Log savedLog = logRepo.save(new Log("AA", "Stuff", "Bulb", currDate, user, em));
		Long logId = savedLog.getId();
		Long userId = user.getId();

		URI uri = new URI(baseUrl + port + "/api/logging/deletelog/" + userId +"/"+logId);
		ResponseEntity<Log> result = restTemplate.withBasicAuth("admin", "goodpassword").exchange(uri, HttpMethod.DELETE, null, Log.class);

		assertEquals(200, result.getStatusCode().value());
	}

	@Test
	public void deleteLog_invalidLogId_Failure() throws Exception {

		//creating role for authentication
		Set<Role> roles = new HashSet<Role>();
		Role userRole = roleRepository.save(new Role(1,ERole.ROLE_USER));
		Role adminRole = roleRepository.save(new Role(2,ERole.ROLE_ADMIN));
		roles.add(userRole);
		roles.add(adminRole);
		User user = new User("admin", "admin@gmail.com", encoder.encode("goodpassword"), roles);
		userRepo.save(user);

		Long userId = user.getId();

		URI uri = new URI(baseUrl + port + "/api/logging/deletelog/" + userId +"/0");
		ResponseEntity<Log> result = restTemplate.withBasicAuth("admin", "goodpassword").exchange(uri, HttpMethod.DELETE, null, Log.class);

		assertEquals(404, result.getStatusCode().value());
	}

	@Test
	public void getuserCo2Sum_validUserId_Success() throws Exception {

		//creating role for authentication
		Set<Role> roles = new HashSet<Role>();
		Role userRole = roleRepository.save(new Role(1,ERole.ROLE_USER));
		Role adminRole = roleRepository.save(new Role(2,ERole.ROLE_ADMIN));
		roles.add(userRole);
		roles.add(adminRole);
		User user = new User("admin", "admin@gmail.com", encoder.encode("goodpassword"), roles);
		userRepo.save(user);

		Long userId = user.getId();

		URI uri = new URI(baseUrl + port + "/api/logging/co2sum/" + userId);
		ResponseEntity<Double> result = restTemplate.withBasicAuth("admin", "goodpassword").exchange(uri, HttpMethod.GET, null, Double.class);

		assertEquals(200, result.getStatusCode().value());
	}

	@Test
	public void gettotalCo2Sum_validUser_Success() throws Exception {

		//creating role for authentication
		Set<Role> roles = new HashSet<Role>();
		Role userRole = roleRepository.save(new Role(1,ERole.ROLE_USER));
		Role adminRole = roleRepository.save(new Role(2,ERole.ROLE_ADMIN));
		roles.add(userRole);
		roles.add(adminRole);
		User user = new User("admin", "admin@gmail.com", encoder.encode("goodpassword"), roles);
		userRepo.save(user);

		URI uri = new URI(baseUrl + port + "/api/logging/co2sum");
		ResponseEntity<Double> result = restTemplate.withBasicAuth("admin", "goodpassword").exchange(uri, HttpMethod.GET, null, Double.class);

		assertEquals(200, result.getStatusCode().value());
	}

	
}

