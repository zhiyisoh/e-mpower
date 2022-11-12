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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// import java.util.Calendar;
// import java.util.GregorianCalendar;
import java.util.HashSet;
import java.sql.Date;

import empower.empower.bin.entity.*;
import empower.empower.bin.repository.*;
import empower.empower.bin.requests.Coordinate;
//import empower.empower.log.repository.*;
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
	private BinRepository binRepo;

    @AfterEach
	void tearDown(){
        binRepo.deleteAll();
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

	@Test //do later -- the responseType is incorrect
	public void getBinPostal_Success() throws Exception {
        Bin savedBin = binRepo.save(new Bin(219468, "109 Dorset Rd", true, false, true, 100.0, 250.55));
		//Long binId = savedBin.getId();
		int postalCode = savedBin.getPostalCode();

		URI uri = new URI(baseUrl + port + "/api/bins/getBin/" + postalCode);
		ResponseEntity<Bin> result = restTemplate.exchange(uri, HttpMethod.GET, null, Bin.class);
		

		assertEquals(200, result.getStatusCode().value());

	}

    @Test //do later -- same error
    public void getBinPostal_InvalidPostal_Failure() throws Exception{
        Bin savedBin = binRepo.save(new Bin(219468, "109 Dorset Rd", true, false, true, 100.0, 250.55));
		Long binId = savedBin.getId();
		//int postalCode = savedBin.getPostalCode();

        
    }

    @Test //same error --json
    public void getBin_ValidBinID_Success() throws Exception{
        Bin savedBin = binRepo.save(new Bin(219468, "109 Dorset Rd", true, false, true, 100.0, 250.55));
		Long binId = savedBin.getId();
		int postalCode = savedBin.getPostalCode();

        URI uri = new URI(baseUrl + port + "/api/bins/getBin/" + binId);
		ResponseEntity<Bin> result = restTemplate.exchange(uri, HttpMethod.GET, null, Bin.class);

        assertEquals(200, result.getStatusCode().value());

    }

    @Test //same error -- json
    public void findNearestBin_Success() throws Exception{

        //public Coordinate(double longitude, double latitude, String recycleType)
        Coordinate currCoordinate = new Coordinate(100.02, 250.59, "ICT");

        URI uri = new URI(baseUrl + port + "/api/bins/findNearestBin/");
		ResponseEntity<Long> result = restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(currCoordinate), Long.class);

        assertEquals(200, result.getStatusCode().value());
    }

}
