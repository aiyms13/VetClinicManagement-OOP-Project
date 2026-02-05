import database.DatabaseConnection;
import database.PetDAO;
import menu.Menu;
import menu.VetClinicMenu;
import model.Dog;
import model.Pet;

import javax.xml.crypto.Data;
import java.rmi.ConnectIOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Menu menu = new VetClinicMenu();
        menu.run();
        PetDAO petDAO = new PetDAO();
        List<Pet> petList = petDAO.getAllPets();
        for(Pet pet : petList){
            System.out.println(pet);
        }
    }
}