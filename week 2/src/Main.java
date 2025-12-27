public class Main {
    public static void main(String[] args) {
        System.out.println("=== Veterinary Clinic Management System ===");
        System.out.println();

        Pet pet1 = new Pet(101, "Aktos", "dog", 2);
        Pet pet2 = new Pet(102, "Musya", "cat", 1);
        Pet pet3 = new Pet(103, "Aktaban", "dog", 3);
        Owner owner1 = new Owner(201, "Ainur", "+77001234567", 1);
        Veterinarian vet1 = new Veterinarian(301, "Dr. Dastan", "dog", 6);
        Veterinarian vet2 = new Veterinarian();

        System.out.println("--- PETS ---");
        System.out.println(pet1);
        System.out.println(pet2);
        System.out.println(pet3);
        System.out.println();

        System.out.println("--- OWNERS ---");
        System.out.println(owner1);
        System.out.println();

        System.out.println("--- VETS ---");
        System.out.println(vet1);
        System.out.println();

        System.out.println("--- TESTING GETTERS ---");
        System.out.println("Pet 1 name: " + pet1.getName());
        System.out.println("Pet 1 age: " + pet1.getAge() + " y.o.");
        System.out.println("Owner 1 phone: " + owner1.getPhone());
        System.out.println("Vet's name: " + vet1.getName());
        System.out.println();

        System.out.println("--- TESTING SETTERS ---");
        System.out.println("Updating pet1....");
        pet1.setPetID(111);
        pet1.setName("Barsik");
        pet1.setAge(3);
        pet1.setSpecies("Cat");
        System.out.println("Updated: " + pet1);
        System.out.println();

        System.out.println("Changing owner's phone...");
        owner1.setPhone("+77714567890");
        System.out.println("Updated: " + owner1);
        System.out.println();

        System.out.println("--- TESTING PET METHODS ---");
        System.out.println(pet1.getName() + " is young: " + pet1.isYoung());
        System.out.println(pet1.getName() + "'s life stage: " + pet1.getLifeStage());
        System.out.println();

        System.out.println("--- TESTING OWNER METHODS ---");
        System.out.println("Number of pets: " + owner1.getNumberOfPets());
        System.out.println("Adding a new pet to owner...");
        owner1.addPet();
        System.out.println("Number of pets: " + owner1.getNumberOfPets());
        System.out.println("Frequent client: " + owner1.isFrequentClient());
        System.out.println();

        System.out.println("--- TESTING VET METHODS ---");
        System.out.println("Vet experienced: " + vet1.isExperienced());
        System.out.println("Can treat pet1: " + vet1.canTreat(pet1.getSpecies()));
        System.out.println("Can treat pet3: " + vet1.canTreat(pet3.getSpecies()));
        System.out.println();

        System.out.println("--- FINAL STATE ---");
        System.out.println("Pets:");
        System.out.println(pet1);
        System.out.println(pet2);
        System.out.println(pet3);
        System.out.println();
        System.out.println("Owners:");
        System.out.println(owner1);
        System.out.println();
        System.out.println("Vets:");
        System.out.println(vet1);

        System.out.println("\n=== Program Complete ===");
    }
}