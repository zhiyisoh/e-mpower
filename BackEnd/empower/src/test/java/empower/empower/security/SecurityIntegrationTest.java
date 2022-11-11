package empower.empower.security;

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

import java.util.HashSet;

import empower.empower.springjwt.models.*;
import empower.empower.springjwt.payloads.request.LoginRequest;
import empower.empower.springjwt.payloads.request.SignUpRequest;
import empower.empower.springjwt.repository.*;

/** Start an actual HTTP server listening at a random port*/
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class SecurityIntegrationTest {

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
    private UserRepository userRepo;

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
		userRepo.deleteAll();
		roleRepository.deleteAll();
	}

/**
 * TEST 1: addUser_validSignupRequest_Success()
 * Tests the registration of new user
 * @throws Exception
 */
	@Test
	public void addUser_validSignupRequest_Success() throws Exception {

		Role userRole = roleRepository.save(new Role(1,ERole.ROLE_USER));										//add new roles
		Role adminRole = roleRepository.save(new Role(2,ERole.ROLE_ADMIN));

		SignUpRequest user = new SignUpRequest();																//create a sign up request
		user.setEmail("admin@gmail.com");
		user.setPassword("goodpassword");
		Set<String> roles = new HashSet<String>();
		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");
		user.setRole(roles);
		user.setUsername("admin");

		URI uri = new URI(baseUrl + port + "/api/auth/signup");													//URI reference
		ResponseEntity<SignUpRequest> result = restTemplate																//get response entity
									.exchange(uri, HttpMethod.POST, new HttpEntity<>(user), SignUpRequest.class);

		assertEquals(200, result.getStatusCode().value());														//ensure that the status code returned is 200 ok
	}

/**
 * TEST 2: addUser_existingUsername_Failure()
 * Tests the registration of new user. If username already exists, it should return error code 400 bad request.
 * @throws Exception
 */
	@Test
	public void addUser_existingUsername_Failure() throws Exception {

		Set<Role> roles = new HashSet<Role>();
		Role userRole = roleRepository.save(new Role(1,ERole.ROLE_USER));										//add new roles
		Role adminRole = roleRepository.save(new Role(2,ERole.ROLE_ADMIN));
		roles.add(userRole);
		roles.add(adminRole);
		User existingUser = new User("admin", "admin@gmail.com", encoder.encode("goodpassword"), roles);		//create an existing user
		userRepo.save(existingUser);

		SignUpRequest user = new SignUpRequest();																//create a sign up request with same username
		user.setEmail("admin1@gmail.com");
		user.setPassword("goodpassword");
		Set<String> role = new HashSet<String>();
		role.add("ROLE_ADMIN");
		role.add("ROLE_USER");
		user.setRole(role);
		user.setUsername("admin");

		URI uri = new URI(baseUrl + port + "/api/auth/signup");													//URI reference
		ResponseEntity<User> result = restTemplate																//get response entity
									.exchange(uri, HttpMethod.POST, new HttpEntity<>(user), User.class);

		assertEquals(400, result.getStatusCode().value());														//ensure that the status code returned is 400
	}

/**
 * TEST 3: addUser_existingEmail_Failure()
 * Tests the registration of new user. If email already exists, it should return error code 400 bad request.
 * @throws Exception
 */
	@Test
	public void addUser_existingEmail_Failure() throws Exception {

		Set<Role> roles = new HashSet<Role>();
		Role userRole = roleRepository.save(new Role(1,ERole.ROLE_USER));										//add new roles
		Role adminRole = roleRepository.save(new Role(2,ERole.ROLE_ADMIN));
		roles.add(userRole);
		roles.add(adminRole);
		User existingUser = new User("admin1", "admin@gmail.com", encoder.encode("goodpassword"), roles);		//create an existing user
		userRepo.save(existingUser);

		SignUpRequest user = new SignUpRequest();																//create a sign up request with same email
		user.setEmail("admin@gmail.com");
		user.setPassword("goodpassword");
		Set<String> role = new HashSet<String>();
		role.add("ROLE_ADMIN");
		role.add("ROLE_USER");
		user.setRole(role);
		user.setUsername("admin");

		URI uri = new URI(baseUrl + port + "/api/auth/signup");													//URI reference
		ResponseEntity<User> result = restTemplate																//get response entity
									.exchange(uri, HttpMethod.POST, new HttpEntity<>(user), User.class);

		assertEquals(400, result.getStatusCode().value());														//ensure that the status code returned is 400
	}

/**
 * TEST 4: getUserDetails_validUserId_Success() 
 * Tests the retreival of user details. If user id is valid, then status code should return 200.
 * @throws Exception
 */
	@Test
	public void getUserDetails_validUserId_Success() throws Exception {

		//create existing user
		Set<Role> roles = new HashSet<Role>();
		Role userRole = roleRepository.save(new Role(1,ERole.ROLE_USER));										//add new roles
		Role adminRole = roleRepository.save(new Role(2,ERole.ROLE_ADMIN));
		roles.add(userRole);
		roles.add(adminRole);
		User existingUser = new User("admin1", "admin@gmail.com", encoder.encode("goodpassword"), roles);
		userRepo.save(existingUser);

		URI uri = new URI(baseUrl + port + "/api/auth/profile/" + existingUser.getId());
		ResponseEntity<User> result = restTemplate.exchange(uri, HttpMethod.GET, null, User.class);

		assertEquals(200, result.getStatusCode().value());														//ensure that the status code returned is 200
	}

/**
 * TEST 5: getUserDetails_invalidUserId_Failure()
 * Tests the retreival of user details. If user id is invalid, then status code should return 404 (UserNotFoundException).
 * @throws Exception
 */
	@Test
	public void getUserDetails_invalidUserId_Failure() throws Exception {

		URI uri = new URI(baseUrl + port + "/api/auth/profile/0");
		ResponseEntity<User> result = restTemplate.exchange(uri, HttpMethod.GET, null, User.class);

		assertEquals(404, result.getStatusCode().value());														//ensure that the status code returned is 404
	}


/**
 * TEST 6: updateProfile_validUserId_Success()
 * Tests the update of user details. If user id is valid, then status code should return 200.
 * @throws Exception
 */
	@Test
	public void updateProfile_validUserId_Success() throws Exception {

		//create existing user
		Set<Role> roles = new HashSet<Role>();
		Role userRole = roleRepository.save(new Role(1,ERole.ROLE_USER));										//add new roles
		Role adminRole = roleRepository.save(new Role(2,ERole.ROLE_ADMIN));
		roles.add(userRole);
		roles.add(adminRole);
		User existingUser = new User("adminNameChange", "admin@gmail.com", encoder.encode("goodpassword"), roles);
		userRepo.save(existingUser);

		SignUpRequest newDetails = new SignUpRequest();															//create new updated user details
		newDetails.setEmail("admin@gmail.com");
		newDetails.setPassword("password");
		newDetails.setUsername("admin");
		Set<String> role = new HashSet<String>();
		role.add("ROLE_ADMIN");
		role.add("ROLE_USER");
		newDetails.setRole(role);

		URI uri = new URI(baseUrl + port + "/api/auth/editprofile/" + existingUser.getId());;					//URI Reference
		ResponseEntity<User> result = restTemplate																//get response entity
									.exchange(uri, HttpMethod.PUT, new HttpEntity<>(newDetails), User.class);

		assertEquals(200, result.getStatusCode().value());														//ensure that the status code returned is 200
	}


/**
 * TEST 7: updateProfile_invalidUserId_Failure()
 * Tests the update of user details. If user id is invalid, then status code should return 404 (UserNotFoundException).
 * @throws Exception
 */
	@Test
	public void updateProfile_invalidUserId_Failure() throws Exception {

		SignUpRequest newDetails = new SignUpRequest();															//create new updated user details
		newDetails.setEmail("admin@gmail.com");
		newDetails.setPassword("password");
		newDetails.setUsername("admin");
		Set<String> role = new HashSet<String>();
		role.add("ROLE_ADMIN");
		role.add("ROLE_USER");
		newDetails.setRole(role);

		URI uri = new URI(baseUrl + port + "/api/auth/editprofile/0");											//URI Reference with invalid user id 0
		ResponseEntity<User> result = restTemplate																//get response entity
									.exchange(uri, HttpMethod.PUT, new HttpEntity<>(newDetails), User.class);

		assertEquals(500, result.getStatusCode().value());														//ensure that the status code returned is 500
	}


/**
 * TEST 8: updateProfile_emailAlreadyExists_Failure()
 * Tests the update of user details. If email already exists, then status code should return 400.
 * @throws Exception
 */
	@Test
	public void updateProfile_emailAlreadyExists_Failure() throws Exception {

		Set<Role> roles = new HashSet<Role>();
		Role userRole = roleRepository.save(new Role(1,ERole.ROLE_USER));										//add new roles
		Role adminRole = roleRepository.save(new Role(2,ERole.ROLE_ADMIN));
		roles.add(userRole);
		roles.add(adminRole);
		User existingUser = new User("adminNameChange", "admin@gmail.com", encoder.encode("goodpassword"), roles);//create existing user with email "admin@gmail.com"
		userRepo.save(existingUser);
		User sameEmailUser = new User("admin", "adminChange@gmail.com", encoder.encode("goodpassword"), roles);	//create user (want to change email to "admin@gmail.com")
		userRepo.save(sameEmailUser);

		SignUpRequest newDetails = new SignUpRequest();															//create new updated user details
		newDetails.setEmail("admin@gmail.com");
		newDetails.setPassword("goodpassword");
		newDetails.setUsername("admin");
		Set<String> role = new HashSet<String>();
		role.add("ROLE_ADMIN");
		role.add("ROLE_USER");
		newDetails.setRole(role);

		URI uri = new URI(baseUrl + port + "/api/auth/editprofile/" + existingUser.getId());					//URI Reference
		ResponseEntity<User> result = restTemplate																//get response entity
										.exchange(uri, HttpMethod.PUT, new HttpEntity<>(newDetails), User.class);

		assertEquals(400, result.getStatusCode().value());														//ensure that the status code returned is 400
	}


/**
 * TEST 9: updateProfile_usernameAlreadyExists_Failure()
 * Tests the update of user details. If username already exists, then status code should return 400.
 * @throws Exception
 */
	@Test
	public void updateProfile_usernameAlreadyExists_Failure() throws Exception {

		Set<Role> roles = new HashSet<Role>();
		Role userRole = roleRepository.save(new Role(1,ERole.ROLE_USER));										//add new roles
		Role adminRole = roleRepository.save(new Role(2,ERole.ROLE_ADMIN));
		roles.add(userRole);
		roles.add(adminRole);
		User existingUser = new User("adminNameChange", "admin@gmail.com", encoder.encode("goodpassword"), roles); //create existing user (want to change name to "admin")
		userRepo.save(existingUser);
		User sameEmailUser = new User("admin", "anotheradmin@gmail.com", encoder.encode("goodpassword"), roles);   //create another user with username "admin"
		userRepo.save(sameEmailUser);
		
		SignUpRequest newDetails = new SignUpRequest();															//create new updated user details
		newDetails.setEmail("admin@gmail.com");
		newDetails.setPassword("goodpassword");
		newDetails.setUsername("admin");
		Set<String> role = new HashSet<String>();
		role.add("ROLE_ADMIN");
		role.add("ROLE_USER");
		newDetails.setRole(role);

		URI uri = new URI(baseUrl + port + "/api/auth/editprofile/" + existingUser.getId());					//URI Reference
		ResponseEntity<User> result = restTemplate																//get response entity
										.exchange(uri, HttpMethod.PUT, new HttpEntity<>(newDetails), User.class);

		assertEquals(400, result.getStatusCode().value());														//ensure that the status code returned is 400
	}

/**
 * TEST 10: authenticateUser_validLoginRequest_Success()
 * Tests the login of user. If loginRequest is valid, then status code should return 200.
 * @throws Exception
 */
	@Test
	public void authenticateUser_validLoginRequest_Success() throws Exception {


		Set<Role> roles = new HashSet<Role>();
		Role userRole = roleRepository.save(new Role(1,ERole.ROLE_USER));										//add new roles
		Role adminRole = roleRepository.save(new Role(2,ERole.ROLE_ADMIN));
		roles.add(userRole);
		roles.add(adminRole);
		User existingUser = new User("admin", "admin@gmail.com", encoder.encode("goodpassword"), roles);//create existing user 
		userRepo.save(existingUser);

		LoginRequest user = new LoginRequest();																	//create new log in request
		user.setPassword("goodpassword");
		user.setUsername("admin");

		URI uri = new URI(baseUrl + port + "/api/auth/signin");													//URI Reference
		ResponseEntity<LoginRequest> result = restTemplate														//get response entity
												.exchange(uri, HttpMethod.POST, new HttpEntity<>(user), LoginRequest.class);

		assertEquals(200, result.getStatusCode().value());														//ensure that the status code returned is 200
	}

}
