package empower.empower;

import java.io.FileReader;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import empower.empower.springjwt.models.Role;
import empower.empower.bin.entity.Bin;
import empower.empower.bin.repository.BinRepository;
import empower.empower.springjwt.models.ERole;
import empower.empower.springjwt.repository.RoleRepository;

@SpringBootApplication
public class EmpowerApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(EmpowerApplication.class, args);

		//user roles repository
		RoleRepository roleRepo = ctx.getBean(RoleRepository.class);
		System.out.println("[Add User Role]: " + roleRepo.save(new Role(1, ERole.ROLE_USER)).getName());
		System.out.println("[Add Admin Role]: " + roleRepo.save(new Role(2, ERole.ROLE_ADMIN)).getName());
		//System.out.println(System.getProperty("user.dir"));

		//bin data upload
		BinRepository binRepo = ctx.getBean(BinRepository.class);
		List<String[]> list = readAllDataAtOnce("./src/main/java/empower/empower/bindata.csv");

		for(String[] s : list){
			boolean isBattery = isItem(s[2]);
			boolean isIct = isItem(s[3]);
			boolean isLamp = isItem(s[4]);

			binRepo.save(new Bin(Long.parseLong(s[0]),Integer.parseInt(s[7]), s[1], isIct, isBattery, isLamp, Float.parseFloat(s[5]), Float.parseFloat(s[6])));
		}

	}
	public static boolean isItem(String s) {
		if(s.equals("1")){
			return true;
		}

		return false;
	}
	// Java code to illustrate reading a 
// all data at once 
public static List<String[]> readAllDataAtOnce(String file) 
{ 
	try { 
		// Create an object of file reader 
		// class with CSV file as a parameter. 
		FileReader filereader = new FileReader(file); 

		// create csvReader object and skip first Line 
		CSVReader csvReader = new CSVReaderBuilder(filereader) 
								.withSkipLines(1) 
								.build(); 
		List<String[]> allData = csvReader.readAll(); 

		// print Data 
		// for (String[] row : allData) { 
		// 	for (String cell : row) { 
		// 		System.out.print(cell + "\t"); 
		// 	} 
		// 	System.out.println(); 
		// } 

		return allData;
	} 
	catch (Exception e) { 
		e.printStackTrace(); 
	}
	return null; 

} 


}
