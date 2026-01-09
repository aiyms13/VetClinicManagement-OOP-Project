public class Pet {
    protected int petID;
    protected String name;
    protected int age;
    protected String species;

    //parameterized constructor
    public Pet(int petID, String name, int age, String species) {
        this.petID = petID;
        this.name = name;
        this.age = age;
        this.species = species;
    }

    //getters
    public int getPetID() {return petID;}
    public String getName() {return name;}
    public int getAge() {return age;}
    public String getSpecies() {return species;}

    //setters
    public void setPetID(int petID) {this.petID = petID;}
    public void setName(String name) {this.name = name;}
    public void setAge(int age) {
        if (age >= 0) this.age = age;
    }
    public void setSpecies(String species) {this.species = species;}

    //methods(2 override + 1 not)
    public void performAction() {
        System.out.println(name + " is being checked by the veterinarian.");
    }
    public String getType() {
        return "Pet";
    }
    public boolean isYoung() {
        return age < 3;
    }

    @Override
    public String toString() {
        return "[" + getType() + "]" + name +
                " (ID:" + petID +
                ", Age:" + age +
                ", Species=" + species + ")";
    }
}