package empower.empower.log;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;
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

	/**
	 * @AfterEach:
	 * used to signal that the annotated method should be executed after each @Test method in the current test class.
	 */
	@AfterEach
	void tearDown(){
		logRepo.deleteAll();
        binRepo.deleteAll();
		userRepo.deleteAll();
		emRepo.deleteAll();
		roleRepository.deleteAll();
	}

	/**
	 * TEST 1: addLog_validUserId_Success()
	 * Tests the addition of log via post method. It checks that with a valid UserId, the log can be added successfully.
	 * If test is successful, it should return the HTTP code 200.
	 * @throws Exception
	 */
	@Test
	public void addLog_validUserId_Success() throws Exception {

		
		Set<Role> roles = new HashSet<Role>();																		//Create a new user for authentication
		Role userRole = roleRepository.save(new Role(1,ERole.ROLE_USER));
		Role adminRole = roleRepository.save(new Role(2,ERole.ROLE_ADMIN));
		roles.add(userRole);
		roles.add(adminRole);
		User user = new User("admin", "admin@gmail.com", encoder.encode("goodpassword"), roles);
		userRepo.save(user);
		Long userId = user.getId();

		Emissions em = emRepo.save(new Emissions("AA", 0.02));														//create new Log 
		Calendar myCalendar = new GregorianCalendar(2014, 2, 11);
        Date currDate = new Date(myCalendar.getTimeInMillis());
        Log saveLog = new Log("AA", "Stuff", "Bulb", currDate, user, em);

		URI uri = new URI(baseUrl + port + "/api/logging/addlog/" + userId);										// URI Reference
		ResponseEntity<Log> result = restTemplate.withBasicAuth("admin", "goodpassword")							//get response entity
									.exchange(uri, HttpMethod.POST, new HttpEntity<>(saveLog), Log.class); 

		
		assertEquals(200, result.getStatusCode().value());															//Ensure that the status code returned is 200
	}

	/**
	 * TEST 2: addLog_invalidUserId_Failure()
	 * Tests the addition of log via post method. It checks that with an invalid UserId, the log cannot be added and an error is thrown (UserNotFoundException).
	 * If test is successful, it should return the HTTP code 404, as the User cannot be found.
	 * @throws Exception
	 */
	@Test
	public void addLog_invalidUserId_Failure() throws Exception {

		Set<Role> roles = new HashSet<Role>();																		//Create a new user for authentication
		Role userRole = roleRepository.save(new Role(1,ERole.ROLE_USER));
		Role adminRole = roleRepository.save(new Role(2,ERole.ROLE_ADMIN));
		roles.add(userRole);
		roles.add(adminRole);
		User user = new User("admin", "admin@gmail.com", encoder.encode("goodpassword"), roles);
		userRepo.save(user);

		Emissions em = emRepo.save(new Emissions("AA", 0.02));														//create new Log 
		Calendar myCalendar = new GregorianCalendar(2014, 2, 11);
        Date currDate = new Date(myCalendar.getTimeInMillis());
        Log saveLog = new Log("AA", "Stuff", "Bulb", currDate, user, em);

		
		URI uri = new URI(baseUrl + port + "/api/logging/addlog/0");												//URI reference with an invalid user id of 0
		ResponseEntity<Log> result = restTemplate.withBasicAuth("admin", "goodpassword")							//get response entity
									.exchange(uri, HttpMethod.POST, new HttpEntity<>(saveLog), Log.class);

		
		assertEquals(404, result.getStatusCode().value());															//Ensure that the status code returned is 404
	}


	/**
	 * TEST 3: updateLog_validLogIdAndUserId_Success()
	 * Tests update of log via put method. It checks that with a valid UserId and logId, the log is updated.
	 * If test is successful, it should return the HTTP code 200.
	 * @throws Exception
	 */
	@Test
	public void updateLog_validLogIdAndUserId_Success() throws Exception {

		Set<Role> roles = new HashSet<Role>();																		//Create a new user for authentication
		Role userRole = roleRepository.save(new Role(1,ERole.ROLE_USER));
		Role adminRole = roleRepository.save(new Role(2,ERole.ROLE_ADMIN));
		roles.add(userRole);
		roles.add(adminRole);
		User user = new User("admin", "admin@gmail.com", encoder.encode("goodpassword"), roles);
		userRepo.save(user);
		Long userId = user.getId();

		Emissions em = emRepo.save(new Emissions("AA", 0.02));														//create new Log 
		Calendar myCalendar = new GregorianCalendar(2014, 2, 11);
		Date currDate = new Date(myCalendar.getTimeInMillis());
		Log savedLog = logRepo.save(new Log("AA", "Stuff", "Bulb", currDate, user, em));
		Long logId = savedLog.getId();

		Log newLogInfo = new Log("AA", "I am changed", "Bulb", currDate, user, em);									//create updated Log Information

		URI uri = new URI(baseUrl + port + "/api/logging/updatelog/" + userId +"/"+ logId);							//URI Reference
		ResponseEntity<Log> result = restTemplate.withBasicAuth("admin", "goodpassword")							//get response entity
									.exchange(uri, HttpMethod.PUT, new HttpEntity<>(newLogInfo), Log.class);

		assertEquals(200, result.getStatusCode().value());															//Ensure that the status code returned is 200
	}


	/**
	 * TEST 4: updateLog_invalidLogId_Failure()
	 * Tests update of log via put method. It checks that with an invalid logId, the log is not updated as log cannot be found (LogNotFoundException).
	 * If test is successful, it should return the HTTP code 404.
	 * @throws Exception
	 */
	@Test
	public void updateLog_invalidLogId_Failure() throws Exception {

		Set<Role> roles = new HashSet<Role>();																		//Create a new user for authentication
		Role userRole = roleRepository.save(new Role(1,ERole.ROLE_USER));
		Role adminRole = roleRepository.save(new Role(2,ERole.ROLE_ADMIN));
		roles.add(userRole);
		roles.add(adminRole);
		User user = new User("admin", "admin@gmail.com", encoder.encode("goodpassword"), roles);
		userRepo.save(user);
		Long userId = user.getId();

		Emissions em = emRepo.save(new Emissions("AA", 0.02));														//create updated Log Information
		Calendar myCalendar = new GregorianCalendar(2014, 2, 11);
		Date currDate = new Date(myCalendar.getTimeInMillis());
		Log newLogInfo = new Log("AA", "I am changed", "Bulb", currDate, user, em);

		URI uri = new URI(baseUrl + port + "/api/logging/updatelog/" + userId +"/0");								//URI Reference with invalid log id 0
		ResponseEntity<Log> result = restTemplate.withBasicAuth("admin", "goodpassword")							//get response entity
									.exchange(uri, HttpMethod.PUT, new HttpEntity<>(newLogInfo), Log.class);

		assertEquals(404, result.getStatusCode().value());															//Ensure that the status code returned is 404
	}

	/**
	 * TEST 5: updateLog_invalidUserId_Failure()
	 * Tests update of log via put method. It checks that with an invalid userId, the log is not updated as user cannot be found (UserNotFoundException).
	 * If test is successful, it should return the HTTP code 404.
	 * @throws Exception
	 */
	@Test
	public void updateLog_invalidUserId_Failure() throws Exception {

		Set<Role> roles = new HashSet<Role>();																		//Create a new user for authentication
		Role userRole = roleRepository.save(new Role(1,ERole.ROLE_USER));
		Role adminRole = roleRepository.save(new Role(2,ERole.ROLE_ADMIN));
		roles.add(userRole);
		roles.add(adminRole);
		User user = new User("admin", "admin@gmail.com", encoder.encode("goodpassword"), roles);
		userRepo.save(user);

		Emissions em = emRepo.save(new Emissions("AA", 0.02));														//create new Log 
		Calendar myCalendar = new GregorianCalendar(2014, 2, 11);
		Date currDate = new Date(myCalendar.getTimeInMillis());
		Log savedLog = logRepo.save(new Log("AA", "Stuff", "Bulb", currDate, user, em));
		Long logId = savedLog.getId();

		Log newLogInfo = new Log("AA", "I am changed", "Bulb", currDate, user, em);									//create updated Log Information

		URI uri = new URI(baseUrl + port + "/api/logging/updatelog/0/" + logId);									//URI Reference with invalid user id 0
		ResponseEntity<Log> result = restTemplate.withBasicAuth("admin", "goodpassword")							//get response entity
									.exchange(uri, HttpMethod.PUT, new HttpEntity<>(newLogInfo), Log.class);

		assertEquals(404, result.getStatusCode().value());															//Ensure that the status code returned is 404
	}


	/**
	 * TEST 6: getLog_InvalidLogId_Failure()
	 * Tests the retreival of log via get method. It checks that with an invalid logId, the log is not updated as log cannot be found (LogNotFoundException).
	 * If test is successful, it should return the HTTP code 404.
	 * @throws Exception
	 */
	@Test
	public void getLog_InvalidLogId_Failure() throws Exception {

		Set<Role> roles = new HashSet<Role>();																		//Create a new user for authentication
		Role userRole = roleRepository.save(new Role(1,ERole.ROLE_USER));
		Role adminRole = roleRepository.save(new Role(2,ERole.ROLE_ADMIN));
		roles.add(userRole);
		roles.add(adminRole);
		userRepo.save(new User("admin", "admin@gmail.com", encoder.encode("goodpassword"), roles));

		URI uri = new URI(baseUrl + port + "/api/logging/0");														//URI Reference with invalid log id 0
		ResponseEntity<Log> result = restTemplate.withBasicAuth("admin", "goodpassword")							//get response entity
									.exchange(uri, HttpMethod.GET, null, Log.class);

		assertEquals(404, result.getStatusCode().value());															//Ensure that the status code returned is 404
	}

	/**
	 * TEST 7: getLog_validLogId_Success() 
	 * Tests the retreival of log via get method. It checks that with an valid logId, the log is returned.
	 * If test is successful, it should return the HTTP code 200.
	 * @throws Exception
	 */
	@Test
	public void getLog_validLogId_Success() throws Exception {

		Set<Role> roles = new HashSet<Role>();																			//Create a new user for authentication
		Role userRole = roleRepository.save(new Role(1,ERole.ROLE_USER));
		Role adminRole = roleRepository.save(new Role(2,ERole.ROLE_ADMIN));
		roles.add(userRole);
		roles.add(adminRole);
		User user = new User("admin", "admin@gmail.com", encoder.encode("goodpassword"), roles);
		userRepo.save(user);

		Emissions em = emRepo.save(new Emissions("AA", 0.02));															//create new log 
		Calendar myCalendar = new GregorianCalendar(2014, 2, 11);
        Date currDate = new Date(myCalendar.getTimeInMillis());
        Log savedLog = logRepo.save(new Log("AA", "Stuff", "Bulb", currDate, user, em));
		Long logId = savedLog.getId();

		URI uri = new URI(baseUrl + port + "/api/logging/" + logId);													//URI Reference with valid log id
		ResponseEntity<Log> result = restTemplate.withBasicAuth("admin", "goodpassword")								//get response entity
									.exchange(uri, HttpMethod.GET, null, Log.class);

		assertEquals(200, result.getStatusCode().value());																//Ensure that the status code returned is 200
	}


	/**
	 * TEST 8: deleteLog_validLogId_Success() 
	 * Tests the deletion of log via delete method. It checks that with an valid logId, the log is deleted.
	 * If test is successful, it should return the HTTP code 200.
	 * @throws Exception
	 */
	@Test
	public void deleteLog_validLogId_Success() throws Exception {

		Set<Role> roles = new HashSet<Role>();																			//Create a new user for authentication
		Role userRole = roleRepository.save(new Role(1,ERole.ROLE_USER));
		Role adminRole = roleRepository.save(new Role(2,ERole.ROLE_ADMIN));
		roles.add(userRole);
		roles.add(adminRole);
		User user = new User("admin", "admin@gmail.com", encoder.encode("goodpassword"), roles);
		userRepo.save(user);
		Long userId = user.getId();

		Emissions em = emRepo.save(new Emissions("AA", 0.02));															//create new log 
		Calendar myCalendar = new GregorianCalendar(2014, 2, 11);
        Date currDate = new Date(myCalendar.getTimeInMillis());
        Log savedLog = logRepo.save(new Log("AA", "Stuff", "Bulb", currDate, user, em));
		Long logId = savedLog.getId();
		

		URI uri = new URI(baseUrl + port + "/api/logging/deletelog/" + userId +"/"+logId);								//URI Reference with valid log id
		ResponseEntity<Log> result = restTemplate.withBasicAuth("admin", "goodpassword")								//get response entity
									.exchange(uri, HttpMethod.DELETE, null, Log.class);

		assertEquals(200, result.getStatusCode().value());																//Ensure that the status code returned is 200
	}

	/**
	 * TEST 9: deleteLog_invalidLogId_Failure()
	 * Tests the deletion of log via delete method. It checks that with an invalid logId, the log is not deleted and an error is thrown (LogNotFoundException).
	 * If test is successful, it should return the HTTP code 404.
	 * @throws Exception
	 */
	@Test
	public void deleteLog_invalidLogId_Failure() throws Exception {

		Set<Role> roles = new HashSet<Role>();																			//Create a new user for authentication
		Role userRole = roleRepository.save(new Role(1,ERole.ROLE_USER));
		Role adminRole = roleRepository.save(new Role(2,ERole.ROLE_ADMIN));
		roles.add(userRole);
		roles.add(adminRole);
		User user = new User("admin", "admin@gmail.com", encoder.encode("goodpassword"), roles);
		userRepo.save(user);
		Long userId = user.getId();

		URI uri = new URI(baseUrl + port + "/api/logging/deletelog/" + userId +"/0");									//URI Reference with invalid log id 0
		ResponseEntity<Log> result = restTemplate.withBasicAuth("admin", "goodpassword")								//get response entity
		.exchange(uri, HttpMethod.DELETE, null, Log.class);

		assertEquals(404, result.getStatusCode().value());																//Ensure that the status code returned is 404
	}


	/**
	 * TEST 10: getuserCo2Sum_validUserId_Success() 
	 * Tests retreival of user log emissions via get method. It checks that with a valid userId, emission value is returned.
	 * If test is successful, it should return the HTTP code 200.
	 * @throws Exception
	 */
	@Test
	public void getuserCo2Sum_validUserId_Success() throws Exception {

		Set<Role> roles = new HashSet<Role>();																			//Create a new user for authentication
		Role userRole = roleRepository.save(new Role(1,ERole.ROLE_USER));
		Role adminRole = roleRepository.save(new Role(2,ERole.ROLE_ADMIN));
		roles.add(userRole);
		roles.add(adminRole);
		User user = new User("admin", "admin@gmail.com", encoder.encode("goodpassword"), roles);
		userRepo.save(user);
		Long userId = user.getId();

		URI uri = new URI(baseUrl + port + "/api/logging/co2sum/" + userId);											//URI Reference with valid user id
		ResponseEntity<Double> result = restTemplate.withBasicAuth("admin", "goodpassword")								//get response entity
										.exchange(uri, HttpMethod.GET, null, Double.class);

		assertEquals(200, result.getStatusCode().value());																//Ensure that the status code returned is 200
	}

	/**
	 * TEST 11: gettotalCo2Sum_validUser_Success()
	 * Tests retreival of total log emissions via get method. It checks that with a valid user, emission value is returned.
	 * If test is successful, it should return the HTTP code 200.
	 * @throws Exception
	 */
	@Test
	public void gettotalCo2Sum_validUser_Success() throws Exception {

		Set<Role> roles = new HashSet<Role>();																			//Create a new user for authentication
		Role userRole = roleRepository.save(new Role(1,ERole.ROLE_USER));
		Role adminRole = roleRepository.save(new Role(2,ERole.ROLE_ADMIN));
		roles.add(userRole);
		roles.add(adminRole);
		User user = new User("admin", "admin@gmail.com", encoder.encode("goodpassword"), roles);
		userRepo.save(user);

		URI uri = new URI(baseUrl + port + "/api/logging/co2sum");														//URI Reference with valid user id
		ResponseEntity<Double> result = restTemplate.withBasicAuth("admin", "goodpassword")								//get response entity
										.exchange(uri, HttpMethod.GET, null, Double.class);

		assertEquals(200, result.getStatusCode().value());																//Ensure that the status code returned is 200
	}

}

