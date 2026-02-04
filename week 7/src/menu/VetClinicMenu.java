package menu;

import model.*;
import exception.InvalidInputException;
import database.*;
import org.postgresql.util.GT;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VetClinicMenu implements Menu  {
    private ArrayList<Pet> pets;
    private ArrayList<Veterinarian> vets;
    private Scanner scanner;
    private PetDAO petDAO;

    public VetClinicMenu() {
        this.pets = new ArrayList<>();
        this.vets = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.petDAO = new PetDAO();

        try {
            pets.add(new Dog(101, "Aktos", 3, true));
            pets.add(new Cat(201, "Marusya", 1, true));

            vets.add(new Veterinarian(401, "Dr. Marat", "dog", 5));
            vets.add(new Veterinarian(402, "Dr. Aidyn", "cat", 6));
            vets.add(new Veterinarian(403, "Dr. Aigul", "cat", 1));
        } catch (IllegalArgumentException e) {
            System.out.println("Error initializing test data: " + e.getMessage());
        }

        System.out.println("\n🗄️ DATABASE MODE: ON");
        System.out.println("   Pet will be saved to/loaded from PostgreSQL");
    }

    @Override
    public void displayMenu() {
        System.out.println("\n=============================================");
        System.out.println("VET CLINIC MANAGEMENT SYSTEM");
        System.out.println("  Week 7: With Database! 🗄️");
        System.out.println("=============================================");
        System.out.println("1. Add Dog");
        System.out.println("2. Add Cat");
        System.out.println("3. View All Pets");
        System.out.println("4. View Dogs Only");
        System.out.println("5. View Cats Only");
        System.out.println("6. Demonstrate Polymorphism");
        System.out.println("7. Add Vet");
        System.out.println("8. View All Vets");
        System.out.println("9. Vet Treats Pets");
        System.out.println("0. Exit");
        System.out.println("=============================================");
    }

    @Override
    public void run() {
        boolean running = true;

        while (running) {
            displayMenu();
            System.out.println("Enter your Choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {

                    case 1:
                        addDog();
                        break;
                    case 2:
                        addCat();
                        break;
                    case 3:
                        viewAllPets();
                        break;
                    case 4:
                        viewAllDogs();
                        break;
                    case 5:
                        viewAllCats();
                        break;
                    case 6:
                        demonstratePolymorphism();
                        break;
                    case 7:
                        addVet();
                        break;
                    case 8:
                        viewAllVets();
                        break;
                    case 9:
                        vetTreatsPets();
                        break;
                    case 0:
                        running = false;
                        System.out.println("\nThank you for using Vet Clinic Management System!");
                        System.out.println("\nGoodbye! 👋");
                        break;
                    default:
                        System.out.println("\n❌ Invalid choice! Please select 0-9.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("❌ Error: Please enter a valid number!");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private void addDog() {
        try {
            System.out.println("\n--- ADD DOG  to Database ---");
            System.out.print("Enter ID: ");
            int petID = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Is trained? (true/false): ");
            boolean trained = scanner.nextBoolean();

            Dog dog = new Dog(petID, name, age, trained) ;
            petDAO.insertDog(dog);
            System.out.println("\n✅ Dog added successfully!");
        } catch (java.util.InputMismatchException e) {
            System.out.println("❌ Error: Invalid input type!");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Validation Error: " + e.getMessage());
        }
    }

    private void addCat() {
        try {
            System.out.println("\n--- ADD CAT to Database ---");
            System.out.print("Enter ID: ");
            int petID = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Is indoor? (true/false): ");
            boolean indoor = scanner.nextBoolean();

            Cat cat = new Cat(petID, name, age, indoor) ;
            petDAO.insertCat(cat);
            System.out.println("\n✅ Cat added successfully!");

        } catch (java.util.InputMismatchException e) {
            System.out.println("❌ Error: Invalid input type!");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Validation Error: " + e.getMessage());
        }
    }

    // Week 7: View All Staff from DATABASE
    private void viewAllPets() {
        petDAO.displayAllPets();
    }

    private void viewAllDogs() {
        List<Dog> dogs = petDAO.getAllDogs();

        System.out.println("\n========================================");
        System.out.println("               DOGS ONLY");
        System.out.println("========================================");


        if (dogs.isEmpty()) {
            System.out.println("No dogs in database.");
        } else {
            for (int i = 0; i < dogs.size(); i++) {
                Dog dog = dogs.get(i);
                System.out.println((i + 1) + ". " + dog.toString());
                System.out.println("   Trained: " + dog.isTrained());
                dog.assistOwner();
                System.out.println();
            }
        }

        System.out.println("========================================");
    }

    private void viewAllCats() {
        List<Cat> cats = petDAO.getAllCats();

        System.out.println("\n========================================");
        System.out.println("                CATS ONLY");
        System.out.println("========================================");


        if (cats.isEmpty()) {
            System.out.println("No cats in database.");
        } else {
            for (int i = 0; i < cats.size(); i++) {
                Cat cat = cats.get(i);
                System.out.println((i + 1) + ". " + cat.toString());
                System.out.println("   Indoor: " + cat.isIndoor());
                cat.scratch();
                System.out.println();
            }
        }

        System.out.println("========================================");
    }

    // Week 7: Polymorphism with DATABASE data!
    private void demonstratePolymorphism() {
        petDAO.demonstratePolymorphism();
    }

    private void addVet() {
        try {
            System.out.println("\n--- Add Veterinarian ---");

            System.out.print("Enter ID: ");
            int vetID = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Specialization: ");
            String specialization = scanner.nextLine();

            System.out.print("Enter Experience Years: ");
            int experienceYears = scanner.nextInt();
            scanner.nextLine();

            Veterinarian vet = new Veterinarian(vetID, name, specialization, experienceYears) ;
            vets.add(vet);
            System.out.println("✅ Vet added successfully!");

        } catch (java.util.InputMismatchException e) {
            System.out.println("❌ Error: Invalid input type!");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Validation Error: " + e.getMessage());
        }
    }

    private void viewAllVets() {
        System.out.println("\n========================================");
        System.out.println("                ALL VETS");
        System.out.println("========================================");

        if (vets.isEmpty()) {
            System.out.println("No vets found.");
            return;
        }

        for (int i = 0; i < vets.size(); i++) {
            System.out.println((i + 1) + ". " + vets.get(i).toString());
        }
    }

    private void vetTreatsPets() {
        System.out.println("\n--- Vet Treats Pets ---");

        if (vets.isEmpty()) {
            System.out.println("❌ No vets available to treat pets!");
            return;
        }

        if (pets.isEmpty()) {
            System.out.println("❌ No pets available to treat!");
            return;
        }

        System.out.println("Available vets:");
        for (int i = 0; i < vets.size(); i++) {
            System.out.println((i + 1) + ". " + vets.get(i).getName());
        }

        try {
            System.out.print("Select vet number: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice < 1 || choice > vets.size()) {
                throw new InvalidInputException("Invalid vet selection!");
            }

            System.out.println("Available pets:");
            for (int i = 0; i < pets.size(); i++) {
                System.out.println((i + 1) + ". " + pets.get(i).getName());
            }

            System.out.print("Select pet number: ");
            int petChoice = scanner.nextInt();
            scanner.nextLine();

            if (petChoice < 1 || petChoice > pets.size()) {
                throw new InvalidInputException("Invalid pet selection!");
            }

            Veterinarian vet = vets.get(choice - 1);
            Pet selectedPet = pets.get(petChoice - 1);
            vet.treat(selectedPet);
            System.out.println(vet.getTreatmentPlan(selectedPet));

        } catch (java.util.InputMismatchException e) {
            System.out.println("❌ Error: Please enter a valid number!");
            scanner.nextLine();
        } catch (InvalidInputException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

}
