package menu;

import model.*;
import database.*;
import exception.InvalidInputException;

import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class VetClinicMenu implements Menu  {
    private Scanner scanner;
    private PetDAO petDAO;

    public VetClinicMenu() {
        this.scanner = new Scanner(System.in);
        this.petDAO = new PetDAO();

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║  VET CLINIC MANAGEMENT SYSTEM v2.0    ║");
        System.out.println("║  Week 8: Fully Database-Driven 🗄️     ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("✅ All data is stored in PostgreSQL");
        System.out.println("✅ No in-memory ArrayLists");
        System.out.println("✅ Complete CRUD operations");
    }

    @Override
    public void displayMenu() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║         MAIN MENU - Week 8             ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("┌─  PET MANAGEMENT  ─────────────────────┐");
        System.out.println("│ 1. Add Dog                             │");
        System.out.println("│ 2. Add Cat                             │");
        System.out.println("│ 3. View All Pet                        │");
        System.out.println("│ 4. View Dogs Only                      │");
        System.out.println("│ 5. View Cats Only                      │");
        System.out.println("│ 6. Update Pet                          │");
        System.out.println("│ 7. Delete Pet                          │");
        System.out.println("├─ SEARCH & FILTER ──────────────────────┤");
        System.out.println("│ 8. Search by Name                      │");
        System.out.println("│ 9. Search by Age                       │");
        System.out.println("│10. Old-Aged Pet (Age >= X)             │");
        System.out.println("├─ DEMO & OTHER ─────────────────────────┤");
        System.out.println("│11. Polymorphism Demo                   │");
        System.out.println("│ 0. Exit                                │");
        System.out.println("└────────────────────────────────────────┘");
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
                        addDog(); break;
                    case 2:
                        addCat(); break;
                    case 3:
                        viewAllPets(); break;
                    case 4:
                        viewAllDogs(); break;
                    case 5:
                        viewAllCats(); break;
                    case 6:
                        updatePet(); break;
                    case 7:
                        deletePet(); break;
                    case 8:
                        searchByName(); break;
                    case 9:
                        searchByAge(); break;
                    case 10:
                        searchByOldAgedPet(); break;
                    case 11:
                        demonstratePolymorphism(); break;
                    case 0:
                        running = false;
                        System.out.println("\n╔════════════════════════════════════════╗");
                        System.out.println("║  Thank you for using our system!       ║");
                        System.out.println("║  Goodbye! 👋                           ║");
                        System.out.println("╚════════════════════════════════════════╝");
                        break;
                    default:
                        System.out.println("\n❌ Invalid choice! Please select 0-11.");
                }

                if (choice != 0) {
                    pressEnterToContinue();
                }

            } catch (java.util.InputMismatchException e) {
                System.out.println("❌ Error: Please enter a valid number!");
                scanner.nextLine();
                pressEnterToContinue();
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
                scanner.nextLine();
                pressEnterToContinue();
            }
        }

        scanner.close();
    }

    private void addDog() {
        try {
            System.out.println("\n┌─ ADD DOG ─────────────────────────────┐");
            System.out.print("│ Enter Dog ID: ");
            int petID = scanner.nextInt();
            scanner.nextLine();

            System.out.print("│ Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("│ Enter Age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("│ Enter Species: ");
            String species = scanner.nextLine();

            System.out.print("│ Is trained? (true/false): ");
            boolean trained = scanner.nextBoolean();

            System.out.println("└────────────────────────────────────────┘");

            Dog dog = new Dog(petID, name, age, species, trained) ;
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
            System.out.println("\n┌─ ADD CAT ───────────────────────────┐");
            System.out.print("│ Enter Cat ID: ");
            int petID = scanner.nextInt();
            scanner.nextLine();

            System.out.print("│ Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("│ Enter Age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("│ Enter Species: ");
            String species = scanner.nextLine();

            System.out.print("│ Is indoor? (true/false): ");
            boolean indoor = scanner.nextBoolean();

            Cat cat = new Cat(petID, name, age, species, indoor) ;
            petDAO.insertCat(cat);
            System.out.println("\n✅ Cat added successfully!");

        } catch (java.util.InputMismatchException e) {
            System.out.println("❌ Error: Invalid input type!");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Validation Error: " + e.getMessage());
        }
    }

    private void viewAllPets() {
        petDAO.displayAllPets();
    }

    private void viewAllDogs() {
        List<Dog> dogs = petDAO.getAllDogs();

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║         DOGS ONLY                      ║");
        System.out.println("╚════════════════════════════════════════╝");

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
            System.out.println("Total Dogs: " + dogs.size());
        }
    }

    private void viewAllCats() {
        List<Cat> cats = petDAO.getAllCats();

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║        CATS ONLY                       ║");
        System.out.println("╚════════════════════════════════════════╝");


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
            System.out.println("Total Cats: " + cats.size());
        }
    }

    private void updatePet() {
        System.out.println("\n┌─ UPDATE PET ─────────────────────────┐");
        System.out.print("│ Enter Pet ID to update: ");

        try {
            int petID = scanner.nextInt();
            scanner.nextLine();

            Pet existingPet = petDAO.getPetById(petID);

            if (existingPet == null) {
                System.out.println("❌ No pet found with ID: " + petID);
                return;
            }

            System.out.println("│ Current Info:");
            System.out.println("│ " + existingPet.toString());
            System.out.println("└────────────────────────────────────────┘");

            System.out.println("\n┌─ ENTER NEW VALUES ─────────────────────┐");
            System.out.println("│ (Press Enter to keep current value)   │");

            System.out.print("│ New Name [" + existingPet.getName() + "]: ");
            String newName = scanner.nextLine();
            if (newName.trim().isEmpty()) {
                newName = existingPet.getName();
            }

            System.out.print("│ New Age [" + existingPet.getAge() + "]: ");
            String ageInput = scanner.nextLine();
            int newAge = ageInput.trim().isEmpty() ?
                    existingPet.getAge() : Integer.parseInt(ageInput) ;

            System.out.print("│ New Species [" + existingPet.getSpecies() + "]: ");
            String newSpecies = scanner.nextLine();
            if (newSpecies.trim().isEmpty()) {
                newSpecies = existingPet.getName();
            }

            if (existingPet instanceof Dog) {
                Dog dog = (Dog) existingPet;
                System.out.print("│ New if Trained [" + dog.isTrained() + "]: ");
                boolean newTrained = scanner.nextBoolean();

                Dog updatedDog = new Dog(petID, newName, newAge, newSpecies , newTrained ) ;
                petDAO.updateDog(updatedDog);

            } else if (existingPet instanceof Cat) {
                Cat cat = (Cat) existingPet;
                System.out.print("│ New if Indoor [" + cat.isIndoor() + "]: ");
                boolean newIndoor = scanner.nextBoolean() ;

                Cat updatedCat = new Cat(petID, newName, newAge, newSpecies , newIndoor );
                petDAO.updateCat(updatedCat);
            }

            System.out.println("└────────────────────────────────────────┘");
        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Invalid number format!");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Validation Error: " + e.getMessage());
        }
    }

    private void deletePet() {
        System.out.println("\n┌─ DELETE PET ─────────────────────────┐");
        System.out.print("│ Enter Pet ID to delete: ");

        try {
            int petID = scanner.nextInt();
            scanner.nextLine();

            Pet pet = petDAO.getPetById(petID) ;

            if (pet == null) {
                System.out.println("❌ No pets found with ID: " + petID);
                return;
            }

            System.out.println("│ Pet to delete:");
            System.out.println("│ " + pet.toString());
            System.out.println("└────────────────────────────────────────┘");

            System.out.print("⚠️  Are you sure? (yes/no): ");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("yes")) {
                petDAO.deletePet(petID);
            } else {
                System.out.println("❌ Deletion cancelled.");
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("❌ Error: Invalid input!");
            scanner.nextLine();
        }
    }

    private void searchByName() {
        System.out.println("\n┌─ SEARCH BY NAME ───────────────────────┐");
        System.out.print("│ Enter name to search: ");
        String name = scanner.nextLine();
        System.out.println("└────────────────────────────────────────┘");

        List<Pet> results = petDAO.searchByName(name);

        displaySearchResults(results, "Search: '" + name + "'");
    }

    private void searchByAge() {
        try {
            System.out.println("\n┌─ SEARCH BY AGE ───────────────┐");
            System.out.print("│ Enter minimum age: ");
            int minAge = scanner.nextInt();
            scanner.nextLine();

            System.out.print("│ Enter maximum age: ");
            int maxAge = scanner.nextInt();
            scanner.nextLine();
            System.out.println("└────────────────────────────────────────┘");

            List<Pet> results = petDAO.searchByAge(minAge, maxAge);

            displaySearchResults(results, "Age: " + minAge + " - " + maxAge + " y.o.");

        } catch (java.util.InputMismatchException e) {
            System.out.println("❌ Error: Invalid number!");
            scanner.nextLine();
        }
    }

    private void searchByOldAgedPet() {
        try {
            System.out.println("\n┌─ OLD AGED PET ──────────────────────┐");
            System.out.print("│ Enter minimum age: ");
            int minAge = scanner.nextInt();
            scanner.nextLine();
            System.out.println("└────────────────────────────────────────┘");

            List<Pet> results = petDAO.searchByMinAge(minAge);

            displaySearchResults(results, "Age >= " + minAge + " y.o.");

        } catch (java.util.InputMismatchException e) {
            System.out.println("❌ Error: Invalid number!");
            scanner.nextLine();
        }
    }

    private void displaySearchResults(List<Pet> results, String criteria) {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║         SEARCH RESULTS                 ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("Criteria: " + criteria);
        System.out.println("─────────────────────────────────────────");

        if (results.isEmpty()) {
            System.out.println("📭 No pet found matching criteria.");
        } else {
            for (int i = 0; i < results.size(); i++) {
                Pet p = results.get(i);
                System.out.print((i + 1) + ". ");
                System.out.print("[" + p.getType() + "] ");
                System.out.println(p.toString());
            }
            System.out.println("─────────────────────────────────────────");
            System.out.println("Total Results: " + results.size());
        }
    }

    private void demonstratePolymorphism() {
        petDAO.demonstratePolymorphism();
    }

    private void pressEnterToContinue() {
        System.out.println("\n[Press Enter to continue...]");
        scanner.nextLine();
    }
}
