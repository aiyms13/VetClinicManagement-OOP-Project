public class Veterinarian {
    private int vetID;
    private String name;
    private String specialization;
    private int experienceYears;

    // Parameterized constructor
    public Veterinarian(int vetID, String name, String specialization, int experienceYears) {
        this.vetID = vetID;
        setName(name);
        setSpecialization(specialization);
        setExperienceYears(experienceYears);
    }
    // Default constructor
    public Veterinarian() {
        this.vetID = 0;
        this.name = "Unknown";
        this.specialization = "General";
        this.experienceYears = 0;
    }

    //getters
    public int getVetID() { return vetID; }
    public String getName() { return name; }
    public String getSpecialization() { return specialization; }
    public int getExperienceYears() { return experienceYears; }

    //setters with validation

    public void setVetID(int vetID) {
        this.vetID = vetID;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    //methods
    public boolean isExperienced() {
        return experienceYears >= 5;
    }
    public boolean canTreat(String species) {
        return specialization.equals(species);
    }

    @Override
    public String toString() {
        return "Veterinarian{" +
                "vetID=" + vetID +
                ", name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                ", experienceYears= " + experienceYears + " years" +
                '}';
    }
}
