package randomcsv;

import java.util.Scanner;

public class Controller {
    Service service = new Service();
    CSVFileWriter CSVFileWriter = new CSVFileWriter();

    public void controller() {
        CSVFileWriter.writeToFile();
        Scanner scannerForMenu = new Scanner(System.in);
        Scanner scannerForID = new Scanner(System.in);

        System.out.println("------MENU------");
        System.out.println("0 -> create \n" +
                "1 -> read \n" +
                "2 -> update \n" +
                "3 -> delete \n" +
                "4 -> quit");
        String choice;
        boolean quit = true;
        while (quit) {
            System.out.println("Choose: ");
            choice = scannerForMenu.nextLine();
            switch (choice) {
                case "0":
                    service.create();
                    System.out.println("Successfully created.");
                    break;
                case "1":
                    System.out.println("Enter id for reading: ");
                    String readId = scannerForID.nextLine();
                    System.out.println(service.read(readId));
                    System.out.println("Successfully read.");
                    break;
                case "2":
                    System.out.println("Enter id for updating: ");
                    String updateId = scannerForID.nextLine();
                    service.update(updateId);
                    System.out.println("Successfully updated.");
                    break;
                case "3":
                    System.out.println("Enter id for deleting: ");
                    String deleteId = scannerForID.nextLine();
                    service.delete(deleteId);
                    System.out.println("Successfully deleted.");
                    break;
                case "4":
                    System.out.println("Thank you :)");
                    quit = false;
                    break;
                default:
                    System.out.println("Try again! ");
            }
        }
    }
}
