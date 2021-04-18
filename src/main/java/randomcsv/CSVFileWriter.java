package randomcsv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVFileWriter {
    Service service = new Service();

    public void writeToFile() {
        File file = new File("randomData.csv");
        if (file.length() == 0) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("randomData.csv"))) {
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("ID", "First Name", "Last Name", "Date", "Gender", "Race", "Phone Number", "Email"));

                for (int i = 0; i < 10; i++) {
                    Person person = service.createRandomData();
                    csvPrinter.printRecord(person.getId(), person.getFirstName(), person.getLastName(), person.getDate(),
                            person.getGender(), person.getRace(), person.getPhoneNumber(), person.getEmail());
                }
                csvPrinter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
