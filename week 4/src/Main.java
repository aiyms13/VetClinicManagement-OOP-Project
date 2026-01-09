import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Pet> pets = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        pets.add(new Pet(100, "Rio", 2, "Bird"));
        pets.add(new Dog(101, "Aktos", 3, true));
        pets.add(new Cat(201, "Marusya", 1, true));

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
                    addDog();
                    break;
                case 3:
                    addCat();
                    break;
                case 4:
                    viewAllPets();
                    break;
                case 5:
                    demonstratePolymorphism();
                    break;
                case 6:
                    viewAllDogs();
                    break;
                case 7:
                    viewAllCats();
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
        System.out.println("2. Add Dog");
        System.out.println("3. Add Cat");
        System.out.println("4. View All Pets");
        System.out.println("5. Demonstrate Polymorphism");
        System.out.println("6. View Dogs Only");
        System.out.println("7. View Cats Only");
        System.out.println("0. Exit");
        System.out.println("=============================================");
        System.out.println("Enter your Choice: ");
    }

    private static void addPet() {
        System.out.println("\n--- ADD PET ---");
        System.out.print("Enter ID: ");
        int petID = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter species: ");
        String species = scanner.nextLine();

        Pet pet = new Pet(petID, name, age, species) ;
        pets.add(pet);
        System.out.println("\n✅ Pet added successfully!");
    }

    private static void addDog() {
        System.out.println("\n--- ADD DOG ---");
        System.out.print("Enter ID: ");
        int petID = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Is trained? (true/false): ");
        boolean trained = scanner.nextBoolean();

        Pet pet = new Dog(petID, name, age, trained) ;
        pets.add(pet);
        System.out.println("\n✅ Dog added successfully!");
    }

    private static void addCat() {
        System.out.println("\n--- ADD CAT ---");
        System.out.print("Enter ID: ");
        int petID = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Is indoor? (true/false): ");
        boolean indoor = scanner.nextBoolean();

        Pet pet = new Cat(petID, name, age, indoor) ;
        pets.add(pet);
        System.out.println("\n✅ Cat added successfully!");
    }

    private static void viewAllPets() {
        System.out.println("\n========================================");
        System.out.println(" ALL PET (POLYMORPHIC LIST)");
        System.out.println("========================================");
        if (pets.isEmpty()) {
            System.out.println("No pets found.");
            return;
        }
        System.out.println("Total staff: " + pets.size());
        System.out.println();
        for (int i = 0; i < pets.size(); i++) {
            Pet p = pets.get(i);
            System.out.println((i + 1) + ". " + p); // Calls overridden toString()
            // Use instanceof to check type and show child-specific info
            if (p instanceof Dog) {
                Dog d = (Dog) p; // Downcast
                if (d.needsTraining()) {
                    System.out.println(" ⚠️ Needs training!");
                } else {
                    System.out.println(" 👍 Well-trained dog.");
                }
            } else if (p instanceof Cat) {
                Cat c = (Cat) p; // Downcast
                if (c.requiresOutdoorCheck()) {
                    System.out.println(" 🫵 Check outdoor activity.");
                } else {
                    System.out.println(" ✅ Indoor cat is safe.");
                }
            }
            System.out.println();
        }
    }
    private static void demonstratePolymorphism() {
        System.out.println("\n========================================");
        System.out.println(" POLYMORPHISM DEMONSTRATION");
        System.out.println("========================================");
        System.out.println("Calling performAction() on all pets:");
        System.out.println();
        for (Pet p : pets) {
            p.performAction(); // Polymorphism: Same method, different behavior!
        }
        System.out.println();
    }

    private static void viewAllDogs() {
        System.out.println("\n========================================");
        System.out.println(" DOGS ONLY");
        System.out.println("========================================");
        int dogCount = 0;
        for (Pet p : pets) {
            if (p instanceof Dog) { // Filter by type
                Dog d = (Dog) p; // Downcast to access Chef methods
                dogCount++;
                System.out.println(dogCount + ". " + d.getName());
                System.out.println("   Age: " + d.age + " y.o.");
                System.out.println("   Trained: " + (d.needsTraining() ? "No" : "Yes"));
                d.assistOwner();// Call unique Dog method
                System.out.println();
            }
        }
        if (dogCount == 0) {
            System.out.println("No dogs found.");
        }
    }

    private static void viewAllCats() {
        System.out.println("\n========================================");
        System.out.println(" CATS ONLY");
        System.out.println("========================================");
        int catCount = 0;
        for (Pet p : pets) {
            if (p instanceof Cat) { // Filter by type
                Cat c = (Cat) p; // Downcast to access Chef methods
                catCount++;
                System.out.println(catCount + ". " + c.getName());
                System.out.println("   Age: " + c.age + " y.o.");
                System.out.println("   Indoor: " + (c.requiresOutdoorCheck() ? "No" : "Yes"));
                c.scratch(); // unique method #2 demonstration            // Call unique Dog method
                System.out.println();
            }
        }
        if (catCount == 0) {
            System.out.println("No cats found.");
        }
    }
}