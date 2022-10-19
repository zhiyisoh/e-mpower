package empower.empower;

import java.io.FileReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import empower.empower.log.repository.EmissionRepository;
import empower.empower.log.entity.Emissions;
@SpringBootApplication
public class EmpowerApplication {
	public static void main(String[] args) {
		SpringApplication.run(EmpowerApplication.class, args);

		EmissionRepository emRepo = ctx.getBean(EmissionRepository.class);
		List<String[]> list = readAllDataAtOnce("./src/main/java/empower/empower/emissiondata.csv");

		for (String[] s : list) {

			emRepo.save(new Emissions());
		}
	}

	public static List<String[]> readAllDataAtOnce(String file) {
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
			// for (String cell : row) {
			// System.out.print(cell + "\t");
			// }
			// System.out.println();
			// }

			return allData;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
