package database;

import model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {

    // INSERT DOG
    public boolean insertDog(Dog dog) {
        String sql = "INSERT INTO pet (name, age, species, trained, indoor)" +
                "VALUES (?, ?, ?, 'Dog', ?, ?, NULL)";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, dog.getName());
            statement.setInt(2, dog.getAge());
            statement.setString(3, dog.getSpecies());
            statement.setBoolean(4, dog.isTrained());

            int rowsInserted = statement.executeUpdate();
            statement.close();

            if (rowsInserted > 0) {
                System.out.println("✅ Dog inserted: " + dog.getName());
                return true;
            }

        } catch (SQLException e) {
            System.out.println("❌ Insert Dog failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    // INSERT CAT
    public boolean insertCat(Cat cat) {
        String sql = "INSERT INTO pet (name, age, species, trained, indoor)" +
                " VALUES (?, ?, ?, 'Cat', ?, NULL, ?)";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
             PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, cat.getName());
            statement.setInt(2, cat.getAge());
            statement.setString(3, cat.getSpecies());
            statement.setBoolean(4, cat.isIndoor());

            int rowsInserted = statement.executeUpdate();
            statement.close();

            if (rowsInserted > 0) {
                System.out.println("✅ Cat inserted: " + cat.getName());
                return true;
            }

        } catch (SQLException e) {
            System.out.println("❌ Insert Cat failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    // SELECT ALL PETS (POLYMORPHISM)
    public List<Pet> getAllPets() {
        List<Pet> petList = new ArrayList<>();
        String sql = "SELECT * FROM pet ORDER BY petID";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return petList;

        try {
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Pet pet = extractPetFromResultSet(resultSet);
                if (pet != null) {
                    petList.add(pet);
                }
            }

            resultSet.close();
            statement.close();

            System.out.println("✅ Retrieved " + petList.size() + " pet from database");

        } catch (SQLException e) {
            System.out.println("❌ Select all pet failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return petList;
    }

    public Pet getPetById(int petID) {
        String sql = "SELECT * FROM pet WHERE petID = ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, petID);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Pet pet = extractPetFromResultSet(resultSet);

                resultSet.close();
                statement.close();

                if (pet != null) {
                    System.out.println("✅ Found pet with ID: " + petID);
                }

                return pet;
            }

            System.out.println("⚠️ No pet found with ID: " + petID);

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println("❌ Select by ID failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return null;
    }

    public List<Dog> getAllDogs() {
        List<Dog> dogs = new ArrayList<>();
        String sql = "SELECT * FROM pet WHERE petType = 'Dog' ORDER BY petID";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return dogs;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Pet pet = extractPetFromResultSet(resultSet);
                if (pet instanceof Dog) {
                    dogs.add((Dog) pet);
                }
            }

            resultSet.close();
            statement.close();

            System.out.println("✅ Retrieved " + dogs.size() + " dogs");

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
        String sql = "SELECT * FROM pet WHERE petType = 'Cat' ORDER BY petID";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return cats;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Pet pet = extractPetFromResultSet(resultSet);
                if (pet instanceof Cat) {
                    cats.add((Cat) pet);
                }
            }

            resultSet.close();
            statement.close();

            System.out.println("✅ Retrieved " + cats.size() + " cats");

        } catch (SQLException e) {
            System.out.println("❌ Select cats failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return cats;
    }

    public boolean updateDog(Dog dog) {
        String sql = "UPDATE pet SET name = ?, age = ?, species = ?, trained = ? " +
                "WHERE petID = ? AND petType = 'Dog'";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, dog.getName());
            statement.setInt(2, dog.getAge()) ;
            statement.setString(3, dog.getSpecies());
            statement.setBoolean(4, dog.isTrained());
            statement.setInt(5, dog.getPetID());

            int rowsUpdated = statement.executeUpdate();
            statement.close();

            if (rowsUpdated > 0) {
                System.out.println("✅ Dog updated: " + dog.getName());
                return true;
            } else {
                System.out.println("⚠️ No dog found with ID: " + dog.getPetID());
            }

        } catch (SQLException e) {
            System.out.println("❌ Update Dog failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }

    public boolean updateCat(Cat cat) {
        String sql = "UPDATE pet SET name = ?, age = ?, species = ?, indoor = ? " +
                "WHERE petID = ? AND petType = 'Cat'";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, cat.getName());
            statement.setInt(2, cat.getAge()) ;
            statement.setString(3, cat.getSpecies()) ;
            statement.setBoolean(4, cat.isIndoor()) ;
            statement.setInt(5, cat.getPetID());

            int rowsUpdated = statement.executeUpdate();
            statement.close();

            if (rowsUpdated > 0) {
                System.out.println("✅ Cat updated: " + cat.getName());
                return true;
            } else {
                System.out.println("⚠️ No cat found with ID: " + cat.getPetID());
            }

        } catch (SQLException e) {
            System.out.println("❌ Update Cat failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }

    public boolean deletePet(int petID) {
        String sql = "DELETE FROM pet WHERE petID = ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, petID);

            int rowsDeleted = statement.executeUpdate();
            statement.close();

            if (rowsDeleted > 0) {
                System.out.println("✅ Pet deleted (ID: " + petID + ")");
                return true;
            } else {
                System.out.println("⚠️ No pet found with ID: " + petID);
            }

        } catch (SQLException e) {
            System.out.println("❌ Delete failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }

    public List<Pet> searchByName(String name) {
        List<Pet> petList = new ArrayList<>();

        String sql = "SELECT * FROM pet WHERE name ILIKE ? ORDER BY name";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return petList;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Pet pet = extractPetFromResultSet(resultSet);
                if (pet != null) {
                    petList.add(pet);
                }
            }

            resultSet.close();
            statement.close();

            System.out.println("✅ Found " + petList.size() + " pet matching '" + name + "'");

        } catch (SQLException e) {
            System.out.println("❌ Search by name failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return petList;
    }

    public List<Pet> searchByAge(int minAge, int maxAge) {
        List<Pet> petList = new ArrayList<>();

        String sql = "SELECT * FROM pet WHERE age BETWEEN ? AND ? ORDER BY age DESC";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return petList;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, minAge) ;
            statement.setInt(2, maxAge) ;

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Pet pet = extractPetFromResultSet(resultSet);
                if (pet != null) {
                    petList.add(pet);
                }
            }

            resultSet.close();
            statement.close();

            System.out.println("✅ Found " + petList.size() + " pet in age " +
                    minAge + " - " + maxAge);

        } catch (SQLException e) {
            System.out.println("❌ Search by age failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return petList;
    }

    public List<Pet> searchByMinAge(int minAge) {
        List<Pet> petList = new ArrayList<>();

        String sql = "SELECT * FROM pet WHERE age >= ? ORDER BY age DESC";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return petList;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, minAge) ;

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Pet pet = extractPetFromResultSet(resultSet);
                if (pet != null) {
                    petList.add(pet);
                }
            }

            resultSet.close();
            statement.close();

            System.out.println("✅ Found " + petList.size() + " pet age >= " + minAge);

        } catch (SQLException e) {
            System.out.println("❌ Search by min age failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return petList;
    }

    private Pet extractPetFromResultSet(ResultSet resultSet) throws SQLException {
        int petID = resultSet.getInt("petID");
        String name = resultSet.getString("name");
        int age = resultSet.getInt("age") ;
        String species = resultSet.getString("species") ;
        String petType = resultSet.getString("petType");

        Pet pet = null;

        if ("Dog".equals(petType)) {
            boolean trained = resultSet.getBoolean("trained") ;
            pet = new Dog(petID, name, age, species, trained);

        } else if ("Cat".equals(petType)) {
            boolean indoor = resultSet.getBoolean("indoor") ;
            pet = new Cat(petID, name, age, species, indoor);
        }

        return pet;
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
                Pet pet = petList.get(i);
                System.out.print((i + 1) + ". ");
                System.out.print("[" + pet.getType() + "] ");
                System.out.println(pet.toString());
            }
        }

        System.out.println("========================================\n");
    }

    // POLYMORPHISM DEMO
    public void demonstratePolymorphism() {
        List<Pet> petList = getAllPets() ;

        System.out.println("\n========================================");
        System.out.println("  POLYMORPHISM: Pet from Database");
        System.out.println("========================================");

        if (petList.isEmpty()) {
            System.out.println("No pet to demonstrate.");
        } else {
            for (Pet pet : petList) {
                pet.performAction(); // Polymorphic call!
            }
        }

        System.out.println("========================================\n");
    }



}