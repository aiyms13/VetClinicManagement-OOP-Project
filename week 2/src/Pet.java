public class Pet {
    private int petID;
    private String name;
    private String species;
    private int age;

    // Parameterized constructor
    public Pet(int petID, String name, String species, int age) {
        this.petID = petID;
        this.name = name;
        this.species = species;
        this.age = age;
    }
    // Default constructor
    public Pet() {
        this.petID = 0;
        this.name = "Unknown";
        this.species = "Unknown";
        this.age = 0;
    }

    // Methods
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

    public int getPetID() {
        return petID;
    }
    public void setPetID(int petID) {
        this.petID = petID;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }
    public void setSpecies(String species) {
        this.species = species;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Pet{petID=" + petID +
                ", name=" + name +
                ", species=" + species +
                ", age=" + age + "}";
    }
}





