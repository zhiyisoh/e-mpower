package empower.empower.bin;

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
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// import java.util.Calendar;
// import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;

import empower.empower.bin.entity.*;
import empower.empower.bin.repository.*;
import empower.empower.bin.requests.Coordinate;
import empower.empower.springjwt.models.*;
import empower.empower.springjwt.repository.*;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class BinIntegrationTest {
    
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
	private RoleRepository roleRepository;
  
	@Autowired
	private UserRepository userRepo;
  
	@Autowired
	private BinRepository binRepo;

	@Autowired
	private BCryptPasswordEncoder encoder;
  
	  @AfterEach
		void tearDown(){
		  binRepo.deleteAll();
	  	  userRepo.deleteAll();
	  	  roleRepository.deleteAll();
	}

    @Test
    public void addbin_Success() throws Exception {
        //create new bin ---  public Bin(int postalCode, String address, boolean ict, boolean battery, boolean lamp, double latitude, double longitude)
        Bin addedBin = new Bin(219468, "109 Dorset Rd", true, false, true, 100.0, 250.55);

        URI uri = new URI(baseUrl + port + "/api/bins/addBin/");										// URI Reference
        ResponseEntity<Bin> result = restTemplate
                                    .exchange(uri, HttpMethod.POST, new HttpEntity<>(addedBin), Bin.class); 

		
		assertEquals(201, result.getStatusCode().value());															//Ensure that the status code returned is 200
    }

	@Test
	public void addBin_Failure() throws Exception {
		Bin addedBin = null;
		
		URI uri = new URI(baseUrl + port + "/api/bins/addBin");
		ResponseEntity<Bin> result = restTemplate
                                    .exchange(uri, HttpMethod.POST, new HttpEntity<>(addedBin), Bin.class); 

		assertEquals(415, result.getStatusCode().value());
	}


	@Test 
  	public void getBinPostal_Success() throws Exception {
        Bin savedBin = binRepo.save(new Bin(219468, "109 Dorset Rd", true, false, true, 100.0, 250.55));
		//Long binId = savedBin.getId();
		int postalCode = savedBin.getPostalCode();

		URI uri = new URI(baseUrl + port + "/api/bins/getBin/" + postalCode);
		ResponseEntity<List<Bin>> result = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<Bin>>() {
		});
    

    	assertEquals(200, result.getStatusCode().value());
  }


    @Test 
	public void getBin_ValidBinID_Success() throws Exception{
        Bin savedBin = binRepo.save(new Bin(219468, "109 Dorset Rd", true, false, true, 100.0, 250.55));
    	Long binId = savedBin.getId();

        URI uri = new URI(baseUrl + port + "/api/bins/" + binId);
    	ResponseEntity<Bin> result = restTemplate.exchange(uri, HttpMethod.GET, null, Bin.class);

        assertEquals(200, result.getStatusCode().value());
    }


	@Test 
    public void getBin_invalidBinID_Failure() throws Exception{

        URI uri = new URI(baseUrl + port + "/api/bins/0");
    	ResponseEntity<Bin> result = restTemplate.exchange(uri, HttpMethod.GET, null, Bin.class);

        assertEquals(404, result.getStatusCode().value());
    }


	@Test 
    public void findNearestBin_Success() throws Exception{
    
		Set<Role> roles = new HashSet<Role>();                                    //Create a new user for authentication
		Role userRole = roleRepository.save(new Role(1,ERole.ROLE_USER));
		Role adminRole = roleRepository.save(new Role(2,ERole.ROLE_ADMIN));
		roles.add(userRole);
		roles.add(adminRole);
		User user = new User("admin", "admin@gmail.com", encoder.encode("goodpassword"), roles);
		userRepo.save(user);

		Bin savedBin = binRepo.save(new Bin(219468, "109 Dorset Rd", true, false, true, 100.0, 250.55));

        Coordinate currCoordinate = new Coordinate(100.02, 250.59, "ICT");

        URI uri = new URI(baseUrl + port + "/api/bins/findNearestBin");
    	ResponseEntity<Long> result = restTemplate.withBasicAuth("admin", "goodpassword")
            .exchange(uri, HttpMethod.POST, new HttpEntity<>(currCoordinate), Long.class);

        assertEquals(201, result.getStatusCode().value());
    }

}
