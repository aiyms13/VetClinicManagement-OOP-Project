package model;

public abstract class Pet {
    protected int petID;
    protected String name;
    protected int age;
    protected String species;

    //parameterized constructor
    public Pet(int petID, String name, int age, String species) {
        setPetID(petID);
        setName(name);
        setAge(age);
        setSpecies(species);
    }

    //getters
    public int getPetID() {return petID;}
    public String getName() {return name;}
    public int getAge() {return age;}
    public String getSpecies() {return species;}

    //setters  with exceptions
    public void setPetID(int petID) {
        if (petID <= 0) {
            throw new IllegalArgumentException("Pet ID must be positive");
        }
        this.petID = petID;
    }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (name.length() < 2) {
            throw new IllegalArgumentException("Name must be at least 2 characters");
        }
        this.name = name;
    }
    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        this.age = age;
    }
    public void setSpecies(String species) {
        if (species == null || species.trim().isEmpty()) {
            throw new IllegalArgumentException("Species cannot be empty");
        }
        this.species = species;
    }

    // Concrete method
    public boolean isYoung() {
        return age < 3;
    }
    // Abstract methods
    public abstract void performAction();
    public abstract String getType();


    @Override
    public String toString() {
        return "[" + getType() + "]" + name +
                " (ID:" + petID +
                ", Age:" + age +
                ", Species=" + species + ")";
    }
}