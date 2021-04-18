package randomcsv;

import com.github.javafaker.Faker;
import com.github.javafaker.IdNumber;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class Service {
    Scanner scannerForUpdate = new Scanner(System.in);
    Scanner scannerForNewValue = new Scanner(System.in);
    Scanner scannerForGenderAndRace = new Scanner(System.in);

    public void create() {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("randomData.csv"), StandardOpenOption.APPEND)) {
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
            Person person = createRandomData();
            csvPrinter.printRecord(person.getId(), person.getFirstName(), person.getLastName(), person.getDate(),
                    person.getGender(), person.getRace(), person.getPhoneNumber(), person.getEmail());

            csvPrinter.flush();
            csvPrinter.close();
        } catch (FileSystemException fileSystemException) {
            fileSystemException.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Person read(String id) {
        try (BufferedReader csvReader = new BufferedReader(new FileReader("randomData.csv"))) {
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");

                if (data[0].equals(id)) {
                    Person person = new Person(data[0], data[1], data[2], data[3], Gender.valueOf(data[4]), Race.valueOf(data[5]), data[6], data[7]);
                    return person;
                }

            }
            try {
                csvReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void update(String id) {
        Person person = read(id);

        System.out.println("Which feature do you want to update? ");
        System.out.println("""
                1 -> first name\s
                2 -> last name\s
                3 -> birthdate\s
                4 -> gender\s
                5 -> race\s
                6 -> phone number\s
                7 -> email""");
        int choice;
        while (true) {
            choice = scannerForUpdate.nextInt();
            if (choice >= 0 && choice <= 7) {
                break;
            } else {
                System.out.println("Please enter between 0 and 7.");
            }
        }

        Person updatedPerson = updateSmth(choice, person);
        System.out.println(updatedPerson);
        ArrayList<String[]> listOfDataFromFile = listOfDataFromFile();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("randomData.csv"))) {
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
            for (String[] dataOfOnePerson : listOfDataFromFile) {
                if (dataOfOnePerson[0].equals(person.getId())) {
                    csvPrinter.printRecord(person.getId(), person.getFirstName(), person.getLastName(), person.getDate(), String.valueOf(person.getGender()), String.valueOf(person.getRace()), person.getPhoneNumber(), person.getEmail());
                } else {
                    csvPrinter.printRecord(dataOfOnePerson[0], dataOfOnePerson[1], dataOfOnePerson[2], dataOfOnePerson[3], dataOfOnePerson[4], dataOfOnePerson[5], dataOfOnePerson[6], dataOfOnePerson[7]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) {

        Person person = read(id);
        ArrayList<String[]> listOfDataFromFile = listOfDataFromFile();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("randomData.csv"))) {
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
            for (String[] strings : listOfDataFromFile) {
                if (!strings[0].equals(person.getId())) {
                    csvPrinter.printRecord(strings[0], strings[1], strings[2], strings[3], strings[4], strings[5], strings[6], strings[7]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Person updateSmth(int choice, Person person) {
        String newValue;
        switch (choice) {
            case 1:
                System.out.println("Enter new first name: ");
                newValue = scannerForNewValue.nextLine();
                newValue = newValue.toUpperCase().charAt(0) + newValue.substring(1);
                person.setFirstName(newValue);
                break;
            case 2:
                System.out.println("Enter new last name: ");
                newValue = scannerForNewValue.nextLine();
                newValue = newValue.toUpperCase().charAt(0) + newValue.substring(1);
                person.setLastName(newValue);
                break;
            case 3:
                System.out.println("Enter new date: yyyy/mm/dd ");
                newValue = scannerForNewValue.nextLine();
                person.setDate(newValue);
                break;
            case 4:
                System.out.println("Enter new gender: \n" +
                        "1 -> Male \n" +
                        "2 -> Female");
                String newGender = scannerForGenderAndRace.nextLine();
                if (newGender.equals("1")) person.setGender(Gender.MALE);
                else person.setGender(Gender.FEMALE);
                break;
            case 5:
                System.out.println("Enter new race: \n" +
                        "1 -> Negroid \n" +
                        "2 -> Australoid \n" +
                        "3 -> Capoid \n" +
                        "4 -> Mongoloid \n" +
                        "5 -> Caucasoid");
                boolean quit = true;
                while (quit) {
                    String newRace = scannerForGenderAndRace.nextLine();
                    switch (newRace) {
                        case "1":
                            person.setRace(Race.NEGROID);
                            quit = false;
                            break;
                        case "2":
                            person.setRace(Race.AUSTRALOID);
                            quit = false;
                            break;
                        case "3":
                            person.setRace(Race.CAPOID);
                            quit = false;
                            break;
                        case "4":
                            person.setRace(Race.MONGOLOID);
                            quit = false;
                            break;
                        case "5":
                            person.setRace(Race.CAUCASOID);
                            quit = false;
                            break;
                        default:
                            System.out.println("Please enter a race from above.");
                    }
                }
                break;
            case 6:
                System.out.println("Enter new phone number: ");
                newValue = scannerForNewValue.nextLine();
                person.setPhoneNumber(newValue);
                break;
            case 7:
                System.out.println("Enter new email address: ");
                newValue = scannerForNewValue.nextLine();
                while (true) {
                    if (newValue.contains("@")) {
                        person.setEmail(newValue);
                        break;
                    } else {
                        System.out.println("Please enter valid email address.");
                        newValue = scannerForGenderAndRace.nextLine();
                    }
                }
                break;
        }
        return person;
    }

    public ArrayList<String[]> listOfDataFromFile() {
        String stringRow;
        String[] data;
        ArrayList<String[]> arrayList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("randomData.csv"))) {
            while ((stringRow = bufferedReader.readLine()) != null) {
                data = stringRow.split(",");
                arrayList.add(data);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public Person createRandomData() {
        Faker faker = new Faker();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        Date date = faker.date().birthday();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String birthdate = calendar.get(Calendar.YEAR) + "/" + Calendar.MONTH + "/" + Calendar.DAY_OF_MONTH;
        Gender gender = Gender.values()[(int) Math.floor(Math.random() * 2)];
        Race race = Race.values()[(int) Math.floor(Math.random() * 5)];
        String phoneNumber = faker.phoneNumber().cellPhone();
        String emailAddress = faker.internet().emailAddress();
        IdNumber idNumber = faker.idNumber();
        String id = idNumber.valid();

        return new Person(id, firstName, lastName, birthdate, gender, race, phoneNumber, emailAddress);
    }


}
