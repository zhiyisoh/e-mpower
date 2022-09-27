package empower.empower;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class EmpowerApplication implements CommandLineRunner{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(EmpowerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		// to add values to tables!!
		// source: https://www.youtube.com/watch?v=pER-N4esW58
		/*
		 * String sql = "INSERT INTO users (fullname, email) VALUES (?, ?)"
		 * int result = jdbcTemplate.update(sql, "<name>", "<email>");
		 * 
		 * if(result > 0){
		 * sysout("A new row has been inserted.");
		 * }
		 */
	}

}
