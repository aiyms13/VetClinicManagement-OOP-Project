package database;

import model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {

    // INSERT DOG
    public boolean insertDog(Dog dog) {
        String sql = "INSERT INTO pet (name, age, species, trained, indoor) VALUES (?, ?, 'DOG', ?, NULL)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dog.getName());
            stmt.setInt(2, dog.getAge());
            stmt.setBoolean(3, dog.isTrained());

            stmt.executeUpdate();
            System.out.println("✅ Dog inserted");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // INSERT CAT
    public boolean insertCat(Cat cat) {
        String sql = "INSERT INTO pet (name, age, species, trained, indoor) VALUES (?, ?, 'CAT', NULL, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cat.getName());
            stmt.setInt(2, cat.getAge());
            stmt.setBoolean(3, cat.isIndoor());

            stmt.executeUpdate();
            System.out.println("✅ Cat inserted");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // SELECT ALL PETS (POLYMORPHISM)
    public List<Pet> getAllPets() {
        List<Pet> petList = new ArrayList<>();
        String sql = "SELECT * FROM pet ORDER BY petID";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("petID");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String species = rs.getString("species");

                Pet pet = null;

                if ("DOG".equals(species)) {
                    boolean trained = rs.getBoolean("trained");
                    pet = new Dog(id, name, age, trained);

                } else if ("CAT".equals(species)) {
                    boolean indoor = rs.getBoolean("indoor");
                    pet = new Cat(id, name, age, indoor);
                }

                if (pet != null) {
                    petList.add(pet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return petList;
    }

    public List<Dog> getAllDogs() {
        List<Dog> dogs = new ArrayList<>();
        String sql = "SELECT * FROM pet WHERE species = 'DOG' ORDER BY petID";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return dogs;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int petID = resultSet.getInt("petID");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age") ;
                boolean trained = resultSet.getBoolean("trained") ;
                Dog dog = new Dog(petID, name, age, trained);
                dogs.add(dog);
            }

            resultSet.close();
            statement.close();

            System.out.println("✅ Retrieved " + dogs.size() + " dogs from database");

        } catch (SQLException e) {
            System.out.println("❌ Select dogs failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return dogs;
    }

    public List<Cat> getAllCats() {
        List<Cat> cats = new ArrayList<>();
        String sql = "SELECT * FROM pet WHERE species = 'CAT' ORDER BY petID";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return cats;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int petID = resultSet.getInt("petID");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age") ;
                boolean indoor = resultSet.getBoolean("indoor") ;
                Cat cat = new Cat(petID, name, age, indoor);
                cats.add(cat);
            }

            resultSet.close();
            statement.close();

            System.out.println("✅ Retrieved " + cats.size() + " cats from database");

        } catch (SQLException e) {
            System.out.println("❌ Select cats failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return cats;
    }

    // SELECT BY ID
    public Pet getPetById(int petID) {
        String sql = "SELECT * FROM pet WHERE petID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, petID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String species = rs.getString("species");

                if ("DOG".equals(species)) {
                    return new Dog(petID, name, age, rs.getBoolean("trained"));
                } else if ("CAT".equals(species)) {
                    return new Cat(petID, name, age, rs.getBoolean("indoor"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public void displayAllPets() {
        List<Pet> petList = getAllPets();

        System.out.println("\n========================================");
        System.out.println("   ALL PET FROM DATABASE");
        System.out.println("========================================");

        if (petList.isEmpty()) {
            System.out.println("No pet members in database.");
        } else {
            for (int i = 0; i < petList.size(); i++) {
                Pet p = petList.get(i);

                // Polymorphic display!
                System.out.print((i + 1) + ". ");
                System.out.print("[" + p.getType() + "] ");
                System.out.println(p.toString());
            }
        }

        System.out.println("========================================\n");
    }

    // POLYMORPHISM DEMO
    public void demonstratePolymorphism() {
        List<Pet> staffList = getAllPets() ;

        System.out.println("\n========================================");
        System.out.println("  POLYMORPHISM: Pet from Database");
        System.out.println("========================================");

        if (staffList.isEmpty()) {
            System.out.println("No pet to demonstrate.");
        } else {
            for (Pet pet : getAllPets()) {
                pet.performAction(); // Polymorphic call!
            }
        }

        System.out.println("========================================\n");
    }



}