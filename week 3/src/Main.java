import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Pet> pets = new ArrayList<>();
    private static ArrayList<Owner> owners = new ArrayList<>();
    private static ArrayList<Veterinarian> veterinarians = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        pets.add(new Pet(101, "Aktos", "dog", 3));
        pets.add(new Pet(102, "Aktaban", "dog", 2));
        pets.add(new Pet(103, "Musya", "cat", 1));

        owners.add(new Owner(201, "Arman", "+77011234567", 1));
        owners.add(new Owner(202, "Akbota", "+77012345678", 1));

        veterinarians.add(new Veterinarian(301, "Dr. Dastan", "dog", 5));
        veterinarians.add(new Veterinarian(302, "Dr. Samat", "cat", 4));

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addPet();
                    break;
                case 2:
                    viewAllPets();
                    break;
                case 3:
                    addOwner();
                    break;
                case 4:
                    viewAllOwners();
                    break;
                case 5:
                    addVeterinarian();
                    break;
                case 6:
                    viewAllVeterinarians();
                    break;
                case 0:
                    System.out.println("\nGoodbye! 👋");
                    running = false;
                    break;
                default:
                    System.out.println("\n❌ Invalid choice!");
            }

            if (running) {
                System.out.println("\nPress to continue...");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n=============================================");
        System.out.println("VET CLINIC MANAGEMENT SYSTEM");
        System.out.println("=============================================");
        System.out.println("1. Add Pet");
        System.out.println("2. View All Pets");
        System.out.println("3. Add Owner");
        System.out.println("4. View All Owners");
        System.out.println("5. Add Vet");
        System.out.println("6. View All Vets");
        System.out.println("0. Exit");
        System.out.println("=============================================");
        System.out.println("Enter your Choice: ");
    }

    private static void addPet() {
        System.out.println("\n--- ADD PET ---");
        System.out.print("Enter pet ID: ");
        int petID = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter pet name: ");
        String name = scanner.nextLine();

        System.out.print("Enter species: ");
        String species = scanner.nextLine();

        System.out.print("Enter pet age: ");
        int age = scanner.nextInt();

        Pet pet = new Pet(petID , name , species , age) ;
        pets.add(pet);
        System.out.println("\n✅ Pet added successfully!");
    }

    private static void viewAllPets() {
        System.out.println("\n========================================");
        System.out.println(" ALL PETS");
        System.out.println("========================================");
        if (pets.isEmpty()) {
            System.out.println("No pets found.");
            return;
        }
        System.out.println("Total pets: " + pets.size());
        System.out.println();
        for (int i = 0; i < pets.size(); i++) {
            Pet pet = pets.get(i);
            System.out.println((i + 1) + ". " + pet.getPetID() +
                    " - " + pet.getName());
            System.out.println(" Species: " + pet.getSpecies());
            System.out.println(" Age: " + pet.getAge());
            if (pet.isYoung()) {
                System.out.println(" Baby Pet 🍼");
            }
            System.out.println();
        }
    }

    private static void addOwner() {
        System.out.println("\n--- ADD OWNER ---");
        System.out.print("Enter owner ID: ");
        int ownerID = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter owner name: ");
        String name = scanner.nextLine();

        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();

        System.out.print("Enter number of pets: ");
        int numberOfPets = scanner.nextInt();

        Owner owner = new Owner(ownerID, name, phone, numberOfPets) ;
        owners.add(owner);
        System.out.println("\n✅ Owner added successfully!");
    }

    private static void viewAllOwners() {
        System.out.println("\n========================================");
        System.out.println(" ALL OWNERS ");
        System.out.println("========================================");
        if (pets.isEmpty()) {
            System.out.println("No owners found.");
            return;
        }
        System.out.println("Total owners: " + owners.size());
        System.out.println();
        for (int i = 0; i < owners.size(); i++) {
            Owner owner = owners.get(i);
            System.out.println((i + 1) + ". " + owner.getOwnerID() +
                    " - " + owner.getName());
            System.out.println(" Phone: " + owner.getPhone());
            System.out.println(" Number of Pets: " + owner.getNumberOfPets());
            if (owner.isFrequentClient()) {
                System.out.println(" The Best Client ❤️");
            }
            System.out.println();
        }
    }

    private static void addVeterinarian() {
        System.out.println("\n--- ADD VET ---");
        System.out.print("Enter vet ID: ");
        int vetID = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter vet name: ");
        String name = scanner.nextLine();

        System.out.print("Enter specialization: ");
        String specialization = scanner.nextLine();

        System.out.print("Enter vet experience years: ");
        int experienceYears = scanner.nextInt();

        Veterinarian veterinarian = new Veterinarian(vetID , name , specialization , experienceYears) ;
        veterinarians.add(veterinarian);
        System.out.println("\n✅ Vet added successfully!");
    }

    private static void viewAllVeterinarians() {
        System.out.println("\n========================================");
        System.out.println(" ALL VETS ");
        System.out.println("========================================");
        if (veterinarians.isEmpty()) {
            System.out.println("No vets found.");
            return;
        }
        System.out.println("Total vets: " + veterinarians.size());
        System.out.println();
        for (int i = 0; i < veterinarians.size(); i++) {
            Veterinarian veterinarian = veterinarians.get(i);
            System.out.println((i + 1) + ". " + veterinarian.getVetID() +
                    " - " + veterinarian.getName());
            System.out.println(" Specialization: " + veterinarian.getSpecialization());
            System.out.println(" Experience Years: " + veterinarian.getExperienceYears());
            if (veterinarian.isExperienced()) {
                System.out.println(" The Most Prof Vet 😎");
            }
            System.out.println();
        }
    }


}