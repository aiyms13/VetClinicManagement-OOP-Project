import database.DatabaseConnection;
import database.PetDAO;
import model.Pet;
import model.Dog;
import model.Cat;

import java.sql.Connection;
import java.util.List;

public class Test {
    static void main() {
        Dog testDog = new Dog(1,"Aktos", 5, "Dog", true);
        PetDAO petDAO = new PetDAO();
        petDAO.insertDog(testDog);

        List<Dog> dogs = petDAO.getAllDogs();
        for(Dog dog : dogs){
            System.out.println(dog);
        }
    }
}
