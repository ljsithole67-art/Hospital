

package com.mycompany.hospital;
import java.util.Scanner;

/**
 *
 * @author ljsit
 */
public class Hospital {


    private static final Scanner scanner = new Scanner(System.in);
    private static final HospitalService hospital = new HospitalService();       


              

    public static void main(String[] args) {

        int choice;
   System.out.println("=====================================");
        System.out.println("     WELCOME TO THE HOSPITAL SYSTEM");
        System.out.println("=====================================");

        do {
            displayMainMenu();

            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    patientManagement();
                    break;

                case 2:
                    bedManagement();
                    break;

                case 3:
                    reports();
                    break;

                case 4:
                    patientCategories();
                    break;

                case 0:
                    System.out.println("Thank you for using the Hospital System.");
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }

        } while (choice != 0);
    }

    // MAIN MENU
    public static void displayMainMenu() {

        System.out.println("\n========== MAIN MENU ==========");
        System.out.println("1. Patient Management");
        System.out.println("2. Bed Management");
        System.out.println("3. Reports");
        System.out.println("4. Patient Categories");
        System.out.println("0. Exit");
        System.out.println("===============================");
    }

    // PATIENT MANAGEMENT
    public static void patientManagement() {

        int choice;

        do {

            System.out.println("\n===== PATIENT MANAGEMENT =====");
            System.out.println("1. Add Patient");
            System.out.println("2. Search Patient");
            System.out.println("3. Update Patient");
            System.out.println("4. Delete Patient");
            System.out.println("5. Display All Patients");
            System.out.println("0. Back to Main Menu");

            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    registerPatient();
                    break;

                case 2:
                    searchPatient();
                    break;

                case 3:
                    updatePatient();
                    break;

                case 4:
                    deletePatient();
                    break;

                case 5:
                    displayPatients();
                    break;

                case 0:
                    System.out.println("Returning to Main Menu...");
                    break;

                default:
                    System.out.println("Invalid option.");
            }

        } while (choice != 0);
    }

    // BED MANAGEMENT
    public static void bedManagement() {

        int choice;

        do {

            System.out.println("\n======= BED MANAGEMENT =======");
            System.out.println("1. Allocate Bed");
            System.out.println("2. Release Bed");
            System.out.println("3. View Ward Layout");
            System.out.println("4. View Available Beds");
            System.out.println("5. View Occupied Beds");
            System.out.println("0. Back to Main Menu");

            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    allocateBed();
                    break;

                case 2:
                    releaseBed();
                    break;

                case 3:
                    displayWard();
                    break;

                case 4:
                    displayAvailableBeds();
                    break;

                case 5:
                    displayOccupiedBeds();
                    break;

                case 0:
                    System.out.println("Returning to Main Menu...");
                    break;

                default:
                    System.out.println("Invalid option.");
            }

        } while (choice != 0);
    }

    // REPORTS
    public static void reports() {

        System.out.println("\n========== REPORTS ==========");
        System.out.println("Total Patients: "
                + hospital.getPatients().size());

        System.out.println("Occupied Beds: "
                + hospital.getOccupiedBedCount());

        System.out.println("Available Beds: "
                + hospital.getAvailableBedCount());

        System.out.printf("Occupancy Percentage: %.2f%%%n",
                hospital.getOccupancyPercentage());
    }

    // PATIENT CATEGORIES
    public static void patientCategories() {

        System.out.println("\n===== PATIENT CATEGORIES =====");
        System.out.println("1. Inpatient");
        System.out.println("2. Outpatient");
        System.out.println("3. Emergency");

        System.out.println("\nAn Inpatient can be allocated a bed.");
        System.out.println("Outpatients do not require a bed.");
        System.out.println("Emergency patients are treated as emergencies.");
    }

    // ADD PATIENT
    public static void registerPatient() {

        System.out.println("\n------- ADD PATIENT -------");

        System.out.print("Patient ID: ");
        String id = scanner.nextLine();

        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Gender: ");
        String gender = scanner.nextLine();

        System.out.print("Medical Condition: ");
        String condition = scanner.nextLine();

        System.out.println("\n1. Inpatient");
        System.out.println("2. Outpatient");
        System.out.println("3. Emergency");

        System.out.print("Choose category: ");
        int categoryChoice = scanner.nextInt();
        scanner.nextLine();

        PatientCategory category;

        switch (categoryChoice) {

            case 1:
                category = PatientCategory.INPATIENT;
                break;

            case 2:
                category = PatientCategory.OUTPATIENT;
                break;

            case 3:
                category = PatientCategory.EMERGENCY;
                break;

            default:
                System.out.println("Invalid category.");
                return;
        }

        Patient patient;

        if (category == PatientCategory.INPATIENT) {

            patient = new Inpatient(
                    id,
                    firstName,
                    lastName,
                    age,
                    gender,
                    condition,
                    "Ward 1",
                    "Not Allocated"
            );

        } else {

            patient = new Patient(
                    id,
                    firstName,
                    lastName,
                    age,
                    gender,
                    condition,
                    category
            );
        }

        if (hospital.registerPatient(patient)) {
            System.out.println("Patient added successfully!");
        } else {
            System.out.println("Patient ID already exists.");
        }
    }

    // SEARCH PATIENT
    public static void searchPatient() {

        System.out.print("Enter Patient ID: ");
        String id = scanner.nextLine();

        Patient patient = hospital.findPatient(id);

        if (patient != null) {
            patient.displayDetails();
        } else {
            System.out.println("Patient not found.");
        }
    }

    // UPDATE PATIENT
    public static void updatePatient() {
        System.out.println("Update Patient option.");
        // We can connect this to updatePatient()
        // in HospitalService.
    }

    // DELETE PATIENT
    public static void deletePatient() {

        System.out.print("Enter Patient ID to delete: ");
        String id = scanner.nextLine();

        if (hospital.deletePatient(id)) {
            System.out.println("Patient deleted successfully.");
        } else {
            System.out.println("Patient not found.");
        }
    }

    // DISPLAY PATIENTS
    public static void displayPatients() {

        System.out.println("\n===== ALL PATIENTS =====");

        for (Patient patient : hospital.getPatients()) {

            patient.displayDetails();

            System.out.println("-----------------------");
        }
    }

    // ALLOCATE BED
    public static void allocateBed() {

        System.out.print("Enter Patient ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Row (0 - 3): ");
        int row = scanner.nextInt();

        System.out.print("Enter Column (0 - 4): ");
        int column = scanner.nextInt();
        scanner.nextLine();

        if (hospital.allocateBed(id, row, column)) {

            System.out.println("Bed allocated successfully!");

        } else {

            System.out.println("Bed allocation failed.");
        }
    }

    // RELEASE BED
    public static void releaseBed() {

        System.out.print("Enter Patient ID: ");
        String id = scanner.nextLine();

        if (hospital.releaseBed(id)) {

            System.out.println("Bed released successfully.");

        } else {

            System.out.println("Patient does not have an allocated bed.");
        }
    }

    // DISPLAY WARD
    public static void displayWard() {

        System.out.println("\n===== WARD LAYOUT =====");

        for (int row = 0; row < 4; row++) {

            for (int column = 0; column < 5; column++) {

                String bedNumber =   hospital.getBedNumber(row, column);
                     

                if (hospital.isBedOccupied(row, column)) {

                    System.out.print("[" + bedNumber + " OCCUPIED] ");

                } else {

                    System.out.print("[" + bedNumber + " AVAILABLE] ");
                }
            }

            System.out.println();
        }
    }

    // AVAILABLE BEDS
    public static void displayAvailableBeds() {

        System.out.println("Available Beds: " + hospital.getAvailableBedCount());
               
    }

    // OCCUPIED BEDS
    public static void displayOccupiedBeds() {

        System.out.println("Occupied Beds: " + hospital.getOccupiedBedCount());
               
    }
}