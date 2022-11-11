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


	@AfterEach
	void tearDown(){
		userRepo.deleteAll();
		roleRepository.deleteAll();
	}

	// @Test
	// public void addUser_validSignupRequest_Success() throws Exception {
	// 	//creating role for authentication
	// 	Set<String> roles = new HashSet<String>();
	// 	roles.add("ROLE_ADMIN");
	// 	roles.add("ROLE_USER");

	// 	//create sign up request
	// 	SignUpRequest user = new SignUpRequest();
	// 	user.setEmail("admin@gmail.com");
	// 	user.setPassword("goodpassword");
	// 	user.setRole(roles);
	// 	user.setUsername("admin");

	// 	URI uri = new URI(baseUrl + port + "/api/auth/signup");
	// 	ResponseEntity<User> result = restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(user), User.class);

	// 	assertEquals(200, result.getStatusCode().value());
	// }

	@Test
	public void addUser_existingUsername_Failure() throws Exception {

		//create existing user
		Set<Role> roles = new HashSet<Role>();
		Role userRole = roleRepository.save(new Role(1,ERole.ROLE_USER));
		Role adminRole = roleRepository.save(new Role(2,ERole.ROLE_ADMIN));
		roles.add(userRole);
		roles.add(adminRole);
		User existingUser = new User("admin", "admin@gmail.com", encoder.encode("goodpassword"), roles);
		userRepo.save(existingUser);

		//creating role for authentication
		Set<String> role = new HashSet<String>();
		role.add("ROLE_ADMIN");
		role.add("ROLE_USER");

		//create sign up request
		SignUpRequest user = new SignUpRequest();
		user.setEmail("admin1@gmail.com");
		user.setPassword("goodpassword");
		user.setRole(role);
		user.setUsername("admin");

		URI uri = new URI(baseUrl + port + "/api/auth/signup");
		ResponseEntity<User> result = restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(user), User.class);

		assertEquals(400, result.getStatusCode().value());
	}

	@Test
	public void addUser_existingEmail_Failure() throws Exception {

		//create existing user
		Set<Role> roles = new HashSet<Role>();
		Role userRole = roleRepository.save(new Role(1,ERole.ROLE_USER));
		Role adminRole = roleRepository.save(new Role(2,ERole.ROLE_ADMIN));
		roles.add(userRole);
		roles.add(adminRole);
		User existingUser = new User("admin1", "admin@gmail.com", encoder.encode("goodpassword"), roles);
		userRepo.save(existingUser);

		//creating role for authentication
		Set<String> role = new HashSet<String>();
		role.add("ROLE_ADMIN");
		role.add("ROLE_USER");

		//create sign up request
		SignUpRequest user = new SignUpRequest();
		user.setEmail("admin@gmail.com");
		user.setPassword("goodpassword");
		user.setRole(role);
		user.setUsername("admin");

		URI uri = new URI(baseUrl + port + "/api/auth/signup");
		ResponseEntity<User> result = restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(user), User.class);

		assertEquals(400, result.getStatusCode().value());
	}

	// @Test
	// public void authenticateUser_validLoginRequest_Success() throws Exception {

	// 	//create existing user
	// 	Set<Role> roles = new HashSet<Role>();
	// 	Role userRole = roleRepository.save(new Role(1, ERole.ROLE_USER));
	// 	Role adminRole = roleRepository.save(new Role(2, ERole.ROLE_ADMIN));
	// 	roles.add(userRole);
	// 	roles.add(adminRole);
	// 	User existingUser = new User("admin", "admin@gmail.com", encoder.encode("goodpassword"), roles);
	// 	userRepo.save(existingUser);

	// 	//create sign up request
	// 	LoginRequest user = new LoginRequest();
	// 	user.setPassword("goodpassword");
	// 	user.setUsername("admin");

	// 	URI uri = new URI(baseUrl + port + "/api/auth/signin");
	// 	ResponseEntity<User> result = restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(user), User.class);

	// 	assertEquals(200, result.getStatusCode().value());
	// }

	@Test
	public void getUserDetails_validUserId_Success() throws Exception {

		//create existing user
		Set<Role> roles = new HashSet<Role>();
		Role userRole = roleRepository.save(new Role(1,ERole.ROLE_USER));
		Role adminRole = roleRepository.save(new Role(2,ERole.ROLE_ADMIN));
		roles.add(userRole);
		roles.add(adminRole);
		User existingUser = new User("admin1", "admin@gmail.com", encoder.encode("goodpassword"), roles);
		userRepo.save(existingUser);

		URI uri = new URI(baseUrl + port + "/api/auth/profile/" + existingUser.getId());
		ResponseEntity<User> result = restTemplate.exchange(uri, HttpMethod.GET, null, User.class);

		assertEquals(200, result.getStatusCode().value());
	}

	@Test
	public void getUserDetails_invalidUserId_Failure() throws Exception {

		URI uri = new URI(baseUrl + port + "/api/auth/profile/0");
		ResponseEntity<User> result = restTemplate.exchange(uri, HttpMethod.GET, null, User.class);

		assertEquals(404, result.getStatusCode().value());
	}

	@Test
	public void updateProfile_validUserId_Success() throws Exception {

		//create existing user
		Set<Role> roles = new HashSet<Role>();
		Role userRole = roleRepository.save(new Role(1,ERole.ROLE_USER));
		Role adminRole = roleRepository.save(new Role(2,ERole.ROLE_ADMIN));
		roles.add(userRole);
		roles.add(adminRole);
		User existingUser = new User("adminNameChange", "admin@gmail.com", encoder.encode("goodpassword"), roles);
		userRepo.save(existingUser);

		//creating role for authentication
		Set<String> role = new HashSet<String>();
		role.add("ROLE_ADMIN");
		role.add("ROLE_USER");

		//create new updated user details
		SignUpRequest newDetails = new SignUpRequest();
		newDetails.setEmail("admin@gmail.com");
		newDetails.setPassword("password");
		newDetails.setUsername("admin");
		newDetails.setRole(role);

		URI uri = new URI(baseUrl + port + "/api/auth/editprofile/" + existingUser.getId());
		ResponseEntity<User> result = restTemplate.exchange(uri, HttpMethod.PUT, new HttpEntity<>(newDetails), User.class);

		assertEquals(200, result.getStatusCode().value());
	}

	@Test
	public void updateProfile_invalidUserId_Failure() throws Exception {

		//creating role for authentication
		Set<String> role = new HashSet<String>();
		role.add("ROLE_ADMIN");
		role.add("ROLE_USER");

		//create new updated user details
		SignUpRequest newDetails = new SignUpRequest();
		newDetails.setEmail("admin@gmail.com");
		newDetails.setPassword("password");
		newDetails.setUsername("admin");
		newDetails.setRole(role);

		URI uri = new URI(baseUrl + port + "/api/auth/editprofile/0");
		ResponseEntity<User> result = restTemplate.exchange(uri, HttpMethod.PUT, new HttpEntity<>(newDetails), User.class);

		assertEquals(500, result.getStatusCode().value());
	}

	@Test
	public void updateProfile_emailAlreadyExists_Failure() throws Exception {

		//create existing user
		Set<Role> roles = new HashSet<Role>();
		Role userRole = roleRepository.save(new Role(1,ERole.ROLE_USER));
		Role adminRole = roleRepository.save(new Role(2,ERole.ROLE_ADMIN));
		roles.add(userRole);
		roles.add(adminRole);
		User existingUser = new User("adminNameChange", "admin@gmail.com", encoder.encode("goodpassword"), roles);
		userRepo.save(existingUser);
		User sameEmailUser = new User("admin", "adminChange@gmail.com", encoder.encode("goodpassword"), roles);
		userRepo.save(sameEmailUser);

		//creating role for authentication
		Set<String> role = new HashSet<String>();
		role.add("ROLE_ADMIN");
		role.add("ROLE_USER");

		//create new updated user details
		SignUpRequest newDetails = new SignUpRequest();
		newDetails.setEmail("admin@gmail.com");
		newDetails.setPassword("goodpassword");
		newDetails.setUsername("admin");
		newDetails.setRole(role);

		URI uri = new URI(baseUrl + port + "/api/auth/editprofile/" + existingUser.getId());
		ResponseEntity<User> result = restTemplate.exchange(uri, HttpMethod.PUT, new HttpEntity<>(newDetails), User.class);

		assertEquals(400, result.getStatusCode().value());
	}

	@Test
	public void updateProfile_usernameAlreadyExists_Failure() throws Exception {

		//create existing user
		Set<Role> roles = new HashSet<Role>();
		Role userRole = roleRepository.save(new Role(1,ERole.ROLE_USER));
		Role adminRole = roleRepository.save(new Role(2,ERole.ROLE_ADMIN));
		roles.add(userRole);
		roles.add(adminRole);
		User existingUser = new User("adminNameChange", "admin@gmail.com", encoder.encode("goodpassword"), roles);
		userRepo.save(existingUser);
		User sameEmailUser = new User("admin", "anotheradmin@gmail.com", encoder.encode("goodpassword"), roles);
		userRepo.save(sameEmailUser);

		//creating role for authentication
		Set<String> role = new HashSet<String>();
		role.add("ROLE_ADMIN");
		role.add("ROLE_USER");

		//create new updated user details
		SignUpRequest newDetails = new SignUpRequest();
		newDetails.setEmail("admin@gmail.com");
		newDetails.setPassword("goodpassword");
		newDetails.setUsername("admin");
		newDetails.setRole(role);

		URI uri = new URI(baseUrl + port + "/api/auth/editprofile/" + existingUser.getId());
		ResponseEntity<User> result = restTemplate.exchange(uri, HttpMethod.PUT, new HttpEntity<>(newDetails), User.class);

		assertEquals(400, result.getStatusCode().value());
	}

}
