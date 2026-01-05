public class Pet {
    private int petID;
    private String name;
    private String species;
    private int age;

    // Parameterized constructor
    public Pet(int petID, String name, String species, int age) {
        this.petID = petID;
        setName(name);
        setSpecies(species);
        setAge(age);
    }
    // Default constructor
    public Pet() {
        this.petID = 0;
        this.name = "Unknown";
        this.species = "Unknown";
        this.age = 0;
    }

    //getters
    public int getPetID() { return petID; }
    public String getName() { return name; }
    public String getSpecies() { return species; }
    public int getAge() { return age; }

    //setters with validation

    public void setPetID(int petID) {
        this.petID = petID;
    }
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Warning: Name cannot be empty!");
        }
    }
    public void setSpecies(String species) {
        if (species != null && !species.trim().isEmpty()) {
            this.species = species;
        } else {
            System.out.println("Warning: Species cannot be empty!");
        }
    }
    public void setAge(int age) {
        if (age >= 0) {
            this.age = age;
        }
    }

    //methods
    public boolean isYoung() {
        return age < 3;
    }
    public String getLifeStage() {
        if (age < 1) {
            return "Baby";
        } else if (age <= 7) {
            return "Adult";
        } else {
            return "Senior";
        }
    }

    @Override
    public String toString() {
        return "Pet{" +
                "petID=" + petID +
                ", name='" + name + '\'' +
                ", species='" + species + '\'' +
                ", age=" + age +
                '}';
    }
}
