package database;

import model.Dog;
import model.Pet;

public class TestInsert {
    public static void main(String[] args) {
        // Create staff object
        Dog dog = new Dog(2, "Aktos", 4, true) {
        };
        // Insert into database
        PetDAO dao = new PetDAO();
        dao.insertDog(dog) ;
        // Check in pgAdmin or psql:
        // SELECT * FROM staff;
    }
}
